package com.CatCave;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class AIplayer implements Player {

    private List<Integer> avaliableNavPointsToSetShip;
    private Random random;
    private RandomNav randomNav;
    private LinkedList<Integer> hitStack;
    private LinkedList<Integer> stackOfSuccesfullFires;

    public AIplayer() {
        random = new Random();
        randomNav = new RandomNav();
        avaliableNavPointsToSetShip = new ArrayList<>();
        int NUMBER_OF_NAV_POINTS = 100;
        for (int i = 0; i < NUMBER_OF_NAV_POINTS; i++) {
            avaliableNavPointsToSetShip.add(i);
        }
        hitStack = new LinkedList<>();
        stackOfSuccesfullFires = new LinkedList<>();
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
                if (board.setOneFlagShip(navPoint)) {
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
                    listOfNewShipNavPoints.add(navPoint + 1);
                } else {
                    listOfNewShipNavPoints.add(navPoint + 10);
                }

                if (board.setTwoFlagShip(
                        listOfNewShipNavPoints.get(0),
                        listOfNewShipNavPoints.get(1))) {
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

                if (board.setThreeFlagShip(
                        listOfNewShipNavPoints.get(0),
                        listOfNewShipNavPoints.get(1),
                        listOfNewShipNavPoints.get(2))) {
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
                        listOfNewShipNavPoints.get(3))) {
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



    private Integer navToFire(Board board) {
        Integer navPointToFire;

        if (!hitStack.isEmpty()) {

            while (!hitStack.isEmpty()) {
                navPointToFire = hitStack.pop();
                if (board.getBoard().get(navPointToFire).equals(BoardMark.S)
                        || board.getBoard().get(navPointToFire).equals(BoardMark.EMPTY)) {
                    return navPointToFire;
                }
            }
        }

        return randomNav.getNavPointToFire(board);
    }


    private void addPossibleHitNavs(Integer nav, Board board) {
        List<Integer> verticalAndHorizontal = Arrays.asList(-1, 1, -10, 10);
        surrondingTest(nav, board, verticalAndHorizontal);
    }

    private void addPossibleHitsVerticalOrHorizontal(Integer nav, Board board) {
        if (!Game.ARE_BENDED_SHIP_ALLOWED){
            return;
        }

        Integer lastSuccessfulFire = stackOfSuccesfullFires.pop();

        if (Math.abs(nav-lastSuccessfulFire)==10){
            List<Integer> vertical = Arrays.asList(-10, 10);
            surrondingTest(nav, board, vertical);
        }

        if (Math.abs(nav-lastSuccessfulFire)==1){
            List<Integer> horizontal = Arrays.asList(-1, 1);
            surrondingTest(nav, board, horizontal);
        }
    }

    private void surrondingTest(Integer nav, Board board, List<Integer> horizontalOrVerticals) {
        for (Integer possibleHit : horizontalOrVerticals) {
            if ((nav + possibleHit >= 0 && nav + possibleHit <= 99)
                    && (board.getBoard().get(nav + possibleHit).equals(BoardMark.S)
                    || board.getBoard().get(nav + possibleHit).equals(BoardMark.EMPTY))) {

                hitStack.push(nav + possibleHit);
            }
        }
    }

    private String computerInputToCoordinators (Integer aIinput) {
        char tens = (char) ((aIinput/10) + 'a');
        return tens + String.valueOf((aIinput%10)+1);
    }

    @Override
    public boolean fire(Board board) {

        Integer navPointToFire;

        boolean hit = true;
        while (hit) {
            navPointToFire = navToFire(board);
            System.out.println(getPlayerName() + "strzela w pole: "
                    + computerInputToCoordinators(navPointToFire));
            hit = board.hit(navPointToFire);
            board.printBoardOfHitsWithHumanPlayerShipsVisiable();
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (hit) {
                if (!stackOfSuccesfullFires.isEmpty()){
                    addPossibleHitsVerticalOrHorizontal(navPointToFire, board);
                } else {
                    addPossibleHitNavs(navPointToFire, board);
                    stackOfSuccesfullFires.push(navPointToFire);
                }

            }

            if (!board.areThereStillShips()) {
                return false;
            }
        }
        return false;
    }

}
