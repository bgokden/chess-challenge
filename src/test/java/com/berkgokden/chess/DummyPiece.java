package com.berkgokden.chess;

/**
 * Dummy Piece moking as King to used in Tests.
 */
public class DummyPiece extends Piece {
    @Override
    public PieceType getType() {
        return PieceType.KING;
    }

    @Override
    public boolean isSafe(Piece piece) {
        // Piece can not be on the same position
        if (piece.getX() == x && piece.getY() == y) {
            return false;
        }
        return true;
    }
}
