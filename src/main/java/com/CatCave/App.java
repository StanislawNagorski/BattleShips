package com.CatCave;

public class App {
    public static void main(String[] args) {

        Board playerOneBoard = new Board();
       // Player player1 = new HumanPlayer(Game.PLAYER_ONE_NAME);
        Player player1 = new AIplayer();
        Board playerTwoBoard = new Board();
        Player player2;
        Player lazyTester = new AIplayer();

        if (Game.NUMBER_OF_PLAYERS == 2) {
            player2 = new HumanPlayer(Game.PLAYER_TWO_NAME);
        } else {
            player2 = new AIplayer();
        }

        playerOneBoard.printBoard();
        System.out.println("Zaczyna gracz " + player1.getPlayerName() + ". Ustaw statki");
        //player1.putShipsOnBoards(playerOneBoard);
        //na potrzeby testu
        //player2.putShipsOnBoards(playerOneBoard);
        lazyTester.putShipsOnBoards(playerOneBoard);
        playerOneBoard.printBoard();
        Board.clearScreen();

//        if (Game.NUMBER_OF_PLAYERS == 2){
//            playerTwoBoard.printBoard();
//        }
        System.out.println("Tura gracza: " + player2.getPlayerName() + ". Ustaw statki");
       // player2.putShipsOnBoards(playerTwoBoard);
        lazyTester.putShipsOnBoards(playerTwoBoard);
        playerTwoBoard.printBoard();
        Board.clearScreen();



        while (playerOneBoard.areThereStillShips() || playerTwoBoard.areThereStillShips()) {


            System.out.println("Tura gracza: " + player1.getPlayerName() + ".");
            player1.fire(playerTwoBoard);

            if (!playerTwoBoard.areThereStillShips()) {
                System.out.println("Wygrywa gracz (☞ﾟ∀ﾟ)☞ " + player1.getPlayerName() + "!");
                break;
            }


            System.out.println("Tura gracza: " + player2.getPlayerName() + ".");
            player2.fire(playerOneBoard);
            if (!playerOneBoard.areThereStillShips()) {
                System.out.println("Wygrywa gracz (☞ﾟ∀ﾟ)☞ " + player2.getPlayerName() + "!");
                break;
            }


        }

        System.out.println("Dzieki za gre!  (~￣▽￣)~ ");


    }
}
