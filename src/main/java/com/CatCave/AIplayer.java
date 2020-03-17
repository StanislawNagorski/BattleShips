package com.CatCave;

import java.util.*;

public class AIplayer implements Player {

    private List<Integer> avaliableNavPointsToSetShip;
    private Random random;
    private RandomNav randomNav;

    public AIplayer() {
        random = new Random();
        randomNav = new RandomNav();
        avaliableNavPointsToSetShip = new ArrayList<>();
        int NUMBER_OF_NAV_POINTS = 100;
        for (int i = 0; i < NUMBER_OF_NAV_POINTS; i++) {
            avaliableNavPointsToSetShip.add(i);
        }
    }


    @Override
    public String getPlayerName() {
        return "Admirał PC";
    }

    private int buildRightOrDown() {
        return random.nextInt(2);
    }

    @Override
    public void putOneFlagShips(Board board) {

        for (int i = 0; i < Game.NUMBER_OF_ONE_FLAG_SHIPS; i++) {

            while (true) {
                Integer navPoint = randomNav.generateNavFromList(avaliableNavPointsToSetShip);
                if (board.setOneFlagShip(navPoint)){
                    avaliableNavPointsToSetShip.remove(navPoint);
                    break;
                }

            }
        }
    }

    @Override
    public void putTwoFlagShips(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_TWO_FLAG_SHIPS; i++) {

            while (true) {

                List<Integer> listOfNewShipNavPoints = new ArrayList<>();

                Integer navPoint = randomNav.generateNavFromList(avaliableNavPointsToSetShip);
                listOfNewShipNavPoints.add(navPoint);

                if (buildRightOrDown() == 0) {
                    listOfNewShipNavPoints.add( navPoint + 1);
                } else {
                    listOfNewShipNavPoints.add(navPoint + 10);
                }

                if (board.setTwoFlagShip(
                        listOfNewShipNavPoints.get(0),
                        listOfNewShipNavPoints.get(1))){
                    avaliableNavPointsToSetShip.remove(listOfNewShipNavPoints.get(0));
                    avaliableNavPointsToSetShip.remove(listOfNewShipNavPoints.get(1));
                    break;
                }

            }
        }

    }

    @Override
    public void putThreeFlagShips(Board board) {
        for (int i = 0; i < Game.NUMBER_OF_THREE_FLAG_SHIPS; i++) {

            while (true) {

                List<Integer> listOfNewShipNavPoints = new ArrayList<>();

                Integer navPoint = randomNav.generateNavFromList(avaliableNavPointsToSetShip);
                listOfNewShipNavPoints.add(navPoint);

                if (buildRightOrDown() == 0) {
                    listOfNewShipNavPoints.add(navPoint + 1);
                    listOfNewShipNavPoints.add(navPoint + 2);

                } else {
                    listOfNewShipNavPoints.add(navPoint + 10);
                    listOfNewShipNavPoints.add(navPoint + 20);

                }

                if(board.setThreeFlagShip(
                        listOfNewShipNavPoints.get(0),
                        listOfNewShipNavPoints.get(1),
                        listOfNewShipNavPoints.get(2))){
                    avaliableNavPointsToSetShip.remove(listOfNewShipNavPoints.get(0));
                    avaliableNavPointsToSetShip.remove(listOfNewShipNavPoints.get(1));
                    avaliableNavPointsToSetShip.remove(listOfNewShipNavPoints.get(2));
                    break;
                }

            }
        }

    }

    @Override
    public void putFourFlagShips(Board board) {

        for (int i = 0; i < Game.NUMBER_OF_FOUR_FLAG_SHIPS; i++) {

            while (true) {
                List<Integer> listOfNewShipNavPoints = new ArrayList<>();

                Integer navPoint = randomNav.generateNavFromList(avaliableNavPointsToSetShip);
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
                if (board.setFourFlagShip(
                        listOfNewShipNavPoints.get(0),
                        listOfNewShipNavPoints.get(1),
                        listOfNewShipNavPoints.get(2),
                        listOfNewShipNavPoints.get(3))){
                    avaliableNavPointsToSetShip.remove(listOfNewShipNavPoints.get(0));
                    avaliableNavPointsToSetShip.remove(listOfNewShipNavPoints.get(1));
                    avaliableNavPointsToSetShip.remove(listOfNewShipNavPoints.get(2));
                    avaliableNavPointsToSetShip.remove(listOfNewShipNavPoints.get(3));
                    break;
                }
            }
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

        while (true) {

            board.hit(randomNav.getNavPointToFire(board));
            board.printBoardOfHits();
            //jeśli trafione to do KOLEJKI dodaj +1 -1 +10 - 10
            //jeśli ponownie trafione to dodaj kierunek statku

            if (!board.areThereStillShips()) {
                return false;
            }
        }



    }
}
