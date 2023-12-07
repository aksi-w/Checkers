package ru.vsu.cs.oop.popova_p_n.task2.Game;

import ru.vsu.cs.oop.popova_p_n.task2.Boards.Cell;
import ru.vsu.cs.oop.popova_p_n.task2.Boards.Direction;
import ru.vsu.cs.oop.popova_p_n.task2.Piece.Piece;
import ru.vsu.cs.oop.popova_p_n.task2.Piece.PieceType;
import ru.vsu.cs.oop.popova_p_n.task2.Players.Player;

import java.util.*;

public class Game {
    private Cell rightUpCell;
    private Cell leftDownCell;
    private List<List<Cell>> boardPainting;
    private Queue<Player> players;

    private final Map<Player, Map<PieceType, String>> visualPiece;
    private Map<Player, List<Piece>> playerPiece;
    private Map<Piece, Player> piecePlayerMap;
    private Map<Player, List<Direction>> availableDirections;

    private Map<Cell, Piece> cellPiece;
    private Map<Piece, Cell> pieceCell;


    private Game() {
        players = new LinkedList<>();
        visualPiece = new HashMap<>();
    }

    public Cell getRightUpCell() {
        return rightUpCell;
    }

    public void setRightUpCell(Cell leftUpCell) {
        this.rightUpCell = leftUpCell;
    }

    public Cell getLeftDownCell() {
        return leftDownCell;
    }

    public void setLeftDownCell(Cell leftDownCell) {
        this.leftDownCell = leftDownCell;
    }

    public List<List<Cell>> getBoardPainting() {
        return boardPainting;
    }

    public void setBoardPainting(List<List<Cell>> boardPainting) {
        this.boardPainting = boardPainting;
    }

    public Map<Player, Map<PieceType, String>> getVisualPiece() {
        return visualPiece;
    }

    public Map<Player, List<Piece>> getPlayerPiece() {
        return playerPiece;
    }

    public Map<Piece, Player> getPiecePlayerMap() {
        return piecePlayerMap;
    }

    public void setPiecePlayerMap(Map<Piece, Player> piecePlayerMap) {
        this.piecePlayerMap = piecePlayerMap;
    }

    public void setPlayerPiece(Map<Player, List<Piece>> playerPiece) {
        this.playerPiece = playerPiece;
    }

    public Map<Player, List<Direction>> getAvailableDirections() {
        return availableDirections;
    }

    public void setAvailableDirections(Map<Player, List<Direction>> availableDirections) {
        this.availableDirections = availableDirections;
    }

    public Map<Cell, Piece> getCellPiece() {
        return cellPiece;
    }

    public void setCellPiece(Map<Cell, Piece> cellPiece) {
        this.cellPiece = cellPiece;
    }

    public Map<Piece, Cell> getPieceCell() {
        return pieceCell;
    }

    public void setPieceCell(Map<Piece, Cell> pieceCell) {
        this.pieceCell = pieceCell;
    }


    public Queue<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Queue<Player> players) {
        this.players = players;
    }

    public static Game createGame() {
        return new Game();
    }
}