package com.berkgokden.chess.pieces;

import com.berkgokden.chess.Piece;
import com.berkgokden.chess.PieceType;

/**
 * Chess Bishop Piece.
 */
public class Bishop extends Piece {

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }

    /**
     * The bishop has no restrictions in distance for each move,
     * but is limited to diagonal movement.
     * (Source: https://en.wikipedia.org/wiki/Bishop_(chess) )
     * @param piece other piece object to check against
     * @return true if this does not threaten other piece
     */
    @Override
    public boolean isSafe(Piece piece) {
        if (Math.abs(piece.getX() - getX()) == Math.abs(piece.getY() - getY()) ) {
            return false;
        }
        return true;
    }
}
