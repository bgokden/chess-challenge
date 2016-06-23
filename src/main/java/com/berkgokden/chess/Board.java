package com.berkgokden.chess;

import java.util.*;

/**
 * Board class defines a chessboard with custom height and width
 * Board also handles safely placing pieces
 */
public class Board {
    private int width;
    private int height;
    private List<Piece> pieces;

    /**
     * A helper structure to speed up search method.
     * This map keeps already searched locations in the map
     * for each piece type.
     */
    private Map<PieceType,Location> lastPositions;

    /**
     * Default miminum constructor for board
     * @param width size of board in x direction ( x > 0)
     * @param height size of board in y direction (y > 0)
     */
    public Board(int width, int height) {
        this(width, height, 5);
    }

    /**
     * Constructor for board if number of pieces known beforehand
     * @param width size of board in x direction ( x > 0)
     * @param height size of board in y direction (y > 0)
     * @param numberOfPieces number of pieces that would be placed
     */
    public Board(int width, int height, int numberOfPieces) {
        if (width <= 0)
            throw new IllegalArgumentException("Board width should be larger than 0.");
        if (height <= 0)
            throw new IllegalArgumentException("Board height should be larger than 0.");
        this.width = width;
        this.height = height;
        this.pieces = new ArrayList<>(numberOfPieces);
        this.lastPositions = initialLastPositions();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Places a piece into the first available square on board.
     * Checks piece type and searches for a safe place in row by row
     *
     * @param piece piece to be positioned on the board
     * @param initialX hinted starting point in X direction (initial X > 0)
     * @param initialY hinted starting point in Y direction (initial Y > 0)
     * @return
     */
    public boolean putPieceToFirstAvailableSquare(Piece piece, int initialX, int initialY) {
        if (initialX < 0)
            throw new IllegalArgumentException("Initial X should be larger than 0");
        if (initialY < 0)
            throw new IllegalArgumentException("Initial Y should be larger than 0");
        boolean found = false;
        Location location = lastPositions.get(piece.getType());
        initialX = Math.max(initialX, location.getX());
        initialY = Math.max(initialY, location.getY());
        for (int y = initialY; y < this.height; y++) {
            for (int x = initialX; x < this.width; x++) {
                boolean moveToNext = false;
                piece.setX(x);
                piece.setY(y);
                for (Piece element : pieces) {
                    if (!element.isPossibleToPlace(piece)) {
                        moveToNext = true;
                        break;
                    }
                }
                if (moveToNext) {
                    continue;
                }
                found = true;
                location.setX(x);
                location.setY(y);
                lastPositions.put(piece.getType(), location);
                pieces.add(piece);
                break;
            }
            if (found) {
                break;
            }
            initialX = 0;
        }
        if (!found) {
            location.setX(this.width);
            location.setY(this.height);
            lastPositions.put(piece.getType(), location);
        }
        return found;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    /**
     * Initilize a map to piece type to location as 0,0
     * Saving last known safe safe locations significantly improve searching
     * @return map to piece type version known last safe locations
     */
    private Map<PieceType, Location> initialLastPositions() {
        Map<PieceType, Location> map = new EnumMap<>(PieceType.class);
        map.put(PieceType.KING, new Location(0, 0));
        map.put(PieceType.QUEEN, new Location(0, 0));
        map.put(PieceType.ROOK, new Location(0, 0));
        map.put(PieceType.BISHOP, new Location(0, 0));
        map.put(PieceType.KNIGHT, new Location(0, 0));
        return map;
    }

    /**
     * A simple printing method to print Board to console
     */
    public void prettyPrint() {
        System.out.println("--Board of "+width+"x"+height+"--");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char letter = 0;
                for (Piece piece : pieces) {
                    if (piece.getX() == x && piece.getY() == y) {
                        letter = PieceType.getLetter(piece.getType());
                        break;
                    }
                }
                if (letter != 0) {
                    System.out.print(letter);
                } else {
                    System.out.print("_");
                }
            }
            System.out.println();
        }
    }

    public Map<PieceType, Location> getLastPositions() {
        return lastPositions;
    }

}
