package ru.vsu.cs.oop.popova_p_n.task2.Boards;

import ru.vsu.cs.oop.popova_p_n.task2.Game.Game;
import ru.vsu.cs.oop.popova_p_n.task2.Piece.Piece;
import ru.vsu.cs.oop.popova_p_n.task2.Players.Player;

import java.util.*;

public class Board  {
    private static final int BOARD_SIZE = 8;
    private static final int MAX_CELLS_IN_LINE = 4;
    public Cell createBoard(Cell rightUp) {
        List<Cell> centerCells = new ArrayList<>();
        centerCells.add(rightUp);
        Cell prev = rightUp;

        for (int i = 1; i < 8; i++) {
            Cell curr = new Cell();
            prev.getNeighbours().put(Direction.LEFT_DOWN, curr);
            curr.getNeighbours().put(Direction.RIGHT_UP, prev);
            prev = curr;
            centerCells.add(curr);
        }

        for (int i = 1; i < 7; i++) {
            Deque<Cell> deq = new ArrayDeque<>();
            Cell center = centerCells.get(i);
            deq.addFirst(center);

            for (int j = 0; j < i && j < 7 - i; j++) {
                Cell northWest = new Cell();
                northWest.getNeighbours().put(Direction.RIGHT_DOWN, deq.getLast());
                deq.getLast().getNeighbours().put(Direction.LEFT_UP, northWest);
                deq.addLast(northWest);

                Cell southEast = new Cell();
                southEast.getNeighbours().put(Direction.LEFT_UP, deq.getFirst());
                deq.getFirst().getNeighbours().put(Direction.RIGHT_DOWN, southEast);
                deq.addFirst(southEast);
            }
        }

        for (int i = 0; i < 7; i++) {
            Cell currentFirst = centerCells.get(i);
            Cell currentSecond = centerCells.get(i + 1);
            createDiag(currentFirst, currentSecond, Direction.RIGHT_DOWN);

            currentFirst = centerCells.get(i);
            currentSecond = centerCells.get(i + 1);
            createDiag(currentFirst, currentSecond, Direction.LEFT_UP);
        }

        return centerCells.get(BOARD_SIZE - 1);
    }

    private void createDiag(Cell currentFirst, Cell currentSecond, Direction direction) {
        while (currentSecond != null && currentFirst != null) {
            currentFirst.getNeighbours().put(Direction.LEFT_DOWN, currentSecond);
            currentSecond.getNeighbours().put(Direction.RIGHT_UP, currentFirst);

            currentFirst = currentFirst.getNeighbours().get(direction);
            currentSecond = currentSecond.getNeighbours().get(direction);
        }
    }

    public static void initializeBoard(Game game) {
        List<List<Cell>> board = game.getBoardPaint();

        for (List<Cell> line : board) {
            for (int j = 0; j < MAX_CELLS_IN_LINE; j++) {
                Cell currentPaintingCell = line.get(j);
                Piece piece = game.getCellPiece().get(currentPaintingCell);

                if (piece != null) {
                    Player owner = game.getPiecePlayerMap().get(piece);
                    System.out.print(" " + game.getSeePiece().get(owner).get(piece.getType()) + " ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }


    public List<List<Cell>> getBoardForPainting(Game game) {
        Cell curr = game.getRightUpCell();
        List<List<Cell>> board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            List<Cell> lineListOfCells = new ArrayList<>();
            if (i % 2 == 0) {
                for (int c = 0; c < 4; c++) {
                    lineListOfCells.add(curr);
                    curr = curr.getNeighbours().get(Direction.LEFT_DOWN);
                    if (curr.getNeighbours().containsKey(Direction.LEFT_UP)) {
                        curr = curr.getNeighbours().get(Direction.LEFT_UP);
                    } else {
                        break;
                    }
                }
                Collections.reverse(lineListOfCells);
            } else {
                if (i != 7) {
                    for (int c = 0; c < 4; c++) {
                        lineListOfCells.add(curr);
                        if (!curr.getNeighbours().containsKey(Direction.RIGHT_DOWN)) {
                            break;
                        }
                        curr = curr.getNeighbours().get(Direction.RIGHT_DOWN);
                        if (curr.getNeighbours().containsKey(Direction.RIGHT_UP)) {
                            curr = curr.getNeighbours().get(Direction.RIGHT_UP);
                        }
                    }
                } else {
                    lineListOfCells.add(curr);
                    for (int l = 0; l < 3; l++) {
                        curr = curr.getNeighbours().get(Direction.RIGHT_UP);
                        curr = curr.getNeighbours().get(Direction.RIGHT_DOWN);
                        lineListOfCells.add(curr);
                    }
                }
            }
            board.add(lineListOfCells);

        }
        return board;
    }
}
