package com.berkgokden.chess;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Since Piece is an abstract Class
 * Piece class will be tested with DummyPiece
 */
public class PieceTest {

    @Test
    public void shouldPassWhenGetSetXYCorrectly() throws Exception {
        Piece piece0 = new DummyPiece();
        piece0.setX(3);
        piece0.setY(4);
        assertEquals(3, piece0.getX());
        assertEquals(4, piece0.getY());
    }

    @Test
    public void shouldPassWhenIsSafeWorksCorrectly() throws Exception {
        Piece piece0 = new DummyPiece();
        piece0.setX(0);
        piece0.setY(0);

        Piece piece1 = new DummyPiece();
        piece1.setX(0);
        piece1.setY(0);

        // pieces are at the same position
        assertFalse(piece0.isSafe(piece1));

        piece1.setX(1);
        piece1.setY(0);
        // pieces are at different positions
        assertTrue(piece0.isSafe(piece1));
        assertTrue(piece1.isSafe(piece0));

        assertTrue(piece0.isPossibleToPlace(piece1));
    }

}