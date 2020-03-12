package com.CatCave;

import java.util.*;

public class AIplayer implements Player {

    private List<Integer> avaliableNavPoints;
    private Random random;

    public AIplayer() {
        avaliableNavPoints = new ArrayList<>();
        random = new Random();
        int NUMBER_OF_NAV_POINTS = 100;
        for (int i = 0; i < NUMBER_OF_NAV_POINTS; i++) {
            avaliableNavPoints.add(i);
        }
    }

    @Override
    public String getPlayerName() {
        return "AdmirałPC";
    }

    public Integer generateNavPoint() {
        return avaliableNavPoints.get(random.nextInt(avaliableNavPoints.size()));
    }

    public int buildRightOrDown() {
        return random.nextInt(2);
    }

    @Override
    public void putOneFlagShip(Board board) {

        for (int i = 0; i < Game.NUMBER_OF_ONE_FLAG_SHIPS; i++) {

            boolean flag = true;
            while (flag) {
                Integer navPoint = generateNavPoint();
                flag = !board.setOneFlagShip(navPoint);

                if (!flag) {
                    avaliableNavPoints.remove(navPoint);
                }
            }
        }
    }

    @Override
    public void putTwoFlagShip(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_TWO_FLAG_SHIPS; i++) {

            boolean flag = true;
            while (flag) {

                List<Integer> listOfNewShipNavPoints = new ArrayList<>();

                Integer navPoint = generateNavPoint();
                listOfNewShipNavPoints.add(navPoint);

                if (buildRightOrDown() == 0) {
                    listOfNewShipNavPoints.add( navPoint + 1);
                } else {
                    listOfNewShipNavPoints.add(navPoint + 10);
                }

                flag = !board.setTwoFlagShip(listOfNewShipNavPoints.get(0), listOfNewShipNavPoints.get(1));

                if (!flag) {
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(0));
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(1));

                }
            }
        }

    }

    @Override
    public void putThreeFlagShip(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_THREE_FLAG_SHIPS; i++) {

            boolean flag = true;
            while (flag) {

                List<Integer> listOfNewShipNavPoints = new ArrayList<>();

                Integer navPoint = generateNavPoint();
                listOfNewShipNavPoints.add(navPoint);

                if (buildRightOrDown() == 0) {
                    listOfNewShipNavPoints.add(navPoint + 1);
                    listOfNewShipNavPoints.add(navPoint + 2);

                } else {
                    listOfNewShipNavPoints.add(navPoint + 10);
                    listOfNewShipNavPoints.add(navPoint + 20);

                }

                flag = !board.setThreeFlagShip(listOfNewShipNavPoints.get(0), listOfNewShipNavPoints.get(1),
                        listOfNewShipNavPoints.get(2));

                if (!flag) {
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(0));
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(1));
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(2));

                }
            }
        }

    }

    @Override
    public void putFourFlagShip(Board board) {

        for (int i = 0; i < Game.NUMBER_OF_FOUR_FLAG_SHIPS; i++) {

            boolean flag = true;
            while (flag) {

                List<Integer> listOfNewShipNavPoints = new ArrayList<>();

                Integer navPoint = generateNavPoint();
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
                flag = !board.setFourFlagShip(listOfNewShipNavPoints.get(0), listOfNewShipNavPoints.get(1),
                        listOfNewShipNavPoints.get(2), listOfNewShipNavPoints.get(3));

                if (!flag) {
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(0));
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(1));
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(2));
                    avaliableNavPoints.remove(listOfNewShipNavPoints.get(3));
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
