package com.advent.code.models;

import com.advent.code.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ScratchCard {

    private final static Pattern patternScratchCard = Pattern.compile("^Card +([0-9]*):");

    private int copies = 1;
    private int cardId;
    private List<Integer> winingNumbers;
    private List<Integer> cardNumbers;
    private Integer matchingNumbers = null;

    public ScratchCard() {
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public void setWiningNumbers(List<Integer> winingNumbers) {
        this.winingNumbers = winingNumbers;
    }

    public void setCardNumbers(List<Integer> cardNumbers) {
        this.cardNumbers = cardNumbers;
    }

    public int getCopies() {
        return copies;
    }

    public int getMatchingNumbers() {
        if (matchingNumbers == null) {
            List<Integer> sharedNumbers = new ArrayList<>(winingNumbers);
            sharedNumbers.retainAll(cardNumbers);
            matchingNumbers = sharedNumbers.size();
        }
        return matchingNumbers;
    }

    public int calculatePoints() {
        return (int) Math.pow(2, getMatchingNumbers() - 1);
    }

    public void addCopies(int newCopies) {
        copies += newCopies;
    }

    public static ScratchCard parseScratchCard(String line) {
        ScratchCard scratchCard = new ScratchCard();
        Matcher matcherGame = patternScratchCard.matcher(line);
        if (matcherGame.find()) {
            int cardId = Integer.parseInt(matcherGame.group(1));
            scratchCard.setCardId(cardId);
            line = line.replace(matcherGame.group(), "");
        } else {
            throw new ServiceException("Card ID not found");
        }
        scratchCard.setWiningNumbers(Stream.of(line.split("\\|")[0].split(" ")).filter(s -> !s.isBlank())
                .map(Integer::parseInt).toList());
        scratchCard.setCardNumbers(Stream.of(line.split("\\|")[1].split(" ")).filter(s -> !s.isBlank())
                .map(Integer::parseInt).toList());
        return scratchCard;
    }
}
