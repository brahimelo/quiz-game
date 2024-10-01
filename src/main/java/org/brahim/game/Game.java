package org.brahim.game;

import org.brahim.player.Player;
import org.brahim.player.PlayerManager;
import org.brahim.question.Category;
import org.brahim.question.Question;
import org.brahim.question.QuestionDeckManager;

import java.util.Random;
import java.util.Scanner;

public class Game {

    private static final int DICE_BOUND = 6;
    private static final int MINIMUM_REQUIRED_PLAYERS = 2;

    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);

    private PlayerManager playerManager = new PlayerManager();
    private QuestionDeckManager questionDeckManager = new QuestionDeckManager();

    private boolean autoPlay = true;

    GameStatus status = GameStatus.INITIALIZING;

    public void start() throws GameException {
        if (playerManager.getPlayerCount() < MINIMUM_REQUIRED_PLAYERS) {
            throw new GameException("Game can only be played with two or more players");
        }

        if (this.status == GameStatus.IN_PROGRESS) {
            throw new GameException("Game is already in progress");
        }

        if (this.status == GameStatus.FINISHED) {
            throw new GameException("Game is already is finished, reset before launching a new game");
        }

        status = GameStatus.IN_PROGRESS;
        System.out.println("\uD83C\uDFAE- Game started with " + playerManager.getPlayerCount() + " player(s)");
    }

    public void resetGame() {
        playerManager = new PlayerManager();
        questionDeckManager = new QuestionDeckManager();
        status = GameStatus.INITIALIZING;
        System.out.println("\uD83D\uDD04- Game has been reset.");
    }

    public void addPlayer(String playerName) throws GameException {
        if (this.status != GameStatus.INITIALIZING) {
            throw new GameException("Game is already in progress or has finished");
        }
        playerManager.addPlayer(playerName);
    }

    public void play() throws GameException {
        if (!canGameBePlayed()) {
            throw new GameException("Game is not in progress");
        }

        System.out.println("------------");

        if (!autoPlay) {
            System.out.println("\uD83C\uDFB2 - Press Enter to launch the dice...");
            scanner.nextLine();
        }

        this.roll(launchDice());
    }

    private void roll(int roll) {

        Player currentPlayer = playerManager.getCurrentPlayer();
        System.out.println("\uD83C\uDFB2- " + currentPlayer.getName() + " rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (!canBeFreedFromPenaltyBox(roll)) {
                System.out.println("\uD83D\uDEAB- " + currentPlayer.getName() + " is not getting out of the penalty box");
                playerManager.advanceToNextPlayerTurn();
                return;
            }
            playerManager.freePlayerFromPenaltyBox();
        }

        playerManager.moveCurrentPlayer(roll);
        boolean isAnswerCorrect = askQuestion();

        if (isAnswerCorrect) {
            System.out.println("\uD83D\uDFE2- Question was correctly answered !!");
            playerManager.playerEarnGold();

            if (playerManager.didPlayerWin()) {
                this.status = GameStatus.FINISHED;
                System.out.println("\uD83D\uDC51- " + this.playerManager.getCurrentPlayer().getName() + " has won !");
                return;
            }

        } else {
            System.out.println("\uD83D\uDD34- Question was incorrectly answered !!");
            playerManager.movePlayerToPenaltyBox();
        }

        playerManager.advanceToNextPlayerTurn();
    }

    public boolean isNotFinished() {
        return status != GameStatus.FINISHED;
    }

    public void setAutoPlay(boolean autoPlay) {
        this.autoPlay = autoPlay;
    }

    private boolean canGameBePlayed() {
        return this.status == GameStatus.IN_PROGRESS;
    }

    private boolean askQuestion() {
        Category category = getCurrentCategory(playerManager.getCurrentPlayer().getPosition());
        Question question = questionDeckManager.getNextQuestion(category);
        System.out.println("\uD83D\uDDE8\uFE0F- " + question);
        return isCorrectlyAnswered(question);
    }

    private boolean isCorrectlyAnswered(Question question) {
        if (autoPlay) {
            return random.nextBoolean();
        }
        return question.isCorrectlyAnswered(getUserInput());
    }

    static Category getCurrentCategory(int position) {
        return Category.values()[position % Category.values().length];
    }

    private boolean canBeFreedFromPenaltyBox(int roll) {
        return roll % 2 != 0;
    }

    private int launchDice() {
        return random.nextInt(DICE_BOUND) + 1;
    }

    private String getUserInput() {
        System.out.println("\uD83D\uDD8A\uFE0F- Enter your answer (max 10 characters): ");
        String userEntry = scanner.nextLine();

        if (userEntry.length() > 10) {
            userEntry = userEntry.substring(0, 10);
        }

        return userEntry;
    }
}
