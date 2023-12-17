package ru.vsu.cs.oop.popova_p_n.task2;

import ru.vsu.cs.oop.popova_p_n.task2.Game.Game;
import ru.vsu.cs.oop.popova_p_n.task2.Game.Games;
import ru.vsu.cs.oop.popova_p_n.task2.Players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Games gameService = new Games();
        Game game = Game.createGame();
        List<Player> players = new ArrayList<>();
        players.add(new Player("Игрок за белых"));
        players.add(new Player("Игрок за черных"));
        gameService.startGame(game, players);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            for (Player player : players) {
                gameService.doMove(game);
                scanner.nextLine();

            }
        }
    }
}
