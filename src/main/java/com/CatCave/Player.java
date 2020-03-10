package com.CatCave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Player {


    private List<String> playerInputToStringList(String input) {

        return Stream.of(input.trim()
                .toLowerCase()
                .split("\\W|(?<=\\d)(?=\\D)|(?=\\d)(?<=\\D)"))
                .filter(sign -> !sign.isBlank())
                .collect(Collectors.toList());
    }

    public int playerInputToNavPoints(String input) {

        List<String> list = playerInputToStringList(input);

        int tens = ((list.get(0).trim().charAt(0)) - 'a') * 10;
        int num = Integer.parseInt(list.get(1));

        return tens + num - 1;
    }


    public void putOneFlagShip(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_ONE_FLAG_SHIPS; i++) {
            System.out.println("Do postawienia zostało " + (Game.NUMBER_OF_ONE_FLAG_SHIPS - i)
                    + " statków jedno-masztowych");

            Scanner scan = new Scanner(System.in);

            boolean flag = true;
            while (flag) {

                System.out.println("Gdzie postawić statek? Podaj punkt");
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
            System.out.println("Do postawienia zostało " + (Game.NUMBER_OF_TWO_FLAG_SHIPS - i)
                    + " statków dwu-masztowych");

            Scanner scan = new Scanner(System.in);

            boolean flag = true;
            while (flag) {

                List<Integer> listOfUserNavPoints = new ArrayList<>();
                for (int j = 0; j < 2; j++) {
                    System.out.println("Gdzie postawić statek? Podaj punkt");
                    listOfUserNavPoints.add(playerInputToNavPoints(scan.nextLine()));
                }

                Collections.sort(listOfUserNavPoints);
                flag = !board.setTwoFlagShip(listOfUserNavPoints.get(0), listOfUserNavPoints.get(1));

                if (flag) {
                    System.out.println("Niepoprawne położenie statku");
                }
            }

            board.printBoard();
        }
    }

    public void putThreeFlagShip(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_THREE_FLAG_SHIPS; i++) {
            System.out.println("Do postawienia zostało " + (Game.NUMBER_OF_THREE_FLAG_SHIPS - i)
                    + " statków trzy-masztowych");

            Scanner scan = new Scanner(System.in);

            boolean flag = true;
            while (flag) {

                List<Integer> listOfUserNavPoints = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    System.out.println("Gdzie postawić statek? Podaj punkt");
                    listOfUserNavPoints.add(playerInputToNavPoints(scan.nextLine()));
                }

                Collections.sort(listOfUserNavPoints);
                flag = !board.setThreeFlagShip(listOfUserNavPoints.get(0),
                        listOfUserNavPoints.get(1), listOfUserNavPoints.get(2));
                if (flag) {
                    System.out.println("Niepoprawne położenie statku");
                }
            }

            board.printBoard();
        }
    }

    public void putFourFlagShip(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_FOUR_FLAG_SHIPS; i++) {
            System.out.println("Do postawienia zostało " + (Game.NUMBER_OF_FOUR_FLAG_SHIPS - i)
                    + " statków cztero-masztowych");

            Scanner scan = new Scanner(System.in);

            boolean flag = true;
            while (flag) {

                List<Integer> listOfUserNavPoints = new ArrayList<>();
                for (int j = 0; j < 4; j++) {
                    System.out.println("Gdzie postawić statek? Podaj punkt");
                    listOfUserNavPoints.add(playerInputToNavPoints(scan.nextLine()));
                }

                Collections.sort(listOfUserNavPoints);
                flag = !board.setFourFlagShip(
                        listOfUserNavPoints.get(0),
                        listOfUserNavPoints.get(1),
                        listOfUserNavPoints.get(2),
                        listOfUserNavPoints.get(3));
                if (flag) {
                    System.out.println("Niepoprawne położenie statku");
                }
            }

            board.printBoard();
        }
    }

    public void fire(Board board) {
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Oddaj STRZAŁ! podaj pole");
            flag = board.hit(playerInputToNavPoints(scan.nextLine()));
            board.printBoardOfHits();
        }
    }


}
