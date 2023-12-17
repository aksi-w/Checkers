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
        players.add(new Player("за белых"));
        players.add(new Player("за черных"));
        gameService.startGame(game, players);

        Scanner scanner = new Scanner(System.in);

        String readString = scanner.nextLine();
        while(readString!=null) {
            System.out.println(readString);

            if (readString.isEmpty()) {
                gameService.nextStep(game);
            }

            readString = scanner.nextLine();
        }

    }
}
