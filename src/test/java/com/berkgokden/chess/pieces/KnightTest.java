package com.berkgokden.chess.pieces;

import com.berkgokden.chess.Piece;
import com.berkgokden.chess.PieceType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the methods of Knight Piece.
 */
public class KnightTest {

    @Test
    public void shouldPassWhenPieceTypeReturnsAsKnight() throws Exception {
        assertEquals(PieceType.KNIGHT, new Knight().getType());
    }

    @Test
    public void shouldPassWhentIsSafeWorksCorrectly() throws Exception {
        Piece piece = new Knight();
        piece.setX(2);
        piece.setY(2);

        // Vertically safe
        Piece piece1 = new DummyPiece();
        piece1.setX(2);
        piece1.setY(0);

        assertTrue(piece.isSafe(piece1));

        // Horizontally safe
        piece1.setX(0);
        piece1.setY(2);

        assertTrue(piece.isSafe(piece1));

        // Diagonally safe
        piece1.setX(4);
        piece1.setY(4);

        assertTrue(piece.isSafe(piece1));

        piece1.setX(0);
        piece1.setY(4);

        assertTrue(piece.isSafe(piece1));

        // L shape not safe
        piece1.setX(3);
        piece1.setY(4);

        assertFalse(piece.isSafe(piece1));

        // L shape not safe
        piece1.setX(1);
        piece1.setY(4);

        assertFalse(piece.isSafe(piece1));
    }
}