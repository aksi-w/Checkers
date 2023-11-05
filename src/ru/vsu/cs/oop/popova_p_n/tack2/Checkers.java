package ru.vsu.cs.oop.popova_p_n.tack2;

public class Checkers {
    private final Piece[][] board;
    static final int rows = 8;
    static final int cols = 8;

    public Checkers() {
        board = new Piece[rows][cols];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((i + j) % 2 == 0) {
                    if (i < 3) {
                        board[i][j] = new Piece(PieceType.BLACK, i, j);
                    } else if (i > 4) {
                        board[i][j] = new Piece(PieceType.WHITE, i, j);
                    } else {
                        board[i][j] = new Piece(PieceType.EMPTY,i,j);
                    }
                } else {
                    board[i][j] = new Piece(PieceType.EMPTY,i,j);
                }
            }
        }
    }

}
