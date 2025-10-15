package main.java.com.csap.service;

import com.csap.model.Topic;
import com.csap.repository.TopicRepository;
import com.csap.util.TextUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NlpService {
    private final TopicRepository topicRepository;

    public NlpService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    /**
     * Simple topic extraction:
     * - Lowercase, tokenize, remove stopwords/punctuation
     * - Pick top N frequent words as 'topics' (for demo)
     */
    public List<String> extractTopics(String text, int topN) {
        if (text == null || text.isBlank()) return Collections.emptyList();
        List<String> tokens = TextUtils.tokenizeAndFilter(text);
        if (tokens.isEmpty()) return Collections.emptyList();

        Map<String, Long> freq = tokens.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        return freq.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(topN)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Increment topic counts in DB (create if not exists)
     */
    public void upsertTopics(Collection<String> topicNames) {
        for (String name : topicNames) {
            topicRepository.findByName(name).ifPresentOrElse(topic -> {
                topic.setOccurrences(topic.getOccurrences() + 1);
                topicRepository.save(topic);
            }, () -> {
                Topic t = Topic.builder().name(name).occurrences(1L).build();
                topicRepository.save(t);
            });
        }
    }

    public List<Topic> getTopTopics(int limit) {
        return topicRepository.findAll().stream()
                .sorted(Comparator.comparingLong(Topic::getOccurrences).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }
}
