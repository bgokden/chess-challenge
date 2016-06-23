package com.berkgokden.chess.pieces;

import com.berkgokden.chess.Piece;
import com.berkgokden.chess.PieceType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the methods of Bishop Piece.
 */
public class BishopTest {

    @Test
    public void shouldPassWhenPieceTypeReturnsAsBishop() throws Exception {
        assertEquals(PieceType.BISHOP, new Bishop().getType());
    }

    @Test
    public void shouldPassWhentIsSafeWorksCorrectly() throws Exception {
        Piece piece = new Bishop();
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

        // Diagonally not safe
        piece1.setX(4);
        piece1.setY(4);

        assertFalse(piece.isSafe(piece1));

        piece1.setX(0);
        piece1.setY(4);

        assertFalse(piece.isSafe(piece1));

        // Random Safe position
        piece1.setX(3);
        piece1.setY(4);

        assertTrue(piece.isSafe(piece1));
    }
}