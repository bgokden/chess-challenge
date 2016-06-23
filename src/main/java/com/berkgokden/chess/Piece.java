package com.berkgokden.chess;

/**
 * Piece abstract class is a base class for all types of pieces.
 * Every piece has common x,y, type variables and isSafe method.
 */
public abstract class Piece{
    protected int x;
    protected int y;

    /**
     * Piece tye used in EnumMaps
     * @return piece Type for extending class
     */
    public abstract PieceType getType();

    /**
     * Used by board when placing a piece
     * @param piece other piece object to check against
     * @return true if this piece does not threaten other piece
     */
    public abstract boolean isSafe(Piece piece);

    /**
     * Mutual checking the piece positions
     * @param piece other piece object to check againts
     * @return true if this piece and other piece do not threaten each other
     */
    public boolean isPossibleToPlace(Piece piece) {
        return isSafe(piece) && piece.isSafe(this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < 0)
            throw new IllegalArgumentException("X should be positive");
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y < 0)
            throw new IllegalArgumentException("Y should be positive");
        this.y = y;
    }

    /**
     * This toString method implemented for easier debugging
     * @return Type name , x, y
     */
    @Override
    public String toString() {
        return getType().name()+","+x+","+y;
    }
}
