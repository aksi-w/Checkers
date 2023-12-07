package ru.vsu.cs.oop.popova_p_n.task2.Piece;

public class Piece {
    private PieceType type;

    public Piece(PieceType type) {
        this.type = type;
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }
}
