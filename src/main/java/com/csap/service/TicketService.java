package main.java.com.csap.service;

import com.csap.model.Ticket;
import com.csap.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final NlpService nlpService;
    private final EventPublisher eventPublisher;

    public TicketService(TicketRepository ticketRepository, NlpService nlpService, EventPublisher eventPublisher) {
        this.ticketRepository = ticketRepository;
        this.nlpService = nlpService;
        this.eventPublisher = eventPublisher;
    }

    public Ticket createTicket(String customerId, String title, String description) {
        Ticket ticket = Ticket.builder()
                .id(UUID.randomUUID().toString())
                .customerId(customerId)
                .title(title)
                .description(description)
                .status("OPEN")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        // Extract topics
        var topics = nlpService.extractTopics(title + " " + description, 3);
        ticket.setTopics(topics);

        // Persist
        Ticket saved = ticketRepository.save(ticket);

        // Update topic counts
        nlpService.upsertTopics(topics);

        // Publish event (in-memory)
        eventPublisher.publishTicketCreated(saved.getId());

        return saved;
    }

    public List<Ticket> listAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> listByCustomer(String customerId) {
        return ticketRepository.findByCustomerId(customerId);
    }

    public List<Ticket> listByStatus(String status) {
        return ticketRepository.findByStatus(status);
    }

    public Ticket updateStatus(String ticketId, String newStatus) {
        var opt = ticketRepository.findById(ticketId);
        if (opt.isEmpty()) return null;
        Ticket t = opt.get();
        t.setStatus(newStatus);
        t.setUpdatedAt(Instant.now());
        return ticketRepository.save(t);
    }
}
