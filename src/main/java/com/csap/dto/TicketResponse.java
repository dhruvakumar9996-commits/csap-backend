package main.java.com.csap.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class TicketResponse {
    private String id;
    private String customerId;
    private String title;
    private String description;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
    private List<String> topics;
}
