package org.brahim.player;

import junit.framework.TestCase;
import org.brahim.game.GameException;

import static org.junit.Assert.assertThrows;

public class PlayerManagerTest extends TestCase {

    public void testAddPlayer() throws GameException {
        PlayerManager playerManager = new PlayerManager();
        playerManager.addPlayer("p1");
        assertEquals(1, playerManager.getPlayerCount());
        assertEquals("p1", playerManager.getCurrentPlayer().getName());
        playerManager.addPlayer("p2");
        playerManager.addPlayer("p3");
        playerManager.addPlayer("p4");
        playerManager.addPlayer("p5");
        playerManager.addPlayer("p6");
        assertEquals(6, playerManager.getPlayerCount());
        assertThrows(GameException.class, () -> playerManager.addPlayer("p7"));
    }

    public void testMoveCurrentPlayer() throws GameException {
        PlayerManager playerManager = new PlayerManager();
        playerManager.addPlayer("p2");

        playerManager.moveCurrentPlayer(1);
        assertEquals(1, playerManager.getCurrentPlayer().getPosition());

        playerManager.moveCurrentPlayer(5);
        assertEquals(6, playerManager.getCurrentPlayer().getPosition());

        playerManager.moveCurrentPlayer(5);
        assertEquals(11, playerManager.getCurrentPlayer().getPosition());

        playerManager.moveCurrentPlayer(3);
        assertEquals(2, playerManager.getCurrentPlayer().getPosition());
    }

    public void testAdvanceToNextPlayerTurn() throws GameException {
        PlayerManager playerManager = new PlayerManager();
        playerManager.addPlayer("p1");
        playerManager.addPlayer("p2");
        playerManager.addPlayer("p3");

        assertEquals("p1", playerManager.getCurrentPlayer().getName());
        playerManager.advanceToNextPlayerTurn();
        assertEquals("p2", playerManager.getCurrentPlayer().getName());
        playerManager.advanceToNextPlayerTurn();
        assertEquals("p3", playerManager.getCurrentPlayer().getName());
        playerManager.advanceToNextPlayerTurn();
        assertEquals("p1", playerManager.getCurrentPlayer().getName());
    }

    public void testGetPlayerCount() throws GameException {
        PlayerManager playerManager = new PlayerManager();
        playerManager.addPlayer("p1");
        playerManager.addPlayer("p2");
        playerManager.addPlayer("p3");
        assertEquals(3, playerManager.getPlayerCount());

    }

    public void testDidPlayerWin() throws GameException {
        PlayerManager playerManager = new PlayerManager();
        playerManager.addPlayer("p1");
        playerManager.getCurrentPlayer().earnGold();
        assertFalse(playerManager.didPlayerWin());
        playerManager.getCurrentPlayer().earnGold();
        assertFalse(playerManager.didPlayerWin());
        playerManager.getCurrentPlayer().earnGold();
        assertFalse(playerManager.didPlayerWin());
        playerManager.getCurrentPlayer().earnGold();
        assertFalse(playerManager.didPlayerWin());
        playerManager.getCurrentPlayer().earnGold();
        assertFalse(playerManager.didPlayerWin());
        playerManager.getCurrentPlayer().earnGold();
        assertTrue(playerManager.didPlayerWin());
    }

    public void testGetCurrentPlayer() throws GameException {
        PlayerManager playerManager = new PlayerManager();
        playerManager.addPlayer("p1");
        playerManager.addPlayer("p2");
        playerManager.addPlayer("p3");
        assertEquals("p1", playerManager.getCurrentPlayer().getName());
    }
}