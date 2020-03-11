package com.CatCave;

public class App {
    public static void main(String[] args) {

        Board playerOneBoard = new Board();
        Player player1 = new Player();

        Board playerTwoBoard = new Board();
        Player player2 = new Player();

        System.out.println("Zaczyna gracz numer JEDEN. Ustaw statki");
        player1.putShipsOnBoard(playerOneBoard);
        System.out.println("Graczu numer DWA. Ustaw statki");
        player2.putShipsOnBoard(playerTwoBoard);


        while (playerOneBoard.areThereStillShips() || playerTwoBoard.areThereStillShips()) {

            System.out.println("Tura gracza numer JEDEN");
            player1.fire(playerTwoBoard);
            if (!playerTwoBoard.areThereStillShips()) {
                System.out.println("Wygrywa gracz numer JEDEN!");
                break;
            }


            System.out.println("Tura gracza numer DWA");
            player2.fire(playerOneBoard);
            if (!playerOneBoard.areThereStillShips()) {
                System.out.println("Wygrywa gracz numer DWA!");
                break;
            }


        }

        System.out.println("Dzięki za gre!");


    }
}
