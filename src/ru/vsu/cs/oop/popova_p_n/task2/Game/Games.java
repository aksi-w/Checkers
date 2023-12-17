package ru.vsu.cs.oop.popova_p_n.task2.Game;

import ru.vsu.cs.oop.popova_p_n.task2.Boards.Board;
import ru.vsu.cs.oop.popova_p_n.task2.Boards.Cell;
import ru.vsu.cs.oop.popova_p_n.task2.Boards.Direction;
import ru.vsu.cs.oop.popova_p_n.task2.Piece.Piece;
import ru.vsu.cs.oop.popova_p_n.task2.Piece.PieceType;
import ru.vsu.cs.oop.popova_p_n.task2.Players.Bot;
import ru.vsu.cs.oop.popova_p_n.task2.Players.Player;

import java.util.*;

public class Games {
    private final static Board boardService = new Board();
    private final static Bot playerService = new Bot();

    public void startGame(Game game, List<Player> players) {
        Cell rightUp = new Cell();
        Cell leftDown = boardService.createBoard(rightUp);
        game.setRightUpCell(rightUp);
        game.setLeftDownCell(leftDown);
        setCheckers(players, game);
        game.setBoardPaint(boardService.getBoardForPainting(game));
        Board.initializeBoard(game);
        processGame(game);
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

    private Player doMove(Game game) {
        Player player = game.getPlayers().poll();
        System.out.println(player != null ? player.getName() : null + "ход");
        if (!playerService.doMove(player, game)) {
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
        List<Piece> figureForPlayerOne = new ArrayList<>();
        List<Piece> figureForPlayerTwo = new ArrayList<>();
        Map<Cell, Piece> cellFigureMap = new HashMap<>();
        Map<Piece, Cell> figureCellMap = new HashMap<>();
        Map<Player, List<Piece>> playerSetMap = new HashMap<>();
        Map<Piece, Player> figurePlayerMap = new HashMap<>();
        Map<Player, List<Direction>> availableDirection = new HashMap<>();

        setFiguresForPlayer(game.getRightUpCell(), one, figureForPlayerOne, cellFigureMap, figureCellMap, figurePlayerMap,
                Direction.LEFT_DOWN, Direction.LEFT_UP, Direction.RIGHT_UP, Direction.RIGHT_DOWN);
        availableDirection.put(one, List.of(Direction.RIGHT_DOWN, Direction.LEFT_DOWN));
        setFiguresForPlayer(game.getLeftDownCell(), two, figureForPlayerTwo, cellFigureMap, figureCellMap, figurePlayerMap,
                Direction.RIGHT_UP, Direction.RIGHT_DOWN, Direction.LEFT_DOWN, Direction.LEFT_UP);
        availableDirection.put(two, List.of(Direction.RIGHT_UP, Direction.LEFT_UP));

        Map<PieceType, String> forOne = new HashMap<>();
        forOne.put(PieceType.PAWN, "W ");
        forOne.put(PieceType.QUEEN, "QW ");
        Map<PieceType, String> forTwo = new HashMap<>();
        forTwo.put(PieceType.PAWN, "B ");
        forTwo.put(PieceType.QUEEN, "QB ");
        game.getSeePiece().put(one, forOne);
        game.getSeePiece().put(two, forTwo);
        game.setCellPiece(cellFigureMap);
        game.setPieceCell(figureCellMap);
        playerSetMap.put(one, figureForPlayerOne);
        playerSetMap.put(two, figureForPlayerTwo);
        game.setPlayerPiece(playerSetMap);
        game.setPiecePlayerMap(figurePlayerMap);
        game.setAvialableDirections(availableDirection);
    }

    private void setFiguresForPlayer(Cell curr,
                                     Player player,
                                     List<Piece> figureForPlayer,
                                     Map<Cell, Piece> cellFigureMap,
                                     Map<Piece, Cell> figureCellMap,
                                     Map<Piece, Player> figurePlayerMap,
                                     Direction firstDirectionForFirstIteration,
                                     Direction secondDirectionForFirstIteration,
                                     Direction firstDirectionForSecondIteration,
                                     Direction secondDirectionForSecondIteration) {
        while (curr.getNeighbours().containsKey(firstDirectionForFirstIteration) ||
                curr.getNeighbours().containsKey(secondDirectionForFirstIteration)) {
            Piece figure = new Piece(PieceType.PAWN);
            figureForPlayer.add(figure);
            cellFigureMap.put(curr, figure);
            figureCellMap.put(figure, curr);
            figurePlayerMap.put(figure, player);
            if (curr.getNeighbours().containsKey(secondDirectionForFirstIteration)) {
                curr = curr.getNeighbours().get(secondDirectionForFirstIteration);
            } else if (curr.getNeighbours().containsKey(firstDirectionForFirstIteration)) {
                curr = curr.getNeighbours().get(firstDirectionForFirstIteration);
            } else {
                break;
            }
        }
        Piece figure = new Piece(PieceType.PAWN);
        figureForPlayer.add(figure);
        cellFigureMap.put(curr, figure);
        figureCellMap.put(figure, curr);
        figurePlayerMap.put(figure, player);
        boolean isCellAvailable = true;
        while (curr.getNeighbours().containsKey(firstDirectionForSecondIteration)
                || curr.getNeighbours().containsKey(secondDirectionForSecondIteration)) {
            if (!isCellAvailable) {
                curr = curr.getNeighbours().get(firstDirectionForSecondIteration);
                isCellAvailable = true;
            } else {
                if (curr.getNeighbours().containsKey(secondDirectionForSecondIteration)) {
                    curr = curr.getNeighbours().get(secondDirectionForSecondIteration);
                    Piece figure1 = new Piece(PieceType.PAWN);
                    figureForPlayer.add(figure1);
                    cellFigureMap.put(curr, figure1);
                    figureCellMap.put(figure1, curr);
                    figurePlayerMap.put(figure1, player);
                    isCellAvailable = false;
                } else {
                    break;
                }
            }
        }
    }
}