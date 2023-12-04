package com.advent.code.dto;

import java.util.ArrayList;
import java.util.List;

public class ScratchCard {

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
}
