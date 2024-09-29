package org.brahim.game;

import junit.framework.TestCase;
import org.brahim.question.Category;

import static org.brahim.game.Game.getCurrentCategory;
import static org.junit.Assert.assertThrows;

public class GameTest extends TestCase {
    
    public void testStart() throws GameException {
        Game game = new Game();
        assertThrows(GameException.class, game::start);
        assertThrows(GameException.class, game::play);
        game.addPlayer("p1");
        game.addPlayer("p2");
        game.start();
        assertTrue(game.isNotFinished());
        assertThrows(GameException.class, game::start);
        while (game.isNotFinished()) {
            game.play();
        }
        assertThrows(GameException.class, game::start);
    }

    public void testAddPlayer() throws GameException {
        Game game = new Game();
        game.addPlayer("p1");
        game.addPlayer("p2");
        game.start();
        assertThrows(GameException.class, () -> game.addPlayer("p3"));
    }

    public void testResetGame() throws GameException {
        Game game = new Game();
        game.addPlayer("p1");
        game.addPlayer("p2");
        game.start();
        assertTrue(game.isNotFinished());
        while (game.isNotFinished()) {
            game.play();
        }
        assertFalse(game.isNotFinished());
        game.resetGame();
        assertTrue(game.isNotFinished());
    }

    public void testCurrentCategory() {
        assertEquals(Category.POP, getCurrentCategory(0));
        assertEquals(Category.SCIENCE, getCurrentCategory(1));
        assertEquals(Category.SPORTS, getCurrentCategory(2));
        assertEquals(Category.ROCK, getCurrentCategory(3));
        assertEquals(Category.POP, getCurrentCategory(4));
        assertEquals(Category.SCIENCE, getCurrentCategory(5));
        assertEquals(Category.SPORTS, getCurrentCategory(6));
        assertEquals(Category.ROCK, getCurrentCategory(7));
        assertEquals(Category.POP, getCurrentCategory(8));
        assertEquals(Category.SCIENCE, getCurrentCategory(9));
        assertEquals(Category.SPORTS, getCurrentCategory(10));
        assertEquals(Category.ROCK, getCurrentCategory(11));
    }
}