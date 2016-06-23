package com.berkgokden.chess.pieces;

import com.berkgokden.chess.Piece;
import com.berkgokden.chess.PieceType;

/**
 * Chess Rook Piece.
 */
public class Rook extends Piece {

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }

    /**
     * The rook moves horizontally or vertically,
     * through any number of unoccupied squares.
     * (Source: https://en.wikipedia.org/wiki/Rook_(chess) )
     * @param piece other piece object to check against
     * @return true if this does not threaten other piece
     */
    @Override
    public boolean isSafe(Piece piece) {
        if (piece.getX() == getX()
                || piece.getY() == getY()) {
            return false;
        }
        return true;
    }
}
