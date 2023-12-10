package ru.vsu.cs.oop.popova_p_n.task2.Piece;

import ru.vsu.cs.oop.popova_p_n.task2.Boards.Cell;
import ru.vsu.cs.oop.popova_p_n.task2.Boards.Direction;
import ru.vsu.cs.oop.popova_p_n.task2.Game.Game;
import ru.vsu.cs.oop.popova_p_n.task2.Moves.Move;
import ru.vsu.cs.oop.popova_p_n.task2.Players.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Queen {
    private final PieceLogic pieceLogic;

    public Queen(PieceLogic pieceLogic) {
        this.pieceLogic = pieceLogic;
    }

    /*
    Возможные ходы для дамок
     */
    public List<Move> availableSteps(Game game, Player player) {
        List<Piece> availablePieces = pieceLogic.availablePiece(game, player, PieceType.QUEEN);

        if (availablePieces.isEmpty()) {
            return Collections.emptyList();
        }

        List<Move> steps = new ArrayList<>();

        for (Piece piece : availablePieces) {
            Cell cell = game.getPieceCell().get(piece);
            Cell to = game.getPieceCell().get(piece);

            for (Direction direction : Direction.values()) {
                while (pieceLogic.checkNeighbourCell(game, direction, to)) {
                    to = to.getNeighbours().get(direction);
                    steps.add(new Move(cell, to, piece, player));
                }
            }
        }

        return steps;
    }


    /*
    проверка на наличие у дамки ходов, по которым она может сходить
     */
    protected boolean haveAvailableMoves(Piece piece, Game game) {
        Cell cell = game.getPieceCell().get(piece);
        for (Direction direction : Direction.values()) {
            if (pieceLogic.checkNeighbourCell(game, direction, cell)) {
                return true;
            }
        }
        return false;
    }

    /*
    может ли дамка побить какую-то фигуру
     */
    protected Direction beat(Piece piece, Player player, Game game) {
        Cell cell = game.getPieceCell().get(piece);

        for (Direction direction : cell.getNeighbours().keySet()) {
            while (cell.getNeighbours().containsKey(direction) && !game.getCellPiece().containsKey(cell.getNeighbours().get(direction))) {
                cell = cell.getNeighbours().get(direction);
            }

            Cell nextCell = cell.getNeighbours().get(direction);

            if (nextCell != null && game.getCellPiece().containsKey(nextCell)) {
                Piece enemyPiece = game.getCellPiece().get(nextCell);
                Player enemyPlayer = game.getPiecePlayerMap().get(enemyPiece);

                if (enemyPlayer != player && pieceLogic.checkNeighbourCell(game, direction, nextCell)) {
                    return direction;
                }
            }
        }

        return null;
    }


    /*
    ходы, которые можно сделать, побив фигуру противника
     */
    private List<Move> stepBeat(Piece piece, Player player, Game game, Direction direction) {
        List<Move> steps = new ArrayList<>();
        Cell from = game.getPieceCell().get(piece);
        Cell cell = from;
        while (pieceLogic.checkNeighbourCell(game, direction, cell)) {
            cell = cell.getNeighbours().get(direction);
        }
        if (cell.getNeighbours().containsKey(direction)) {
            Cell to = cell.getNeighbours().get(direction);
            while (pieceLogic.checkNeighbourCell(game, direction, to)) {
                to = to.getNeighbours().get(direction);
                steps.add(new Move(from, to, piece, player, game.getCellPiece().get(cell.getNeighbours().get(direction))));
            }
        }
        return steps;
    }

    /*
    Обязательные ходы для дамки
     */
    public List<Move> getNecessarySteps(Game game, Player player) {
        List<Piece> pieces = pieceLogic.listNecessarySteps(game, player, PieceType.QUEEN);
        List<Move> necessarySteps = new ArrayList<>();
        for (Piece piece : pieces) {
            necessarySteps.addAll(stepBeat(piece, player, game, beat(piece, player, game)));
        }
        return necessarySteps;
    }

}
