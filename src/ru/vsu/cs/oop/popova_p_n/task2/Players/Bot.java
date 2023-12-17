package ru.vsu.cs.oop.popova_p_n.task2.Players;

import ru.vsu.cs.oop.popova_p_n.task2.Game.Game;
import ru.vsu.cs.oop.popova_p_n.task2.Moves.Move;
import ru.vsu.cs.oop.popova_p_n.task2.Moves.MoveHistory;
import ru.vsu.cs.oop.popova_p_n.task2.Piece.PieceLogic;

import java.util.List;

public class Bot {
    private final PieceLogic piece = new PieceLogic();
    private final MoveHistory moveHistory;

    public Bot(MoveHistory moveHistory) {
        this.moveHistory = moveHistory;
    }

    public boolean makeMove(Player player, Game game) {
        boolean beat = false;
        List<Move> steps = piece.necessarySteps(game, player);
        if (!steps.isEmpty()) {
            Move step = steps.get((int) (Math.random() * (steps.size() - 1)));
            beat = true;
            doStep(step, game, beat);
            steps = piece.necessarySteps(game, player);
            while (!steps.isEmpty()) {
                step = steps.get((int) (Math.random() * (steps.size() - 1)));
                doStep(step, game, beat);
                steps = piece.necessarySteps(game, player);
            }
        } else {
            List<Move> availableSteps = piece.availableSteps(game, player);
            if (availableSteps.isEmpty()) {
                System.out.println("У Игрока " + player.getName() + " не осталось ходов");
                return false;
            }
            Move step = availableSteps.get((int) (Math.random() * (availableSteps.size() - 1)));
            doStep(step, game, beat);
        }
        return true;
    }

    private void doStep(Move step, Game game, boolean beat) {
        game.getCellPiece().remove(step.getFrom());
        game.getCellPiece().put(step.getTo(), step.getPiece());
        game.getPieceCell().put(step.getPiece(), step.getTo());
        if (piece.checkQueen(step.getPlayer(), step.getTo(), game)) {
            piece.makeQueen(step.getPiece());
        }
        if (beat) {
            beat(step, game);
            // Добавляем ход в стек
            moveHistory.addMove(step);
        }
    }

    private void beat(Move step, Game game) {
        game.getCellPiece().remove(game.getPieceCell().get(step.getBeatPiece()));
        game.getPieceCell().remove(step.getBeatPiece());
        game.getPlayerPiece().get(game.getPiecePlayerMap().get(step.getBeatPiece())).remove(step.getBeatPiece());
        game.getPiecePlayerMap().remove(step.getBeatPiece());
    }
    public MoveHistory getMoveHistory() {
        return moveHistory;
    }
}
