package ru.vsu.cs.oop.popova_p_n.task2.Piece;

import ru.vsu.cs.oop.popova_p_n.task2.Boards.Cell;
import ru.vsu.cs.oop.popova_p_n.task2.Boards.Direction;
import ru.vsu.cs.oop.popova_p_n.task2.Game.Game;
import ru.vsu.cs.oop.popova_p_n.task2.Moves.CheckersGame;
import ru.vsu.cs.oop.popova_p_n.task2.Moves.Move;
import ru.vsu.cs.oop.popova_p_n.task2.Players.Player;

import java.util.ArrayList;
import java.util.List;

public class PieceLogic {
    private final CheckersGame checkers = new CheckersGame(this);
    private final Queen queen = new Queen(this);

    public boolean checkQueen(Player player, Cell cell, Game game) {
        List<Direction> directions = game.getAvialableDirections().get(player);
        for (Direction d : directions) {
            Cell next = cell.getNeighbours().get(d);
            if (next != null) {
                return false;
            }
        }
        return true;
    }

    public void makeQueen(Piece piece) {
        piece.setType(PieceType.QUEEN);
    }

    protected boolean haveAvailableMoves(Piece piece, Game game, Player player) {
        if (piece.getType() == PieceType.QUEEN) {
            return queen.haveAvailableMoves(piece, game);
        } else {
            return checkers.haveAvailableMoves(piece, game, player);
        }
    }


    public List<Move> necessarySteps(Game game, Player player) {
        List<Move> necessarySteps = new ArrayList<>();
        necessarySteps.addAll(queen.getNecessarySteps(game, player));
        necessarySteps.addAll(checkers.getSteps(game, player));
        return necessarySteps;
    }

    public List<Move> availableSteps(Game game, Player player) {
        List<Move> availableStep = new ArrayList<>();
        availableStep.addAll(checkers.getAvailableSteps(game, player));
        availableStep.addAll(queen.availableSteps(game, player));
        return availableStep;
    }

    public List<Piece> availablePiece(Game game, Player player, PieceType pieceType) {
        List<Piece> pieceColor = game.getPlayerPiece().get(player);
        List<Piece> result = new ArrayList<>();
        for (Piece piece : pieceColor) {
            if (haveAvailableMoves(piece, game, player) && piece.getType() == pieceType) {
                result.add(piece);
            }
        }
        return result;
    }

    public List<Piece> listNecessarySteps(Game game, Player player, PieceType pieceType) {
        List<Piece> allPieces = game.getPlayerPiece().get(player);
        List<Piece> result = new ArrayList<>();

        for (Piece piece : allPieces) {
            boolean hasNecessaryMove = piece.getType() == pieceType &&
                    (pieceType == PieceType.QUEEN ? queen.beat(piece, player, game) != null : checkers.beat(piece, player, game) != null);

            if (hasNecessaryMove) {
                result.add(piece);
            }
        }

        return result;
    }


    public boolean checkNeighbourCell(Game game, Direction direction, Cell cell) {
        return cell.getNeighbours().containsKey(direction) &&
                !game.getCellPiece().containsKey(cell.getNeighbours().get(direction));
    }
}
