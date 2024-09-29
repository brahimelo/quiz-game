package org.brahim;


import org.brahim.game.Game;
import org.brahim.game.GameException;

public class Main {
    public static void main(String[] args) throws GameException {
        Game game = new Game();

        game.setAutoPlay(false);
        
        game.addPlayer("Player 1");
        game.addPlayer("Player 2");
        game.addPlayer("Player 3");
        game.addPlayer("Player 4");
        game.addPlayer("Player 5");
        game.addPlayer("Player 6");

        game.start();

        while (game.isNotFinished()) {
            game.play();
        }
    }
}
