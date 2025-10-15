package main.java.com.csap.util;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextUtils {
    private static final Set<String> STOPWORDS = Set.of(
            "the","is","in","at","which","on","a","an","and","or","for","to","of","with","i","me","we","you","it","this","that"
    );
    private static final Pattern WORD = Pattern.compile("[a-zA-Z0-9]+");

    public static List<String> tokenizeAndFilter(String text) {
        String lower = text.toLowerCase(Locale.ROOT);
        var matcher = WORD.matcher(lower);
        List<String> tokens = new ArrayList<>();
        while (matcher.find()) {
            String token = matcher.group();
            if (STOPWORDS.contains(token)) continue;
            if (token.length() <= 2) continue;
            tokens.add(token);
        }
        return tokens;
    }
}
