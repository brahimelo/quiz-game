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
        System.out.println("\uD83C\uDFF3\uFE0F- " + this.name + " is getting out of the penalty box");
    }

    public void moveToPenaltyBox() {
        this.isInPenaltyBox = true;
        System.out.println("↩\uFE0F- " + this.name + " was sent to the penalty box");
    }

    public void earnGold() {
        this.goldCoins++;
        System.out.println("\uD83E\uDE99- " + this.name + " now has " + this.goldCoins + " Gold Coin(s).");
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

