package ru.vsu.cs.oop.popova_p_n.task2.Moves;

import ru.vsu.cs.oop.popova_p_n.task2.Boards.Cell;
import ru.vsu.cs.oop.popova_p_n.task2.Boards.Direction;
import ru.vsu.cs.oop.popova_p_n.task2.Game.Game;
import ru.vsu.cs.oop.popova_p_n.task2.Piece.Piece;
import ru.vsu.cs.oop.popova_p_n.task2.Piece.PieceLogic;
import ru.vsu.cs.oop.popova_p_n.task2.Piece.PieceType;
import ru.vsu.cs.oop.popova_p_n.task2.Players.Player;

import java.util.ArrayList;
import java.util.List;

public class CheckersGame {
    private final PieceLogic piece;

    public CheckersGame(PieceLogic piece) {
        this.piece = piece;
    }

    //Получение возможных шагов для всех шашек игрока
    public List<Move> getAvailableSteps(Game game, Player player) {
        List<Piece> availablePieces = piece.availablePiece(game, player, PieceType.PAWN);
        List<Move> steps = new ArrayList<>();

        for (Piece pieces : availablePieces) {
            if (pieces.getType() == PieceType.PAWN) {
                Cell cell = game.getPieceCell().get(pieces);

                for (Direction direction : game.getAvialableDirections().get(player)) {
                    Cell nextCell = cell.getNeighbours().get(direction);

                    if (nextCell != null && !game.getCellPiece().containsKey(nextCell)) {
                        steps.add(new Move(cell, nextCell, pieces, player));
                    }
                }
            }
        }

        return steps;
    }

    public boolean haveAvailableMoves(Piece piece, Game game, Player player) {
        Cell cell = game.getPieceCell().get(piece);
        for (Direction direction : game.getAvialableDirections().get(player)) {
            if (this.piece.checkNeighbourCell(game, direction, cell)) {
                return true;
            }
        }
        return false;
    }

    public Direction beat(Piece piece, Player player, Game game) {
        Cell cell = game.getPieceCell().get(piece);
        for (Direction direction : cell.getNeighbours().keySet()) {
            Cell nextCell = cell.getNeighbours().get(direction);
            if (game.getCellPiece().containsKey(nextCell) &&
                    game.getPiecePlayerMap().get(game.getCellPiece().get(nextCell)) != player &&
                    this.piece.checkNeighbourCell(game, direction, nextCell)) {
                return direction;
            }

        }
        return null;
    }

    private Move stepBeat(Piece piece, Player player, Game game, Direction direction) {
        Cell cell = game.getPieceCell().get(piece);
        return new Move(cell, cell.getNeighbours().get(direction).getNeighbours().get(direction), piece, player,
                game.getCellPiece().get(cell.getNeighbours().get(direction)));
    }

    public List<Move> getSteps(Game game, Player player) {
        List<Piece> pieces = piece.listNecessarySteps(game, player, PieceType.PAWN);
        List<Move> necessarySteps = new ArrayList<>();
        for (Piece piece1 : pieces) {
            necessarySteps.add(stepBeat(piece1, player, game, beat(piece1, player, game)));
        }
        return necessarySteps;
    }
}