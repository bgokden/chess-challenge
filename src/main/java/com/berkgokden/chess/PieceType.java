package com.berkgokden.chess;

import com.berkgokden.chess.pieces.*;

/**
 * Piece Enum Type to be used in EnumMap
 */
public enum PieceType {
    KING,QUEEN,ROOK,BISHOP,KNIGHT;

    /**
     * A helper class to easy print a piece on board
     * @param pieceType enum type to define Piece
     * @return char a capital letter representing Piece
     */
    public static char getLetter(PieceType pieceType) {
        switch (pieceType) {
            case KING:
                return 'K';
            case QUEEN:
                return 'Q';
            case ROOK:
                return 'R';
            case BISHOP:
                return 'B';
            case KNIGHT:
                return 'N';
            default:
                return 0;
        }
    }

    public static Piece getInstance(PieceType pieceType) {
        switch (pieceType) {
            case KING:
                return new King();
            case QUEEN:
                return new Queen();
            case ROOK:
                return new Rook();
            case BISHOP:
                return new Bishop();
            case KNIGHT:
                return new Knight();
            default:
                return null;
        }
    }
}
