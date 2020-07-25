package com.spring.euler.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public abstract class StringHelper {
    public static boolean isPalindrome(String val) {
        return new StringBuilder(val).reverse().toString().equals(val);
    }

    public static List<String> permutations(String permuteObject) {
        List<String> chars = permuteObject.chars().mapToObj(e -> String.valueOf((char) e)).collect(Collectors.toList());
        if (StringUtils.isEmpty(chars)) { return Collections.emptyList(); }
        else if (chars.size() == 1) { return List.of(permuteObject); }
        else if (chars.size() == 2) { return List.of(chars.get(0) + chars.get(1), chars.get(1) + chars.get(0)); }
        else if (chars.size() > 2) {
            List<String> result = new ArrayList<>();
            for (int i = 0; i < chars.size(); i++) {
                List<String> recursionPermuteObject = new ArrayList<>(chars);
                recursionPermuteObject.remove(i);
                List<String> recursiveResult = permutations(String.join("", recursionPermuteObject));
                for (String recursivePermute: recursiveResult) { result.add(chars.get(i) + recursivePermute); }
            }
            result = result.stream().distinct().collect(Collectors.toList());
            Collections.sort(result);
            return result;
        }
        else { return List.of(permuteObject); }
    }

    public static List<String> rotations(String rotateObject) {
        List<String> chars = List.of(rotateObject.split(""));
        if (StringUtils.isEmpty(chars)) { return Collections.emptyList(); }
        else if (chars.size() == 1) { return List.of(rotateObject); }
        else if (chars.size() == 2) { return List.of(chars.get(0) + chars.get(1), chars.get(1) + chars.get(0)); }
        else if (chars.size() > 2) {
            List<String> result = new ArrayList<>();
            result.add(rotateObject);
            for (int i = 0; i < chars.size() - 1; i++) {
                List<String> rotateChars = new ArrayList<>(List.of(result.get(result.size() - 1).split("")));
                String removeChar = rotateChars.remove(0);
                rotateChars.add(removeChar);
                result.add(String.join("", rotateChars));
            }
            return result;
        }
        else { return List.of(rotateObject); }
    }
}
