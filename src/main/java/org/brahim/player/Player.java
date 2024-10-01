package org.brahim.player;

public class Player {

    private final String name;
    private boolean isInPenaltyBox;
    private int goldCoins;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.goldCoins = 0;
        this.isInPenaltyBox = false;
    }

    public void moveForward(int roll, int boardSize) {
        this.position = (this.position + roll) % boardSize;
    }

    public void freeFromPenaltyBox() {
        this.isInPenaltyBox = false;
    }

    public void moveToPenaltyBox() {
        this.isInPenaltyBox = true;
    }

    public void earnGold() {
        this.goldCoins++;
    }

    public int getGoldCoins() {
        return goldCoins;
    }

    public String getName() {
        return name;
    }

    public boolean isInPenaltyBox() {
        return isInPenaltyBox;
    }

    public int getPosition() {
        return position;
    }
}

