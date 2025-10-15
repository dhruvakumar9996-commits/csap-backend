package main.java.com.csap.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateTicketRequest {
    @NotBlank
    private String customerId;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
}
