package com.spring.euler.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class StringHelper {
    public static List<String> permutations(String permuteObject) {
        List<String> chars = permuteObject.chars().mapToObj(e -> String.valueOf((char) e)).collect(Collectors.toList());
        if (StringUtils.isEmpty(chars)) { return Collections.emptyList(); }
        else if (chars.size() == 1) { return List.of(permuteObject); }
        else if (chars.size() == 2) { return List.of(chars.get(0) + chars.get(1), chars.get(1) + chars.get(0)); }
        else if (chars.size() > 2) {
            List<String> result = new ArrayList<>();
            for (int i = 0; i < chars.size(); i++) {
                int skipIndex = i;
                List<String> recursionPermuteObject = chars.stream()
                        .filter(elem -> chars.indexOf(elem) != skipIndex)
                        .collect(Collectors.toList());
                List<String> recursiveResult = permutations(String.join("", recursionPermuteObject));
                for (String recursivePermute: recursiveResult) { result.add(chars.get(i) + recursivePermute); }
            }
            Collections.sort(result);
            return result;
        }
        else { return List.of(permuteObject); }
    }
}
