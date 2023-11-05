package ru.vsu.cs.oop.popova_p_n.tack2;

public class Piece {

    private PieceType type;
    private int row;
    private int col;

    public Piece(PieceType type, int row, int col) {
        this.type = type;
        this.row = row;
        this.col = col;
    }

    public PieceType getType() {
        return type;
    }

    public String getDisplaySymbol() {
        if (type == PieceType.BLACK) {
            return "B";
        } else if (type == PieceType.WHITE) {
            return "W";
        } else {
            return " ";
        }
    }
}
