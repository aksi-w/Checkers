package ru.vsu.cs.oop.popova_p_n.task2.Moves;

import java.util.Stack;

public class MoveHistory {
    private final Stack<Move> moveStack;

    public MoveHistory() {
        this.moveStack = new Stack<>();
    }

    public void addMove(Move move) {
        moveStack.push(move);
    }

    public Move otkatMove() {
        if (!moveStack.isEmpty()) {
            return moveStack.pop();
        }
        return null;
    }

}
