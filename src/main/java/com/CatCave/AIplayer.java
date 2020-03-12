package com.CatCave;

import java.util.*;

public class AIplayer implements Player {

    private List<Integer> avaliableNavPoints;
    private Random random;

    public AIplayer() {
        random = new Random();
        avaliableNavPoints = new ArrayList<>();
        int NUMBER_OF_NAV_POINTS = 100;
        for (int i = 0; i < NUMBER_OF_NAV_POINTS; i++) {
            avaliableNavPoints.add(i);
        }
    }


    @Override
    public String getPlayerName() {
        return "Admirał PC";
    }

    private Integer getRandomNavPointFromListOfStillNotUsed() {

        return avaliableNavPoints.get(random.nextInt(avaliableNavPoints.size()));
    }

    public int buildRightOrDown() {
        return random.nextInt(2);
    }

    @Override
    public void putOneFlagShip(Board board) {

        for (int i = 0; i < Game.NUMBER_OF_ONE_FLAG_SHIPS; i++) {

            while (true) {
                Integer navPoint = getRandomNavPointFromListOfStillNotUsed();
                if (board.setOneFlagShip(navPoint)){
                    avaliableNavPoints.remove(navPoint);
                    System.out.println("JEDNOMASZTOWY na " + navPoint);
                    break;
                }

            }
        }
    }

    @Override
    public void putTwoFlagShip(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_TWO_FLAG_SHIPS; i++) {

            while (true) {

                List<Integer> listOfNewShipNavPoints = new ArrayList<>();

                Integer navPoint = getRandomNavPointFromListOfStillNotUsed();
                listOfNewShipNavPoints.add(navPoint);

                if (buildRightOrDown() == 0) {
                    listOfNewShipNavPoints.add( navPoint + 1);
                } else {
                    listOfNewShipNavPoints.add(navPoint + 10);
                }

                if (board.setTwoFlagShip(listOfNewShipNavPoints.get(0), listOfNewShipNavPoints.get(1))){
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(0));
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(1));
                    System.out.println("DWUMASZTOWY na" + listOfNewShipNavPoints.get(0)+ "-"+ listOfNewShipNavPoints.get(1));
                    break;
                }

            }
        }

    }

    @Override
    public void putThreeFlagShip(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_THREE_FLAG_SHIPS; i++) {

            while (true) {

                List<Integer> listOfNewShipNavPoints = new ArrayList<>();

                Integer navPoint = getRandomNavPointFromListOfStillNotUsed();
                listOfNewShipNavPoints.add(navPoint);

                if (buildRightOrDown() == 0) {
                    listOfNewShipNavPoints.add(navPoint + 1);
                    listOfNewShipNavPoints.add(navPoint + 2);

                } else {
                    listOfNewShipNavPoints.add(navPoint + 10);
                    listOfNewShipNavPoints.add(navPoint + 20);

                }

                if(board.setThreeFlagShip(listOfNewShipNavPoints.get(0), listOfNewShipNavPoints.get(1),
                        listOfNewShipNavPoints.get(2))){
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(0));
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(1));
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(2));
                    System.out.println("TRZYMASZTOWY"  + listOfNewShipNavPoints.get(0)+ "-"+ listOfNewShipNavPoints.get(1) +
                            "-" + listOfNewShipNavPoints.get(2) + "- ");
                    break;
                }

            }
        }

    }

    @Override
    public void putFourFlagShip(Board board) {

        for (int i = 0; i < Game.NUMBER_OF_FOUR_FLAG_SHIPS; i++) {

            while (true) {
                List<Integer> listOfNewShipNavPoints = new ArrayList<>();

                Integer navPoint = getRandomNavPointFromListOfStillNotUsed();
                listOfNewShipNavPoints.add(navPoint);

                if (buildRightOrDown() == 0) {
                    listOfNewShipNavPoints.add(navPoint + 1);
                    listOfNewShipNavPoints.add(navPoint + 2);
                    listOfNewShipNavPoints.add(navPoint + 3);

                } else {
                    listOfNewShipNavPoints.add(navPoint + 10);
                    listOfNewShipNavPoints.add(navPoint + 20);
                    listOfNewShipNavPoints.add(navPoint + 30);
                }

                Collections.sort(listOfNewShipNavPoints);
                if (board.setFourFlagShip(listOfNewShipNavPoints.get(0), listOfNewShipNavPoints.get(1),
                        listOfNewShipNavPoints.get(2), listOfNewShipNavPoints.get(3))){
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(0));
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(1));
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(2));
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(3));
                    System.out.println("CZTEROMASZTOWY "+ listOfNewShipNavPoints.get(0)+ "-"+ listOfNewShipNavPoints.get(1) +
                            "-" + listOfNewShipNavPoints.get(2) + "- " + listOfNewShipNavPoints.get(3));
                    break;
                }
            }
        }

    }

    @Override
    public void putShipsOnBoard(Board board) {
        putFourFlagShip(board);
        putThreeFlagShip(board);
        putTwoFlagShip(board);
        putOneFlagShip(board);
    }

    @Override
    public void fire(Board board) {
        // użyj stosu aby przechowywać okoliczne punkty do strzelenia
    }
}
