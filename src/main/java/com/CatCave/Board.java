package com.CatCave;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Getter
@Setter
public class Board {

    private List<Ship> listOfShips;
    private List<BoardMark> board;
    private static final List<Integer> AREA_AROUND_NAV_POINT = Arrays.asList(-11, -10, -9, -1, 1, 9, 10, 11);
    private static final int BOARD_SIZE = 100;


    public Board() {
        listOfShips = new ArrayList<>();
        board = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            board.add(BoardMark.EMPTY);
        }
    }

    private boolean thereIsShipNearby(int nav, List<Integer> arenaAround) {

        return arenaAround.stream()
                .filter(num -> (nav + num >= 0 && nav + num <= 99))
                .filter(navAround -> {
                    if ((nav + 1) % 10 == 0) {
                        return navAround != 11 && navAround != 1 && navAround != -9;
                    }
                    if (nav % 10 == 0) {
                        return navAround != -11 && navAround != -1 && navAround != 9;
                    }
                    return true;
                })
                .anyMatch(num -> (!board.get(nav + num).equals(BoardMark.EMPTY))
                );
    }

    private boolean diagonalShip(int secondFromEndNav, int lastNav) {
        return Math.abs(secondFromEndNav - lastNav) == 11 || Math.abs(secondFromEndNav - lastNav) == 9;
    }

    private boolean bendShip(int thirdFromEndNav, int secondFromEndNav, int lastNav) {
        if (Game.ARE_BENDED_SHIP_ALLOWED) {
            return true;
        }
        return (Math.abs(thirdFromEndNav - secondFromEndNav) == 10 && Math.abs(secondFromEndNav - lastNav) != 10)
                || (Math.abs(thirdFromEndNav - secondFromEndNav) == 1 && Math.abs(secondFromEndNav - lastNav) != 1);

    }

    private boolean setOneFlagShipOnBoard(int nav1, List<Integer> areaAround) {
        if (nav1 < 0 || nav1 > 99
                || !board.get(nav1).equals(BoardMark.EMPTY)
                || thereIsShipNearby(nav1, areaAround)) {
            return false;
        }
        board.set(nav1, BoardMark.S);

        return true;
    }

    public boolean setOneFlagShip(int nav1) {
        if (setOneFlagShipOnBoard(nav1, AREA_AROUND_NAV_POINT)) {
            listOfShips.add(new Ship(nav1));
            return true;
        }
        return false;
    }

    private boolean isShipInStraightLine(int nav1, int nav2) {
        return Math.abs(nav1 - nav2) == 10 || (Math.abs(nav1 - nav2) == 1 && (nav1 / 10 == nav2 / 10));
    }

    private boolean isItForbiddenToPutThoseNavNextToEachOther(int nav1, int nav2){
        return     nav1 < 0 || nav1 > 99
                || nav2 < 0 || nav2 > 99
                || !board.get(nav1).equals(BoardMark.EMPTY)
                || !board.get(nav2).equals(BoardMark.EMPTY)
                || thereIsShipNearby(nav1, AREA_AROUND_NAV_POINT)
                || thereIsShipNearby(nav2, AREA_AROUND_NAV_POINT);
    }

    private boolean setTwoFlagShipOnBoard(int nav1, int nav2) {
        if (diagonalShip(nav1, nav2)) {
            return false;
        }

        if (isShipInStraightLine(nav1, nav2)) {
            if (isItForbiddenToPutThoseNavNextToEachOther(nav1, nav2)) {
                return false;
            } else {
                setOneFlagShipOnBoard(nav1, AREA_AROUND_NAV_POINT);
                return setOneFlagShipOnBoard(nav2, AREA_AROUND_NAV_POINT.stream()
                        .filter(num -> num != nav1 - nav2)
                        .collect(Collectors.toList()));
            }
        }
        return false;
    }

    public boolean setTwoFlagShip(int nav1, int nav2) {

        if (setTwoFlagShipOnBoard(nav1, nav2)) {
            listOfShips.add(new Ship(nav1, nav2));
            return true;
        }
        return false;
    }


    private boolean setThreeFlagShipOnBoard(int nav1, int nav2, int nav3) {

        if (bendShip(nav1, nav2, nav3) || diagonalShip(nav2, nav3)) {
            return false;
        }

        if (isShipInStraightLine(nav2, nav3)) {
            if (!setTwoFlagShipOnBoard(nav1, nav2)) {
                return false;
            }

            return setOneFlagShipOnBoard(nav3, AREA_AROUND_NAV_POINT.stream()
                    .filter(num -> num != nav1 - nav2)
                    .collect(Collectors.toList()));
        }
        return false;
    }

    public boolean setThreeFlagShip(int nav1, int nav2, int nav3) {

        if (setThreeFlagShipOnBoard(nav1, nav2, nav3)) {
            listOfShips.add(new Ship(nav1, nav2, nav3));

            return true;
        }
        return false;
    }

    private boolean setFourFlagShipOnBoard(int nav1, int nav2, int nav3, int nav4) {

        if (bendShip(nav2, nav3, nav4) || diagonalShip(nav3, nav4)) {
            return false;
        }

        if (isShipInStraightLine(nav3, nav4)) {
            if (!setThreeFlagShipOnBoard(nav1, nav2, nav3)) {
                return false;
            }

            return setOneFlagShipOnBoard(nav4, AREA_AROUND_NAV_POINT.stream()
                    .filter(num -> num != nav2 - nav3)
                    .collect(Collectors.toList()));
        }
        return false;
    }

    public boolean setFourFlagShip(int nav1, int nav2, int nav3, int nav4) {

        if (setFourFlagShipOnBoard(nav1, nav2, nav3, nav4)) {
            listOfShips.add(new Ship(nav1, nav2, nav3, nav4));

            return true;
        }
        return false;
    }


    public boolean hit(int nav) {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (nav < 0 || nav > 99){
            System.out.println("Nie właściwe współrzedne");
            return false;
        }

        if (board.get(nav).equals(BoardMark.EMPTY)) {
            board.set(nav, BoardMark.X);
            System.out.println("Pudło!");
            return false;
        }

        if (board.get(nav).equals(BoardMark.X) || board.get(nav).equals(BoardMark.O)) {
            return false;
        }


        //wydziel, może do playerów?
        if (board.get(nav).equals(BoardMark.S)) {
            listOfShips.stream()
                    .filter(ship -> ship.getListofShipNavPoints().contains(nav))
                    .forEach(ship -> {
                        ship.reduceHealthPointsByOne();
                        System.out.println("Trafiony!");
                        if (ship.isItSink()) {
                            markXAllAroundSinkedShip(ship);
                            System.out.println("TRAFIONY ZATOPIONY!");
                        }
                    });
            board.set(nav, BoardMark.O);

            return true;
        }
        return false;
    }

    public void markXAllAroundSinkedShip(Ship ship) {

        ship.getListofShipNavPoints()
                .forEach(shipNavPoint -> AREA_AROUND_NAV_POINT.stream()
                        .filter(navAround -> (shipNavPoint + navAround >= 0 && shipNavPoint + navAround <= 99))
                        .filter(navAround -> {
                            if ((shipNavPoint + 1) % 10 == 0) {
                                return navAround != 11 && navAround != 1 && navAround != -9;
                            }
                            if ((shipNavPoint) % 10 == 0) {
                                return navAround != -11 && navAround != -1 && navAround != 9;
                            }
                            return true;
                        })
                        .forEach(navAround -> {
                            if (board.get(shipNavPoint + navAround).equals(BoardMark.EMPTY)) {
                                board.set(shipNavPoint + navAround, BoardMark.X);
                            }
                        }));
    }


    public void printBoard() {

        char c = 'a';
        System.out.println(" | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10|");

        for (int i = 0; i < board.size(); i++) {
            c = boardPrintSetUp(c, i);

            if (board.get(i).equals(BoardMark.EMPTY)) {
                System.out.print("   |");
            } else {
                System.out.print(" " + board.get(i) + " |");
            }
        }
        System.out.println();
    }

    private char boardPrintSetUp(char c, int i) {
        if (i == 0) {
            System.out.print(c + "|");
            c++;
        }

        if (i != 0 && (i) % 10 == 0 && i < 99) {
            System.out.println();
            System.out.print(c + "|");
            c++;
        }
        return c;
    }

    public void printBoardOfHits() {

        char c = 'a';
        System.out.println(" | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10|");

        for (int i = 0; i < board.size(); i++) {
            c = boardPrintSetUp(c, i);

            if (board.get(i).equals(BoardMark.EMPTY) || board.get(i).equals(BoardMark.S)) {
                System.out.print("   |");
            } else if (board.get(i).equals(BoardMark.O)) {
                System.out.print(" \u00d8 |");
            } else {
                System.out.print(" " + board.get(i) + " |");
            }
        }
        System.out.println();
    }

    public void printBoardOfHitsWithHumanPlayerShipsVisiable() {

        char c = 'a';
        System.out.println(" | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10|");

        for (int i = 0; i < board.size(); i++) {
            c = boardPrintSetUp(c, i);

            if (board.get(i).equals(BoardMark.EMPTY)) {
                System.out.print("   |");
            } else if (board.get(i).equals(BoardMark.O)) {
                System.out.print(" \u00d8 |");
            } else {
                System.out.print(" " + board.get(i) + " |");
            }
        }
        System.out.println();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public boolean areThereStillShips() {
        return listOfShips.stream()
                .anyMatch(ship -> !ship.isItSink());
    }

}
