package ru.vsu.cs.oop.popova_p_n.tack2;

import java.util.Scanner;

public class CheckersGame {
    private Board board;

    public CheckersGame(Board board) {
        this.board = board;
    }
    public void startGame() {
        boolean isWhite = true;
        Scanner scanner = new Scanner(System.in);

        while (!board.isGameOver()) {
            board.displayBoard();

            int startI, startJ, endI, endJ;

            if (isWhite) {
                System.out.println("Ход белых шашек:");
            } else {
                System.out.println("Ход черных шашек:");
            }

            System.out.print("Введите координаты начальной клетки (строка столбец): ");
            startI = scanner.nextInt();
            startJ = scanner.nextInt();

            System.out.print("Введите координаты конечной клетки (строка столбец): ");
            endI = scanner.nextInt();
            endJ = scanner.nextInt();

            if (board.isValidMove(startI, startJ, endI, endJ)) {
                board.makeMove(startI, startJ, endI, endJ);

                if (Math.abs(endI - startI) == 2) {
                    int killedI = (startI + endI) / 2;
                    int killedJ = (startJ + endJ) / 2;


                    board.removePiece(killedI, killedJ);
                }
            } else {
                System.out.println("Неверный ход. Повторите попытку.");
                continue;
            }

            isWhite = !isWhite;
        }

        board.displayBoard();
        System.out.println("Игра завершена.");
        scanner.close();
    }

}
