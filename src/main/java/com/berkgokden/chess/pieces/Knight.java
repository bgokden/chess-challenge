package com.berkgokden.chess.pieces;

import com.berkgokden.chess.Piece;
import com.berkgokden.chess.PieceType;

/**
 * Chess Knight Piece.
 */
public class Knight extends Piece {

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }

    /**
     * The knight move is unusual among chess pieces.
     * When it moves, it can move to a square that is
     * two squares horizontally and one square vertically,
     * or two squares vertically and one square horizontally.
     * (Source: https://en.wikipedia.org/wiki/Knight_(chess) )
     * @param piece other piece object to check against
     * @return true if this does not threaten other piece
     */
    @Override
    public boolean isSafe(Piece piece) {
        int xDistance = Math.abs(piece.getX() - getX());
        int yDistance = Math.abs(piece.getY() - getY());
        if ((xDistance == 1 && yDistance == 2)
                || (xDistance == 2 && yDistance == 1)
                || (xDistance == 0 && yDistance == 0)) {
            return false;
        }
        return true;
    }
}
