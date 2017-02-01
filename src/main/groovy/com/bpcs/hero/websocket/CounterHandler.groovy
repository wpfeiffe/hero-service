package com.bpcs.hero.websocket

import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

import javax.xml.soap.Text

/**
 * Counter web socket handler
 */
@Component
class CounterHandler extends TextWebSocketHandler
{
    WebSocketSession session

    void counterIncrementCallback(int counter)
    {
        println  "Trying to send $counter"

        if (session && session.isOpen())
        {
            try
            {
                println "Now sending $counter"

                session.sendMessage(new TextMessage("{\"value\": \"${counter}\"}"))
            }
            catch (Exception e)
            {
                e.printStackTrace()
            }
        }
        else
        {
            println "Don't have a session to send $counter"
        }
    }

    @Override
    void afterConnectionEstablished(WebSocketSession session)
    {
        println "Connection established"
        this.session = session
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception
    {
        if ("CLOSE".equalsIgnoreCase(message.getPayload()))
        {
            session.close()
        }
        else
        {
            println ("Recieved ${message.getPayload()}")
        }

    }
}
