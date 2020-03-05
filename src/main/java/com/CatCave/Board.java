package com.CatCave;


import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public class Board {

    private List<Mark> board;
    private List<Integer> listToCheckArea = Arrays.asList(-11, -10, -9, -1, 1, 9, 10, 11);
    private static final int BOARD_SIZE = 100;


    public Board() {
        board = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            board.add(Mark.EMPTY);
        }
    }

    private boolean thereIsShipNearby(int nav, List<Integer> list) {

        return list.stream()
                .filter(num -> (nav + num >= 0 && nav + num <= 99))
                .anyMatch(num -> (!board.get(nav + num).equals(Mark.EMPTY))
                );
    }

    public boolean setOneFlagShip(int nav, List<Integer> list) {
        if (nav < 0 || nav > 99) {
            return false;
        }

        if (!board.get(nav).equals(Mark.EMPTY)) {
            return false;
        }

        if (thereIsShipNearby(nav, list)) {
            return false;
        }

        board.set(nav, Mark.S);
        return true;
    }

    public boolean setTwoFlagShip(int nav, int nav2) {
        if (nav2 < 0 || nav2 > 99) {
            return false;
        }

        if (nav - nav2 == -11 || nav - nav2 == -9 || nav - nav2 == 11 || nav - nav2 == 9) {
            System.out.println("Diagonalne statki są niedozwolone");
            return false;
        }

        if (Math.abs(nav - nav2) == 10 || Math.abs(nav - nav2) == 1) {
            List<Integer> nextShipModulNavListCheck = new ArrayList<>();
            for (Integer integer : listToCheckArea) {
                if (integer != nav - nav2) {
                    nextShipModulNavListCheck.add(integer);
                }
            }

            if (!setOneFlagShip(nav, listToCheckArea)) {
                return false;
            }

            return setOneFlagShip(nav2, nextShipModulNavListCheck);
        }

        System.out.println("Maszty statku muszą być postawione obok siebie, inaczej to nie pływa...");
        return false;
    }

    public boolean setThreeFlagShip(int nav1, int nav2, int nav3) {
        if (nav2 - nav3 == -11 || nav2 - nav3 == -9 || nav2 - nav3 == 11 || nav2 - nav3 == 9) {
            System.out.println("Diagonalne statki są niedozwolone");
            return false;
        }

        if ((Math.abs(nav1 - nav2) == 10 && Math.abs(nav2 - nav3) != 10) || (Math.abs(nav1 - nav2) == 1 && Math.abs(nav2 - nav3) != 1)) {
            System.out.println("Łamane statki są niedozwolone");
            return false;
        }


        if (Math.abs(nav1 - nav2) == 10 || Math.abs(nav1 - nav2) == 1) {
            List<Integer> nextShipModulNavListCheck = new ArrayList<>();
            for (Integer integer : listToCheckArea) {
                if (integer != nav1 - nav2) {
                    nextShipModulNavListCheck.add(integer);
                }
            }

            if (!setTwoFlagShip(nav1, nav2)) {
                return false;
            }
            return setOneFlagShip(nav3, nextShipModulNavListCheck);
        }

        System.out.println("Maszty statku muszą być postawione obok siebie, inaczej to nie pływa...");
        return false;

    }

    public boolean setFourFlagShip(int nav1, int nav2, int nav3, int nav4) {

        if (nav3 - nav4 == -11 || nav3 - nav4 == -9 || nav3 - nav4 == 11 || nav3 - nav4 == 9) {
            System.out.println("Diagonalne statki są niedozwolone");
            return false;
        }

        if ((Math.abs(nav2 - nav3) == 10 && Math.abs(nav3 - nav4) != 10) || (Math.abs(nav2 - nav3) == 1 && Math.abs(nav3 - nav4) != 1)) {
            System.out.println("Łamane statki są niedozwolone");
            return false;
        }


        if (Math.abs(nav2 - nav3) == 10 || Math.abs(nav2 - nav3) == 1) {
            List<Integer> nextShipModulNavListCheck = new ArrayList<>();
            for (Integer integer : listToCheckArea) {
                if (integer != nav2 - nav3) {
                    nextShipModulNavListCheck.add(integer);
                }
            }

            if (!setThreeFlagShip(nav1, nav2, nav3)) {
                return false;
            }
            return setOneFlagShip(nav4, nextShipModulNavListCheck);
        }

        System.out.println("Maszty statku muszą być postawione obok siebie, inaczej to nie pływa...");
        return false;

    }


}
