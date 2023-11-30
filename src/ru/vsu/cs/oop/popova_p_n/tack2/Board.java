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
                    } else if (i > cols-4) {
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
                System.out.print(board[i][j].toString() + " ");
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int startI, int startJ, int endI, int endJ) {
        Piece startPiece = board[startI][startJ];
        Piece endPiece = board[endI][endJ];

        if (startPiece.getType() == PieceType.EMPTY) {
            throw new IllegalArgumentException("Нельзя совершать ход с пустой клетки");
        }

        if (endPiece.getType() != PieceType.EMPTY) {
            throw new IllegalArgumentException("Нельзя совершать ход на занятую клетку");
        }

        int rowChange = endI - startI;
        int colChange = Math.abs(endJ - startJ);

        if (startPiece.getType() == PieceType.WHITE && rowChange == 1 && colChange == 1) {
            return true;
        } else if (startPiece.getType() == PieceType.BLACK && rowChange == -1 && colChange == 1) {
            return true;
        }

        if (Math.abs(rowChange) == 2 && colChange == 2) {
            int killedI = (startI + endI) / 2;
            int killedJ = (startJ + endJ) / 2;

            Piece killedPiece = board[killedI][killedJ];

            if (startPiece.getType() == PieceType.WHITE && killedPiece.getType() == PieceType.BLACK) {
                return true;
            } else if (startPiece.getType() == PieceType.BLACK && killedPiece.getType() == PieceType.WHITE) {
                return true;
            }
        }

        return true;
    }
    public void removePiece(int row, int col) {
        board[row][col] = new Piece(PieceType.EMPTY, row, col);
    }


    public void makeMove(int startI, int startJ, int endI, int endJ) {
        if (!isValidMove(startI, startJ, endI, endJ)) {
            return;
        }
        Piece startPiece = board[startI][startJ];
        board[endI][endJ] = startPiece;
        startPiece.updatePosition(endI, endJ);
        board[startI][startJ] = new Piece(PieceType.EMPTY, startI, startJ);
    }

    public boolean isGameOver() {
        int blackCount = 0;
        int whiteCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j].getType() == PieceType.BLACK) {
                    blackCount++;
                } else if (board[i][j].getType() == PieceType.WHITE) {
                    whiteCount++;
                }
            }
        }
        return blackCount == 0 || whiteCount == 0;
    }


}
