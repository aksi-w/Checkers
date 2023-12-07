package ru.vsu.cs.oop.popova_p_n.task2.Boards;

public interface Boardable {
    public void displayBoard();
    public boolean isValidMove();
    public void removePiece(int x, int y);
}
