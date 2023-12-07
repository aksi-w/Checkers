//package ru.vsu.cs.oop.popova_p_n.task2;
//
//import ru.vsu.cs.oop.popova_p_n.task2.Boards.Cell;
//import ru.vsu.cs.oop.popova_p_n.task2.Piece.Piece;
//import ru.vsu.cs.oop.popova_p_n.task2.Piece.PieceType;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Board {
//    private List<Cell> cells;
//
//    public Board(int rows, int cols) {
//        initializeBoard(rows, cols);
//        initializePieces();
//    }
//
//    private void initializeBoard(int rows, int cols) {
//        cells = new ArrayList<>();
//
//        for (int x = 0; x < rows; x++) {
//            for (int y = 0; y < cols; y++) {
//                createCell(x, y);
//            }
//        }
//
//        connectCells();
//    }
//
//    private void createCell(int x, int y) {
//        cells.add(new Cell(x, y));
//    }
//
//    private void initializePieces() {
//        for (Cell cell : cells) {
//            initializeCheckers(cell);
//        }
//    }
//
//    private void initializeCheckers(Cell currentCell) {
//        int x = currentCell.getX();
//        int y = currentCell.getY();
//
//        if ((x + y) % 2 == 0 && x < 3) {
//            Piece piece = new Piece(PieceType.BLACK, x, y);
//            currentCell.setPiece(piece);
//        }
//
//        if ((x + y) % 2 == 0 && x > 4) {
//            Piece piece = new Piece(PieceType.WHITE, x, y);
//            currentCell.setPiece(piece);
//        }
//    }
//
//    private void connectCells() {
//        for (Cell cell : cells) {
//            int x = cell.getX();
//            int y = cell.getY();
//
//            for (Cell neighbor : cells) {
//                int nx = neighbor.getX();
//                int ny = neighbor.getY();
//
//                if (Math.abs(x - nx) == 1 && Math.abs(y - ny) == 1) {
//                    cell.addEdge(neighbor);
//                }
//            }
//        }
//    }
//
//
//    public void displayBoard() {
//        int count = 0;
//        for (Cell cell : cells) {
//            if (cell.getPiece() != null) {
//                System.out.print(cell.getPiece().getType().getLabel() + " ");
//            } else {
//                System.out.print(PieceType.EMPTY.getLabel() + " ");
//            }
//            count++;
//            if (count % 8 == 0) {
//                System.out.println();
//            }
//        }
//    }
//
//    public void removePiece(int x, int y) {
//        Cell cellToRemove = getCell(x, y);
//        if (cellToRemove != null) {
//            cells.remove(cellToRemove);
//            for (Cell cell : cells) {
//                cell.removeEdge(cellToRemove);
//            }
//        }
//    }
//
//    private Cell getCell(int x, int y) {
//        for (Cell cell : cells) {
//            if (cell.getX() == x && cell.getY() == y) {
//                return cell;
//            }
//        }
//        return null;
//    }
//}