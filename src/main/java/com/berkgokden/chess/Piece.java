package com.berkgokden.chess;

/**
 * Piece abstract class is a base class for all types of pieces.
 * Every piece has common x,y, type variables and isSafe method.
 */
public abstract class Piece implements Comparable<Piece> {
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
     * @param piece other piece object to check against
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
     * @return Type name, x, y
     */
    @Override
    public String toString() {
        return getType().name()+","+x+","+y;
    }

    /**
     * Compare two pieces by
     * First type ordinance
     * Second position in x direction
     * Third position in y direction
     * Required for Collection.sort.
     * Any consistent sorting method would work.
     * @param o other piece to compare
     * @return 1 if bigger, 0 if equal, -1 is less than this piece.
     */
    @Override
    public int compareTo(Piece o) {
        if ( this.getType().ordinal() > o.getType().ordinal()) {
            return 1;
        } else if ( this.getType().ordinal() < o.getType().ordinal()) {
            return -1;
        }else if ( this.x > o.getX()) {
            return 1;
        } else if ( this.x < o.getX()) {
            return -1;
        }else if ( this.y > o.getY()) {
            return 1;
        } else if ( this.y < o.getY()){
            return -1;
        }
        return 0;
    }

    /**
     * Checks equality to other object or piece.
     * @param obj other object to check
     * @return true if this object is same as other object or
     * This piece's type, x position and y position are equal to other piece.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Piece) {
            Piece piece = (Piece) obj;
            if (getType().ordinal() == piece.getType().ordinal()
                    && x == piece.getX()
                    && y == piece.getY()) {
                return true;
            }
        }
        return super.equals(obj);
    }

    /**
     * HashCode implentation to be used in HashMaps.
     * This method added since equal method is overridden.
     * Equals method and hashcode has a contract and must return consistent values.
     * @return hash code using toString implementation
     */
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
