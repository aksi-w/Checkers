package ru.vsu.cs.oop.popova_p_n.task2.Moves;

import ru.vsu.cs.oop.popova_p_n.task2.Boards.Cell;
import ru.vsu.cs.oop.popova_p_n.task2.Piece.Piece;
import ru.vsu.cs.oop.popova_p_n.task2.Players.Player;

public class Move {

    private Cell from;

    private Cell to;

    private Piece piece;

    private Player player;

    private Piece beatPiece = null;

    public Move(Cell from, Cell to, Piece piece, Player player) {
        this.from = from;
        this.to = to;
        this.piece = piece;
        this.player = player;
    }
    public Move(Cell from, Cell to, Piece piece, Player player, Piece beatPiece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
        this.player = player;
        this.beatPiece = beatPiece;
    }

    public Cell getFrom() {
        return from;
    }

    public Cell getTo() {
        return to;
    }

    public Piece getPiece() {
        return piece;
    }


    public Player getPlayer() {
        return player;
    }


    public Piece getBeatPiece() {
        return beatPiece;
    }

}
