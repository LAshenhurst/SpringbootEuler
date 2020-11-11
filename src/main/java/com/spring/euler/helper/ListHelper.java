package com.spring.euler.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ListHelper {
    public static <T> List<List<T>> permute(List<T> original) {
        if (original.isEmpty()) {
            List<List<T>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        T firstElement = original.remove(0);
        List<List<T>> result = new ArrayList<>();
        List<List<T>> recursionResult = permute(original);
        for (List<T> recursionObject: recursionResult) {
            for (int i = 0; i <= recursionObject.size(); i++) {
                List<T> temp = new ArrayList<>(recursionObject);
                temp.add(i, firstElement);
                result.add(temp);
            }
        }
        return result;
    }

    public static <T> List<List<T>> generateCombinations (List<T> source, Integer size) {
        return MathsHelper.generateCombinatorics(source.size(), size)
                .stream()
                .map(combination -> {
                    List<T> sourceCombination = new ArrayList<>();
                    for (int index: combination) { sourceCombination.add(source.get(index - 1)); }

                    return sourceCombination;
                })
                .collect(Collectors.toList());
    }
}
