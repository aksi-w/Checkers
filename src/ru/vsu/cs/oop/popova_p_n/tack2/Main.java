package ru.vsu.cs.oop.popova_p_n.tack2;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        System.out.println("Начальное состояние доски:");
        CheckersGame game = new CheckersGame(board);
        game.startGame();
    }
}
