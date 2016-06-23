package com.berkgokden.chess.pieces;

import com.berkgokden.chess.Piece;
import com.berkgokden.chess.PieceType;

/**
 * Chess King Piece
 */
public class King extends Piece {

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }

    /**
     * A king can move one square in any direction
     * (horizontally, vertically, or diagonally)
     * unless the square is already occupied by a friendly piece
     * @param piece other piece object to check against
     * @return true if this does not threaten other piece
     */
    @Override
    public boolean isSafe(Piece piece) {
        if (Math.abs(piece.getX() - getX()) <= 1
                && Math.abs(piece.getY() - getY()) <= 1 ) {
            return false;
        }
        return true;
    }
}
