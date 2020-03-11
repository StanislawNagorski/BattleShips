package com.CatCave;

import lombok.AllArgsConstructor;

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

        if (list.size() != 2){
            return 150;
        }

        int tens, num;

        if (!Character.isDigit(list.get(0).charAt(0))){
            tens = ((list.get(0).trim().charAt(0)) - 'a') * 10;
            num = Integer.parseInt(list.get(1));
        } else {
            tens = ((list.get(1).trim().charAt(0)) - 'a') * 10;
            num = Integer.parseInt(list.get(0));
        }

        return tens + num - 1;
    }


    public void putOneFlagShip(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_ONE_FLAG_SHIPS; i++) {
            System.out.println("Do postawienia zostalo " + (Game.NUMBER_OF_ONE_FLAG_SHIPS - i)
                    + " statkow jedno-masztowych");

            Scanner scan = new Scanner(System.in);

            boolean flag = true;
            while (flag) {

                System.out.println("Gdzie postawic statek? Podaj punkt");
                flag = !board.setOneFlagShip(playerInputToNavPoints(scan.nextLine()));
                if (flag) {
                    System.out.println("Niepoprawne miejsce");
                }
            }
            board.printBoard();
        }
    }

    public void putTwoFlagShip(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_TWO_FLAG_SHIPS; i++) {
            System.out.println("Do postawienia zostalo " + (Game.NUMBER_OF_TWO_FLAG_SHIPS - i)
                    + " statkow dwu-masztowych");

            Scanner scan = new Scanner(System.in);

            boolean flag = true;
            while (flag) {

                List<Integer> listOfUserNavPoints = new ArrayList<>();
                for (int j = 0; j < 2; j++) {
                    System.out.println("Gdzie postawic statek? Podaj punkt");
                    listOfUserNavPoints.add(playerInputToNavPoints(scan.nextLine()));
                }

                Collections.sort(listOfUserNavPoints);
                flag = !board.setTwoFlagShip(listOfUserNavPoints.get(0), listOfUserNavPoints.get(1));

                if (flag) {
                    System.out.println("Niepoprawne polozenie statku");
                }
            }

            board.printBoard();
        }
    }

    public void putThreeFlagShip(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_THREE_FLAG_SHIPS; i++) {
            System.out.println("Do postawienia zostalo " + (Game.NUMBER_OF_THREE_FLAG_SHIPS - i)
                    + " statkow trzy-masztowych");

            Scanner scan = new Scanner(System.in);

            boolean flag = true;
            while (flag) {

                List<Integer> listOfUserNavPoints = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    System.out.println("Gdzie postawic statek? Podaj punkt");
                    listOfUserNavPoints.add(playerInputToNavPoints(scan.nextLine()));
                }

                Collections.sort(listOfUserNavPoints);
                flag = !board.setThreeFlagShip(listOfUserNavPoints.get(0),
                        listOfUserNavPoints.get(1), listOfUserNavPoints.get(2));
                if (flag) {
                    System.out.println("Niepoprawne polozenie statku");
                }
            }

            board.printBoard();
        }
    }

    public void putFourFlagShip(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_FOUR_FLAG_SHIPS; i++) {
            System.out.println("Do postawienia zostalo " + (Game.NUMBER_OF_FOUR_FLAG_SHIPS - i)
                    + " statkow cztero-masztowych");

            Scanner scan = new Scanner(System.in);

            boolean flag = true;
            while (flag) {

                List<Integer> listOfUserNavPoints = new ArrayList<>();
                for (int j = 0; j < 4; j++) {
                    System.out.println("Gdzie postawic statek? Podaj punkt");
                    listOfUserNavPoints.add(playerInputToNavPoints(scan.nextLine()));
                }

                Collections.sort(listOfUserNavPoints);
                flag = !board.setFourFlagShip(
                        listOfUserNavPoints.get(0),
                        listOfUserNavPoints.get(1),
                        listOfUserNavPoints.get(2),
                        listOfUserNavPoints.get(3));
                if (flag) {
                    System.out.println("Niepoprawne polozenie statku");
                }
            }

            board.printBoard();
        }
    }

    public void putShipsOnBoard(Board board){
        putFourFlagShip(board);
        putThreeFlagShip(board);
        putTwoFlagShip(board);
        putOneFlagShip(board);

    }

    public void fire(Board board) {
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Oddaj STRZAL! podaj pole");
            board.printBoardOfHits();
            flag = board.hit(playerInputToNavPoints(scan.nextLine()));
            if (!board.areThereStillShips()){
                return;
            }
        }
    }
}
