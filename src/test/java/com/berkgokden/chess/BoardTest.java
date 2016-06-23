package com.berkgokden.chess;

import com.berkgokden.chess.pieces.DummyPiece;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test methods for Board Class
 */
public class BoardTest {

    @Test
    public void shouldPassWhenWidthSetCorrectly() throws Exception {
        Board board = new Board(3, 4);
        assertEquals(3, board.getWidth());
        Board board1 = new Board(3, 4, 5);
        assertEquals(3, board1.getWidth());
    }

    @Test (expected=IllegalArgumentException.class)
    public void shouldThrowExceptionWhenWidthIsLessThanZero() throws Exception {
        Board board = new Board(-3, 4);
    }

    @Test
    public void shouldPassWhenHeightSetCorrectly() throws Exception {
        Board board = new Board(3, 4);
        assertEquals(4, board.getHeight());
        Board board1 = new Board(3, 4, 5);
        assertEquals(4, board1.getHeight());
    }

    @Test (expected=IllegalArgumentException.class)
    public void shouldThrowExceptionWhenHeightLessThanZero() throws Exception {
        Board board = new Board(3, -4);
    }

    @Test
    public void shouldPositionDummyPieceInEmeptySquaresCorrectly() throws Exception {
        Board board = new Board(3,3);
        assertTrue(board.putPieceToFirstAvailableSquare(new DummyPiece(), 0, 0));
        assertEquals(1, board.getPieces().size());
        Piece piece = board.getPieces().get(0);
        assertNotEquals(null, piece);
        assertEquals(PieceType.KING, piece.getType());
        assertEquals(0, piece.getX());
        assertEquals(0, piece.getY());
        board.prettyPrint();
        assertTrue(board.putPieceToFirstAvailableSquare(new DummyPiece(), 0, 0));
        assertEquals(2, board.getPieces().size());
        Piece piece1 = board.getPieces().get(1);
        assertNotEquals(null, piece1);
        board.prettyPrint();
        assertEquals(PieceType.KING, piece1.getType());
        assertEquals(1, piece1.getX());
        assertEquals(0, piece1.getY());
    }

}