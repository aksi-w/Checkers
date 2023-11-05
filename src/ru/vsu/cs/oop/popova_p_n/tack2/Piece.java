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

    public void setType(PieceType type) {
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
