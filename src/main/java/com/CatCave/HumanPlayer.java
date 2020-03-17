package com.CatCave;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class HumanPlayer implements Player {

    String playerName;

    public HumanPlayer(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    private List<String> playerInputToStringList(String input) {

        return Stream.of(input.trim()
                .toLowerCase()
                .split("\\W|(?<=\\d)(?=\\D)|(?=\\d)(?<=\\D)"))
                .filter(sign -> !sign.isBlank())
                .collect(Collectors.toList());
    }

    public int playerInputToNavPoints(String input) {

        List<String> list = playerInputToStringList(input);

        if (list.size() != 2) {
            return 150;
        }

        int tens, num;

        if (!Character.isDigit(list.get(0).charAt(0))) {
            tens = ((list.get(0).trim().charAt(0)) - 'a') * 10;
            num = Integer.parseInt(list.get(1));
        } else {
            tens = ((list.get(1).trim().charAt(0)) - 'a') * 10;
            num = Integer.parseInt(list.get(0));
        }

        return tens + num - 1;
    }

    @Override
    public void putOneFlagShips(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_ONE_FLAG_SHIPS; i++) {
            System.out.println("Do postawienia zostalo " + (Game.NUMBER_OF_ONE_FLAG_SHIPS - i)
                    + " statkow jedno-masztowych");

            Scanner scan = new Scanner(System.in);

            while (true) {
                System.out.println("Gdzie postawic statek? Podaj punkt");
                if (board.setOneFlagShip(playerInputToNavPoints(scan.nextLine()))) {
                    break;
                } else {
                    System.out.println("Niepoprawne miejsce");
                }
            }
            board.printBoard();
        }
    }

    @Override
    public void putTwoFlagShips(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_TWO_FLAG_SHIPS; i++) {
            System.out.println("Do postawienia zostalo " + (Game.NUMBER_OF_TWO_FLAG_SHIPS - i)
                    + " statkow dwu-masztowych");

            Scanner scan = new Scanner(System.in);

            while (true) {

                List<Integer> listOfUserNavPoints = new ArrayList<>();
                int numberOfShipFlags = 2;
                for (int j = 0; j < numberOfShipFlags; j++) {
                    System.out.println("Gdzie postawic statek? Podaj punkt");
                    listOfUserNavPoints.add(playerInputToNavPoints(scan.nextLine()));
                }

                Collections.sort(listOfUserNavPoints);
                if (board.setTwoFlagShip(
                        listOfUserNavPoints.get(0),
                        listOfUserNavPoints.get(1))) {
                    break;
                } else {
                    System.out.println("Niepoprawne polozenie statku");
                }
            }
            board.printBoard();
        }
    }

    @Override
    public void putThreeFlagShips(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_THREE_FLAG_SHIPS; i++) {
            System.out.println("Do postawienia zostalo " + (Game.NUMBER_OF_THREE_FLAG_SHIPS - i)
                    + " statkow trzy-masztowych");

            Scanner scan = new Scanner(System.in);

            while (true) {
                int numberOfShipFlags = 3;
                List<Integer> listOfUserNavPoints = new ArrayList<>();
                for (int j = 0; j < numberOfShipFlags; j++) {
                    System.out.println("Gdzie postawic statek? Podaj punkt");
                    listOfUserNavPoints.add(playerInputToNavPoints(scan.nextLine()));
                }

                Collections.sort(listOfUserNavPoints);
                if (board.setThreeFlagShip(
                        listOfUserNavPoints.get(0),
                        listOfUserNavPoints.get(1),
                        listOfUserNavPoints.get(2))) {
                    break;
                } else {
                    System.out.println("Niepoprawne polozenie statku");
                }
            }

            board.printBoard();
        }
    }

    @Override
    public void putFourFlagShips(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_FOUR_FLAG_SHIPS; i++) {
            System.out.println("Do postawienia został " + (Game.NUMBER_OF_FOUR_FLAG_SHIPS - i)
                    + " statek cztero-masztowy");

            Scanner scan = new Scanner(System.in);

            while (true) {

                int numberOfShipFlags = 4;
                List<Integer> listOfUserNavPoints = new ArrayList<>();
                for (int j = 0; j < numberOfShipFlags; j++) {
                    System.out.println("Gdzie postawic statek? Podaj punkt");
                    listOfUserNavPoints.add(playerInputToNavPoints(scan.nextLine()));
                }

                Collections.sort(listOfUserNavPoints);
                if (board.setFourFlagShip(
                        listOfUserNavPoints.get(0),
                        listOfUserNavPoints.get(1),
                        listOfUserNavPoints.get(2),
                        listOfUserNavPoints.get(3))) {
                    break;
                } else {
                    System.out.println("Niepoprawne polozenie statku");
                }
            }

            board.printBoard();
        }
    }

    @Override
    public void putShipsOnBoards(Board board) {
        putFourFlagShips(board);
        putThreeFlagShips(board);
        putTwoFlagShips(board);
        putOneFlagShips(board);
    }





    @Override
    public boolean fire(Board board) {
        Scanner scan = new Scanner(System.in);
        board.printBoardOfHits();
        while (board.hit(playerInputToNavPoints(scan.nextLine()))) {
            System.out.println("Oddaj STRZAL! podaj pole");
            board.printBoardOfHits();

            if (!board.areThereStillShips()) {
                return false;
            }
        }

        return false;
    }
}
