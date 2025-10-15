package main.java.com.csap.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "topics")
public class Topic {
    @Id
    private String id;
    private String name;
    private long occurrences; // number of tickets associated
}
