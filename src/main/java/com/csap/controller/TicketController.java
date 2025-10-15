package main.java.com.csap.controller;

import com.csap.dto.CreateTicketRequest;
import com.csap.dto.TicketResponse;
import com.csap.model.Ticket;
import com.csap.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@Valid @RequestBody CreateTicketRequest req) {
        Ticket t = ticketService.createTicket(req.getCustomerId(), req.getTitle(), req.getDescription());
        return ResponseEntity.ok(toDto(t));
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> listAll(@RequestParam(required = false) String customerId,
                                                        @RequestParam(required = false) String status) {
        List<Ticket> tickets;
        if (customerId != null) tickets = ticketService.listByCustomer(customerId);
        else if (status != null) tickets = ticketService.listByStatus(status);
        else tickets = ticketService.listAll();

        List<TicketResponse> dto = tickets.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TicketResponse> updateStatus(@PathVariable String id, @RequestParam String status) {
        Ticket t = ticketService.updateStatus(id, status);
        if (t == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(toDto(t));
    }

    private TicketResponse toDto(Ticket t) {
        return TicketResponse.builder()
                .id(t.getId())
                .customerId(t.getCustomerId())
                .title(t.getTitle())
                .description(t.getDescription())
                .status(t.getStatus())
                .createdAt(t.getCreatedAt())
                .updatedAt(t.getUpdatedAt())
                .topics(t.getTopics())
                .build();
    }
}
