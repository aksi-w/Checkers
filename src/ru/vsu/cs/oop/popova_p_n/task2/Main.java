package ru.vsu.cs.oop.popova_p_n.task2;

import ru.vsu.cs.oop.popova_p_n.task2.Game.Game;
import ru.vsu.cs.oop.popova_p_n.task2.Game.Games;
import ru.vsu.cs.oop.popova_p_n.task2.Players.Player;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Games gameService = new Games();
        Game game = Game.createGame();
        List<Player> pl = new ArrayList<>();
        pl.add(new Player("Игрок за белых"));
        pl.add(new Player("Игрок за черных"));
        gameService.startGame(game, pl);
    }
}