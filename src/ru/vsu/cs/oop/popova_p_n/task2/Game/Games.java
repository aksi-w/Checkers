package ru.vsu.cs.oop.popova_p_n.task2.Game;

import ru.vsu.cs.oop.popova_p_n.task2.Boards.Board;
import ru.vsu.cs.oop.popova_p_n.task2.Boards.Cell;
import ru.vsu.cs.oop.popova_p_n.task2.Boards.Direction;
import ru.vsu.cs.oop.popova_p_n.task2.Moves.Move;
import ru.vsu.cs.oop.popova_p_n.task2.Moves.MoveHistory;
import ru.vsu.cs.oop.popova_p_n.task2.Piece.Piece;
import ru.vsu.cs.oop.popova_p_n.task2.Piece.PieceType;
import ru.vsu.cs.oop.popova_p_n.task2.Players.Bot;
import ru.vsu.cs.oop.popova_p_n.task2.Players.Player;

import java.util.*;

public class Games {
    private final static Board boardService = new Board();
    private final static Bot playerService = new Bot(new MoveHistory());
    private final MoveHistory moveHistory = new MoveHistory();

    public void startGame(Game game, List<Player> players) {
        Cell rightUp = new Cell();
        Cell leftDown = boardService.createBoard(rightUp);
        game.setRightUpCell(rightUp);
        game.setLeftDownCell(leftDown);
        setCheckers(players, game);
        game.setBoardPaint(boardService.getBoardForPainting(game));
        Board.initializeBoard(game);

    }

    private void processGame(Game game) {
        Player player = null;
        while (game.getPlayers().size() == 2) {
            player = doMove(game);
            Board.initializeBoard(game);
        }
        if (player != null) {
            System.out.println(player.getName() + " Победитель");
        }
    }

    public boolean move(Game game) {
        Player player = game.getPlayers().peek();
        boolean moveResult = playerService.makeMove(player, game);
        if (moveResult) {
            Move currentMove = playerService.getMoveHistory().getLastMove();
            if (currentMove != null) {
                moveHistory.addMove(currentMove);
            }
        }
        return moveResult;
    }

    public void nextStep(Game game) {
        Player player = doMove(game);
        Board.initializeBoard(game);
        if (player != null) {
            System.out.println(player.getName() + " Победитель");
        }
    }

    private Player doMove(Game game) {
        Player player = game.getPlayers().poll();
        System.out.println("Время хода игрока " + player.getName());
        if (!playerService.makeMove(player, game)) {
            return game.getPlayers().peek();
        }
        if (game.getPlayerPiece().get(game.getPlayers().peek()).isEmpty()) {
            return player;
        }
        game.getPlayers().offer(player);
        return null;
    }

    private void setCheckers(List<Player> players, Game game) {
        List<Player> pl = new ArrayList<>(players);
        Player one = pl.get(0);
        Player two = pl.get(1);
        Queue<Player> playerQueue = new ArrayDeque<>();
        playerQueue.offer(one);
        playerQueue.offer(two);
        game.setPlayers(playerQueue);
        List<Piece> pieceForPlayerOne = new ArrayList<>();
        List<Piece> pieceForPlayerTwo = new ArrayList<>();
        Map<Cell, Piece> cellPieceMap = new HashMap<>();
        Map<Piece, Cell> pieceCellMap = new HashMap<>();
        Map<Player, List<Piece>> playerSetMap = new HashMap<>();
        Map<Piece, Player> piecePlayerMap = new HashMap<>();
        Map<Player, List<Direction>> availableDirection = new HashMap<>();

        setPiecesForPlayer(game.getRightUpCell(), one, pieceForPlayerOne, cellPieceMap, pieceCellMap, piecePlayerMap,
                Direction.LEFT_DOWN, Direction.LEFT_UP, Direction.RIGHT_UP, Direction.RIGHT_DOWN);
        availableDirection.put(one, List.of(Direction.RIGHT_DOWN, Direction.LEFT_DOWN));
        setPiecesForPlayer(game.getLeftDownCell(), two, pieceForPlayerTwo, cellPieceMap, pieceCellMap, piecePlayerMap,
                Direction.RIGHT_UP, Direction.RIGHT_DOWN, Direction.LEFT_DOWN, Direction.LEFT_UP);
        availableDirection.put(two, List.of(Direction.RIGHT_UP, Direction.LEFT_UP));

        Map<PieceType, String> forOne = new HashMap<>();
        forOne.put(PieceType.PAWN, " W ");
        forOne.put(PieceType.QUEEN, " QW ");
        Map<PieceType, String> forTwo = new HashMap<>();
        forTwo.put(PieceType.PAWN, " B ");
        forTwo.put(PieceType.QUEEN, " QB ");
        game.getSeePiece().put(one, forOne);
        game.getSeePiece().put(two, forTwo);
        game.setCellPiece(cellPieceMap);
        game.setPieceCell(pieceCellMap);
        playerSetMap.put(one, pieceForPlayerOne);
        playerSetMap.put(two, pieceForPlayerTwo);
        game.setPlayerPiece(playerSetMap);
        game.setPiecePlayerMap(piecePlayerMap);
        game.setAvialableDirections(availableDirection);
    }

    private void setPiecesForPlayer(Cell curr,
                                    Player player,
                                    List<Piece> piecesForPlayer,
                                    Map<Cell, Piece> cellPieceMap,
                                    Map<Piece, Cell> pieceCellMap,
                                    Map<Piece, Player> piecePlayerMap,
                                    Direction firstDirectionForFirstIteration,
                                    Direction secondDirectionForFirstIteration,
                                    Direction firstDirectionForSecondIteration,
                                    Direction secondDirectionForSecondIteration) {
        while (curr.getNeighbours().containsKey(firstDirectionForFirstIteration) ||
                curr.getNeighbours().containsKey(secondDirectionForFirstIteration)) {
            Piece piece = new Piece(PieceType.PAWN);
            piecesForPlayer.add(piece);
            cellPieceMap.put(curr, piece);
            pieceCellMap.put(piece, curr);
            piecePlayerMap.put(piece, player);
            if (curr.getNeighbours().containsKey(secondDirectionForFirstIteration)) {
                curr = curr.getNeighbours().get(secondDirectionForFirstIteration);
            } else if (curr.getNeighbours().containsKey(firstDirectionForFirstIteration)) {
                curr = curr.getNeighbours().get(firstDirectionForFirstIteration);
            } else {
                break;
            }
        }
        Piece piece = new Piece(PieceType.PAWN);
        piecesForPlayer.add(piece);
        cellPieceMap.put(curr, piece);
        pieceCellMap.put(piece, curr);
        piecePlayerMap.put(piece, player);
        boolean isCellAvailable = true;
        while (curr.getNeighbours().containsKey(firstDirectionForSecondIteration)
                || curr.getNeighbours().containsKey(secondDirectionForSecondIteration)) {
            if (!isCellAvailable) {
                curr = curr.getNeighbours().get(firstDirectionForSecondIteration);
                isCellAvailable = true;
            } else {
                if (curr.getNeighbours().containsKey(secondDirectionForSecondIteration)) {
                    curr = curr.getNeighbours().get(secondDirectionForSecondIteration);
                    Piece piece1 = new Piece(PieceType.PAWN);
                    piecesForPlayer.add(piece1);
                    cellPieceMap.put(curr, piece1);
                    pieceCellMap.put(piece1, curr);
                    piecePlayerMap.put(piece1, player);
                    isCellAvailable = false;
                } else {
                    break;
                }
            }
        }
    }
}
