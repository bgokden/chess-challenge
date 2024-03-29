package com.berkgokden.chess.pieces;

import com.berkgokden.chess.Piece;
import com.berkgokden.chess.PieceType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the methods of King Piece
 */
public class KingTest {

    @Test
    public void shouldPassWhenPieceTypeReturnsAsKing() throws Exception {
        assertEquals(PieceType.KING, new King().getType());
    }

    @Test
    public void shouldPassWhentIsSafeWorksCorrectly() throws Exception {
        Piece piece = new King();
        piece.setX(1);
        piece.setY(1);

        Piece piece1 = new DummyPiece();

        // Diagonally not safe
        piece1.setX(0);
        piece1.setY(0);

        assertFalse(piece.isSafe(piece1));

        // Hozizontally not safe
        piece1.setX(0);
        piece1.setY(1);

        assertFalse(piece.isSafe(piece1));

        // Safe position
        piece1.setX(3);
        piece1.setY(0);

        assertTrue(piece.isSafe(piece1));
    }
}