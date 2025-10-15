package main.java.com.csap.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "tickets")
public class Ticket {
    @Id
    private String id;
    private String customerId;
    private String title;
    private String description;
    private String status; // OPEN, IN_PROGRESS, RESOLVED, CLOSED
    private Instant createdAt;
    private Instant updatedAt;
    private List<String> topics; // topic ids or names
}
