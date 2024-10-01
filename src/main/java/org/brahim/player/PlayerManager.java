package org.brahim.player;

import org.brahim.game.GameException;

import java.util.LinkedList;

public class PlayerManager {

    public static final int MAXIMUM_PLAYERS = 6;
    public static final int WINNING_GOLD_COINS = 6;
    private static final int BOARD_SIZE = 12;

    private final LinkedList<Player> players = new LinkedList<>();

    private Player currentPlayer = null;

    public void addPlayer(String playerName) throws GameException {
        if (players.size() >= MAXIMUM_PLAYERS) {
            throw new GameException("Cannot add " + playerName + ". Maximum player limit of 6 has been reached.");
        }
        Player newPlayer = new Player(playerName);
        players.add(newPlayer);

        if (currentPlayer == null) {
            currentPlayer = newPlayer;
        }

        System.out.println("\uD83D\uDE4E\u200D- " + playerName + " was added. They are " + players.size() + " player(s)");
    }

    public void moveCurrentPlayer(int roll) {
        this.currentPlayer.moveForward(roll, BOARD_SIZE);
        System.out.println("\uD83D\uDCCD- " + this.currentPlayer.getName() + "'s new location is " + this.currentPlayer.getPosition());
    }

    public void advanceToNextPlayerTurn() {
        int currentIndex = players.indexOf(currentPlayer);
        currentPlayer = players.get((currentIndex + 1) % players.size());
    }

    public int getPlayerCount() {
        return players.size();
    }

    public boolean didPlayerWin() {
        if (currentPlayer == null) {
            throw new IllegalStateException("Current player is not set.");
        }
        return currentPlayer.getGoldCoins() == WINNING_GOLD_COINS;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void freePlayerFromPenaltyBox() {
        this.currentPlayer.freeFromPenaltyBox();
        System.out.println("\uD83C\uDFF3\uFE0F- " + this.currentPlayer.getName() + " is getting out of the penalty box");
    }

    public void movePlayerToPenaltyBox() {
        this.currentPlayer.moveToPenaltyBox();
        System.out.println("↩\uFE0F- " + this.currentPlayer.getName() + " was sent to the penalty box");
    }

    public void playerEarnGold() {
        this.currentPlayer.earnGold();
        System.out.println("\uD83E\uDE99- " + this.currentPlayer.getName() + " now has " + this.currentPlayer.getGoldCoins() + " Gold Coin(s).");
    }
}