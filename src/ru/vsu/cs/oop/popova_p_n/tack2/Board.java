package ru.vsu.cs.oop.popova_p_n.tack2;

public class Board {
    private final Piece[][] board;
    static final int rows = 8;
    static final int cols = 8;

    public Board() {
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
                        board[i][j] = new Piece(PieceType.EMPTY, i, j);
                    }
                } else {
                    board[i][j] = new Piece(PieceType.EMPTY, i, j);
                }
            }
        }
    }

    public void displayBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j].getDisplaySymbol() + " ");
            }
            System.out.println();
        }
    }


    public void makeMove(int startI, int startJ, int endI, int endJ) {
        Piece startPiece = board[startI][startJ];
        Piece endPiece = board[endI][endJ];

        if (startPiece.getType() == PieceType.EMPTY) {
            System.out.println("Ход соответвует правилам игры");
            return;
        }

        if (endPiece.getType() != PieceType.EMPTY) {
            System.out.println("Конечная клетка не пуста");
            return;
        }

        board[endI][endJ] = startPiece;
        board[startI][startJ] = new Piece(PieceType.EMPTY, startI, startJ);
    }


}
