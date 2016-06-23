package com.berkgokden.chess.pieces;

import com.berkgokden.chess.Piece;
import com.berkgokden.chess.PieceType;

/**
 * Chess Queen Piece.
 */
public class Queen extends Piece {

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }

    /**
     * The queen can be moved any number of unoccupied squares
     * in a straight line vertically, horizontally, or diagonally,
     * thus combining the moves of the rook and bishop.
     * (Source: https://en.wikipedia.org/wiki/Queen_(chess) )
     * @param piece other piece object to check against
     * @return true if this does not threaten other piece
     */
    @Override
    public boolean isSafe(Piece piece) {
        if (piece.getX() == getX()
                || piece.getY() == getY()
                || Math.abs(piece.getX()-getX()) == Math.abs(piece.getY() - getY()) ) {
            return false;
        }
        return true;
    }
}
