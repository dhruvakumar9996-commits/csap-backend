package main.java.com.csap.controller;

import com.csap.model.Topic;
import com.csap.service.NlpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {
    private final NlpService nlpService;

    public TopicController(NlpService nlpService) {
        this.nlpService = nlpService;
    }

    @GetMapping("/top")
    public ResponseEntity<List<Topic>> topTopics(@RequestParam(defaultValue = "10") int limit) {
        var list = nlpService.getTopTopics(limit);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/extract")
    public ResponseEntity<List<String>> extract(@RequestBody String text,
                                                @RequestParam(defaultValue = "5") int topN) {
        var topics = nlpService.extractTopics(text, topN);
        return ResponseEntity.ok(topics);
    }
}
