package com.advent.code.models;

import com.advent.code.exception.ServiceException;
import org.springframework.lang.NonNull;

import java.util.*;

public class CamelHand implements Comparable<CamelHand> {

    private final String hand;
    private final long bet;
    private final boolean jokerRule;
    private final List<Integer> cardRankList;
    private final int typeRank;

    public long getBet() {
        return bet;
    }

    public CamelHand(String hand, long bet, boolean jokerRule) {
        this.hand = hand;
        this.bet = bet;
        this.jokerRule = jokerRule;
        cardRankList =  Arrays.stream(hand.split("")).map(c -> getCardRank(c, jokerRule)).toList();
        typeRank = calculateTypeRank(cardRankList);
    }

    private int calculateTypeRank(List<Integer> cardRankList) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        cardRankList.forEach(r -> frequencyMap.put(r, frequencyMap.getOrDefault(r, 0) + 1));
        if (jokerRule && frequencyMap.get(1) != null) {
            if(frequencyMap.get(1) == 5) {
                // Special case for hand JJJJJ
                return 6;
            }
            int mostRepeatedCardRank = Collections.max(
                    frequencyMap.entrySet().stream().filter(x -> x.getKey() != 1).toList(),
                    Map.Entry.comparingByValue()).getKey();
            frequencyMap.put(mostRepeatedCardRank, frequencyMap.get(mostRepeatedCardRank) + frequencyMap.get(1));
            frequencyMap.remove(1);
        }
        return switch (frequencyMap.size()) {
            case 1 -> 6;
            case 2 -> frequencyMap.values().stream().anyMatch(x -> x == 4)? 5 : 4;
            case 3 -> frequencyMap.values().stream().anyMatch(x -> x == 3)? 3 : 2;
            case 4 -> 1;
            case 5 -> 0;
            default -> throw new ServiceException("Hand rank not found");
        };
    }

    private static int getCardRank(String s, boolean jokerRule) {
        if (s.matches("[2-9]")) {
            return Integer.parseInt(s);
        }
        return switch (s) {
            case "T" -> 10;
            case "J" -> jokerRule? 1 : 11;
            case "Q" -> 12;
            case "K" -> 13;
            case "A" -> 14;
            default -> throw new ServiceException("Card rank not found");
        };
    }

    public static CamelHand parseCamelHand(String line, boolean jokerRule) {
        String[] camelHandValues = line.split(" ");
        return new CamelHand(camelHandValues[0], Long.parseLong(camelHandValues[1]), jokerRule);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CamelHand camelHand = (CamelHand) o;
        return Objects.equals(hand, camelHand.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hand);
    }

    @Override
    public int compareTo(@NonNull CamelHand camelHand) {
        if (this.equals(camelHand)) {
            throw new ServiceException(String.format("Compared hands are equal: [%s]", hand));
        }
        if (typeRank != camelHand.typeRank) {
           return typeRank - camelHand.typeRank;
        }
        for (int i = 0; i < cardRankList.size(); i++) {
            int thisCardRank = cardRankList.get(i);
            int thatCardRank = camelHand.cardRankList.get(i);
            if (thisCardRank != thatCardRank) {
                return thisCardRank - thatCardRank;
            }
        }
        throw new ServiceException(String.format("Compared hands are equal: [%s]", hand));
    }
}