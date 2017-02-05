package com.bpcs.hero.service

import com.bpcs.hero.websocket.CounterHandler
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

import javax.inject.Inject
import java.util.concurrent.atomic.AtomicInteger

/**
 * Counter service
 */
@Service
class CounterService
{
    private AtomicInteger counter = new AtomicInteger()
    private Boolean enabled = true

    @Inject
    CounterHandler counterHandler

    @Scheduled(fixedDelay = 6000L)
    void sendCounterUpdate()
    {
        if (enabled)
        {
            counterHandler.counterIncrementCallback(counter.incrementAndGet())
        }
    }

    Boolean getEnabled()
    {
        return enabled
    }

    void setEnabled(Boolean enabled)
    {
        this.enabled = enabled
    }
}
