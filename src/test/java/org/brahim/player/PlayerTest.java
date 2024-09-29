package org.brahim.player;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {

    public void testMoveForward() {
        Player p1 = new Player("p1");
        assertEquals(0, p1.getPosition());
        int boardSize = 12;
        p1.moveForward(5, boardSize);
        assertEquals(5, p1.getPosition());
        p1.moveForward(5, boardSize);
        assertEquals(10, p1.getPosition());
        p1.moveForward(5, boardSize);
        assertEquals(3, p1.getPosition());
    }

    public void testFreeFromPenaltyBox() {
        Player p1 = new Player("p1");
        p1.moveToPenaltyBox();
        assertTrue(p1.isInPenaltyBox());
        p1.freeFromPenaltyBox();
        assertFalse(p1.isInPenaltyBox());
    }

    public void testMoveToPenaltyBox() {
        Player p1 = new Player("p1");
        p1.moveToPenaltyBox();
        assertTrue(p1.isInPenaltyBox());
    }

    public void testEarnGold() {
        Player p1 = new Player("p1");
        p1.earnGold();
        assertEquals(1, p1.getGoldCoins());
        p1.earnGold();
        assertEquals(2, p1.getGoldCoins());
    }
}