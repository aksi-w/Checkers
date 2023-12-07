package ru.vsu.cs.oop.popova_p_n.task2.Moves;

import ru.vsu.cs.oop.popova_p_n.task2.Boards.Cell;
import ru.vsu.cs.oop.popova_p_n.task2.Piece.Piece;
import ru.vsu.cs.oop.popova_p_n.task2.Players.Player;

public class Move {

    private Cell from;

    private Cell to;

    private Piece figure;

    private Player player;

    private Piece beatenFigure = null;

    public Move(Cell from, Cell to, Piece figure, Player player) {
        this.from = from;
        this.to = to;
        this.figure = figure;
        this.player = player;
    }
    public Move(Cell from, Cell to, Piece figure, Player player, Piece beatenFigure) {
        this.from = from;
        this.to = to;
        this.figure = figure;
        this.player = player;
        this.beatenFigure=beatenFigure;
    }

    public Cell getFrom() {
        return from;
    }

    public Cell getTo() {
        return to;
    }

    public Piece getFigure() {
        return figure;
    }


    public Player getPlayer() {
        return player;
    }


    public Piece getBeatenFigure() {
        return beatenFigure;
    }

}
