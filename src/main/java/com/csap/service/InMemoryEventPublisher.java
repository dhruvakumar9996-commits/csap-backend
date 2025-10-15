package main.java.com.csap.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InMemoryEventPublisher implements EventPublisher {
    private static final Logger log = LoggerFactory.getLogger(InMemoryEventPublisher.class);

    @Override
    public void publishTicketCreated(String ticketId) {
        // In a real system, this would publish to Kafka. For this deliverable we log it.
        log.info("[EventPublisher] TicketCreated event published for ticketId={}", ticketId);
    }
}
