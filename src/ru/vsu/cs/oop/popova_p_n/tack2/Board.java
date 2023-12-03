package ru.vsu.cs.oop.popova_p_n.tack2;

public class Board {
    private final Piece[][] board;
    static final int rows = 8;
    static final int cols = 8;
    private boolean isWhite = true;

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
                    } else if (i > cols - 4) {
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
        if (!isValidIndex(startI, startJ) || !isValidIndex(endI, endJ)) {
            return false;
        }

        Piece startPiece = board[startI][startJ];
        Piece endPiece = board[endI][endJ];
        if (startPiece.getType() == PieceType.EMPTY) {
            return false;
        }

        if (endPiece.getType() != PieceType.EMPTY) {
            return false;
        }

        int rowChange = endI - startI;
        int colChange = endJ - startJ;

        if ((endI == 0 && startPiece.getType() == PieceType.BLACK) || (endI == board.length-1 && startPiece.getType() == PieceType.WHITE)) {
            startPiece.setType((startPiece.getType() == PieceType.BLACK) ? PieceType.BLACK_QUEEN : PieceType.WHITE_QUEEN);
            return true;
        }


        if (Math.abs(rowChange) == 2 && Math.abs(colChange) == 2) {
            int killedI = (startI + endI) / 2;
            int killedJ = (startJ + endJ) / 2;

            if (isValidIndex(killedI, killedJ)) {
                Piece killedPiece = board[killedI][killedJ];
                return startPiece.getType() != killedPiece.getType() && killedPiece.getType() != PieceType.EMPTY;
            } else {
                return false;
            }
        }

        if (Math.abs(rowChange) == 2 && Math.abs(colChange) == 0) {
            int killedI = (startI + endI) / 2;
            if (isValidIndex(killedI, startJ)) {
                Piece killedPiece = board[killedI][startJ];
                return startPiece.getType() != killedPiece.getType() && killedPiece.getType() != PieceType.EMPTY;
            } else {
                return false;
            }
        }

        if ((isWhite && startPiece.getType() != PieceType.WHITE) ||
                (!isWhite && startPiece.getType() != PieceType.BLACK)) {
            return false;
        }

        if (Math.abs(rowChange) != 1 || Math.abs(colChange) != 1) {
            return false;
        }

        if (startPiece.getType() == PieceType.BLACK_QUEEN || startPiece.getType() == PieceType.WHITE_QUEEN) {
            return Math.abs(rowChange) == Math.abs(colChange);
        }

        return true;
    }


    private boolean isValidIndex(int i, int j) {
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length;
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

        isWhite = !isWhite;
    }

    public boolean isGameOver() {
        int blackCnt = 0;
        int whiteCnt = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j].getType() == PieceType.BLACK) {
                    blackCnt++;
                } else if (board[i][j].getType() == PieceType.WHITE) {
                    whiteCnt++;
                }
            }
        }
        return blackCnt == 0 || whiteCnt == 0;
    }
}