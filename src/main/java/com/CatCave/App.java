package com.CatCave;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App
{
    public static void main( String[] args ){

        Board playerOneBoard = new Board();
        Player player1 = new Player();

        Board playerTwoBoard = new Board();
        Player player2 = new Player();


//        playerOneBoard.printBoard();
//        player1.putFourFlagShip(playerOneBoard);
//        player1.putThreeFlagShip(playerOneBoard);
//        player1.putTwoFlagShip(playerOneBoard);
//        player1.putOneFlagShip(playerOneBoard);

        System.out.println();

        playerTwoBoard.printBoard();
        player2.putFourFlagShip(playerTwoBoard);
//        player2.putThreeFlagShip(playerTwoBoard);
//        player2.putTwoFlagShip(playerTwoBoard);
        player2.putOneFlagShip(playerTwoBoard);

        player1.fire(playerTwoBoard);



    }
}
