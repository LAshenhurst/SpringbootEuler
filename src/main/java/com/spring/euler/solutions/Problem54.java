package com.spring.euler.solutions;

import com.spring.euler.helper.FilesHelper;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Problem54 {
    private static final HashMap<String, Integer> CARD_SCORES = fillCardScores();
    private static HashMap<String, Integer> HAND_SCORES;
    private static final List<String> CARD_VALUE_ORDER = List.of("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");

    public static String run() {
        fillHandScores();
        File file = FilesHelper.getResourceFile("static/Problem54.txt");
        List<String> lines = FilesHelper.readAllLines(file);
        long result =  lines.stream()
                .filter(line -> {
                    String playerOneHand = line.substring(0, 14);
                    String playerTwoHand = line.substring(14);
                    return compareHands(playerOneHand, playerTwoHand);
                }).count();

        return String.valueOf(result);
    }

    private static Boolean compareHands(String firstHand, String secondHand) {
        return handScore(firstHand) > handScore(secondHand);
    }

    private static Integer handScore(String hand) {
        String handScore = flushValue(hand);
        if (handScore == null) { handScore = matches(hand); }
        if (handScore == null) { handScore = highestCard(hand); }
        return HAND_SCORES.get(handScore);
    }

    private static String highestCard(String hand) {
        return Arrays.stream(hand.split(" "))
                .filter(x -> !StringUtils.isEmpty(x))
                .map(card -> card.split("")[0])
                .max(Comparator.comparing(CARD_SCORES::get))
                .get();
    }

    private static String matches(String hand) {
        List<String> cardValues = new ArrayList<>();
        Arrays.stream(hand.split(" "))
                .map(card -> card.split(""))
                .forEach(cardSplit -> {
                    if (cardSplit.length < 2) { return; }
                    cardValues.add(cardSplit[0]);
                });
        HashSet<String> uniqueValues = new HashSet<>(cardValues);
        for (int i = 0; i < cardValues.size(); i++) {
            String cardValue = cardValues.get(i);
            if (Collections.frequency(cardValues, cardValue) == 4) { return cardValue + "Four"; }
            else if (Collections.frequency(cardValues, cardValue) == 3) {
                if (uniqueValues.size() == 2) {
                    List<String> uniques = new ArrayList<>(uniqueValues);
                    return uniques.get(0).equals(cardValue) ? uniques.get(0) + uniques.get(1) + "Full" : uniques.get(1) + uniques.get(0) + "Full";
                } else { return cardValue + "Triple"; }
            } else if (Collections.frequency(cardValues, cardValue) == 2) {
                if (uniqueValues.size() == 3) {
                    List<String> pairs = uniqueValues.stream().filter(x -> Collections.frequency(cardValues, x) == 2).collect(Collectors.toList());
                    return CARD_SCORES.get(pairs.get(0)) > CARD_SCORES.get(pairs.get(1)) ? pairs.get(0) + pairs.get(1) + "Pairs" : pairs.get(1) + pairs.get(0) + "Pairs";
                } else { return cardValue + "Pair"; }
            }
        }
        return null;
    }

    private static String flushValue(String hand) {
        List<String> cardValues = new ArrayList<>();
        List<String> cardSuits = new ArrayList<>();
        Arrays.stream(hand.split(" "))
                .map(card -> card.split(""))
                .collect(Collectors.toList())
                .forEach(cardSplit -> {
                    if (cardSplit.length < 2) { return; }
                    cardValues.add(cardSplit[0]);
                    cardSuits.add(cardSplit[1]);
                });
        HashSet<String> uniqueSuits = new HashSet<>(cardSuits);
        List<Integer> sortedValues = cardValues.stream().map(CARD_VALUE_ORDER::indexOf).sorted().collect(Collectors.toList());
        if (uniqueSuits.size() == 1) {
            for (int i = 0; i < sortedValues.size() - 1; i++) {
                if (sortedValues.get(i) + 1 != sortedValues.get(i + 1)) { return "Flush"; }
            }
            return CARD_VALUE_ORDER.get(sortedValues.get(0)) + "Flush";
        } else {
            for (int i = 0; i < sortedValues.size() - 1; i++) {
                if (sortedValues.get(i) + 1 != sortedValues.get(i + 1)) { return null; }
            }
            return CARD_VALUE_ORDER.get(sortedValues.get(0)) + "Straight";
        }
    }

    private static HashMap<String, Integer> fillCardScores() {
        HashMap<String, Integer> cardScores = new HashMap<>();
        cardScores.put("2", 1); cardScores.put("3", 2); cardScores.put("4", 3);
        cardScores.put("5", 4); cardScores.put("6", 5); cardScores.put("7", 6);
        cardScores.put("8", 7); cardScores.put("9", 8); cardScores.put("T", 9);
        cardScores.put("J", 10); cardScores.put("Q", 11); cardScores.put("K", 12);
        cardScores.put("A", 13);
        return cardScores;
    }

    private static void fillHandScores() {
        int score = 13;
        HAND_SCORES = new HashMap<>();
        HAND_SCORES.putAll(CARD_SCORES);
        score = addToHandScore("Pair", score);
        for (int i = 1; i < CARD_VALUE_ORDER.size(); i++) {
            for (int j = 0; j < i; j++) {
                HAND_SCORES.put(CARD_VALUE_ORDER.get(i) + CARD_VALUE_ORDER.get(j) + "Pairs", score);
                score++;
            }
        }
        score = addToHandScore("Triple", score);
        score = addToHandScore("Straight", score);
        HAND_SCORES.put("Flush", score + 1); score++;
        for (int i = 1; i < CARD_VALUE_ORDER.size(); i++) {
            for (int j = 1; j < CARD_VALUE_ORDER.size(); j++) {
                HAND_SCORES.put(CARD_VALUE_ORDER.get(i) + CARD_VALUE_ORDER.get(j) + "Full", score);
                score++;
            }
        }
        score = addToHandScore("Four", score);
        addToHandScore("Flush", score);
    }

    private static Integer addToHandScore(String scoreType, int score) {
        for (String value: CARD_VALUE_ORDER) {
            score++;
            HAND_SCORES.put(value + scoreType, score);
        }
        return score;
    }
}
