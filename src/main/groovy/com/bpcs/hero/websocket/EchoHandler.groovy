package com.bpcs.hero.websocket

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.PongMessage
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

import javax.inject.Inject

/**
 * Simple echo handler to exercise in/out with websockets
 */
@Component
class EchoHandler extends TextWebSocketHandler
{

    private final Logger logger = LoggerFactory.getLogger(EchoHandler.class);

    List<WebSocketSession> sessions = []

    @Override
    void afterConnectionEstablished(WebSocketSession session)
    {
        println "Connection established"
        this.sessions << session
        logger.info("afterConnectionEstablished() invoked")
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception
    {
        String textMessage = message.getPayload()
        String messageToBroadcast = "SERVER SAYS: " + textMessage
        if ("CLOSE".equalsIgnoreCase(message.getPayload()))
        {
            session.close()
            this.sessions.remove(session)
        }
        else
        {
            logger.info ("Recieved ${textMessage}")
            this.broadCastMessage(messageToBroadcast)
        }
        logger.info("handleTextMessage() invoked")

    }

    protected void broadCastMessage(String messageToBroadcast)
    {
        TextMessage textMessage = new TextMessage("{\"value\": \"${messageToBroadcast}\"}")
        sessions.each { WebSocketSession session ->
            if (session && session.isOpen())
            {
                try
                {
                    println "Now sending message: \"${messageToBroadcast}\" on session: ${session.id}"

                    session.sendMessage(textMessage)
                }
                catch (Exception e)
                {
                    e.printStackTrace()
                }
            }
            else
            {
                println "Don't have a session to send "
            }
        }
    }

    @Override
    void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception
    {
        super.handleMessage(session, message)
        logger.info("handleMessage() invoked")
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception
    {
        super.handlePongMessage(session, message)
        logger.info("handlePongMessage() invoked")
    }

    @Override
    void handleTransportError(WebSocketSession session, Throwable exception) throws Exception
    {
        super.handleTransportError(session, exception)
        logger.info("handleTransportError() invoked")
    }

    @Override
    void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception
    {
        super.afterConnectionClosed(session, status)
        this.sessions.remove(session)
        logger.info("afterConnectionClosed() invoked")
    }
}
