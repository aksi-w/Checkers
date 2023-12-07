package ru.vsu.cs.oop.popova_p_n.task2.Boards;

import ru.vsu.cs.oop.popova_p_n.task2.Piece.Piece;
import ru.vsu.cs.oop.popova_p_n.task2.Piece.PieceType;

import java.util.ArrayList;
import java.util.List;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cell {

    private final Map<Direction, Cell> neighbours;

    public Cell() {
        this.neighbours = new LinkedHashMap<>();
    }

    public Map<Direction, Cell> getNeighbours() {
        return neighbours;
    }
}
