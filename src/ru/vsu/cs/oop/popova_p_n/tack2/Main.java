package ru.vsu.cs.oop.popova_p_n.tack2;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        System.out.println("Начальное состояние доски:");
        board.displayBoard();

        int startI = 2;
        int startJ = 1;
        int endI = 3;
        int endJ = 2;
        board.makeMove(startI, startJ, endI, endJ);

        System.out.println("Состояние доски после хода:");
        board.displayBoard();
    }
}
