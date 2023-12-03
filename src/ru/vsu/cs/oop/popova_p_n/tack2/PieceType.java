package ru.vsu.cs.oop.popova_p_n.tack2;

public enum PieceType {

    EMPTY("."),
    BLACK("B"),
    WHITE("W"),
    WHITE_QUEEN("WQ"),
    BLACK_QUEEN("BQ");

    private String label;

    public String getLabel() {
        return label;
    }

    private PieceType(String label) {
        this.label = label;
    }
}


