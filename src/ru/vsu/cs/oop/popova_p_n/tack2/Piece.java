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



    public void updatePosition(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
    }

    @Override
    public String toString() {
        return type.getLabel();
    }
}
