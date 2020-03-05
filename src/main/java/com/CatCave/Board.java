package com.CatCave;


import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        if (nav < 0 || nav > 99
                || !board.get(nav).equals(Mark.EMPTY)
                || thereIsShipNearby(nav, list)) {
            return false;
        }

        board.set(nav, Mark.S);
        return true;
    }

    public boolean setTwoFlagShip(int nav, int nav2) {
        if (nav2 < 0 || nav2 > 99
                || Math.abs(nav - nav2) == 11 || Math.abs(nav - nav2) == 9) {
            System.out.println("Niedozwolone miejsce");
            return false;
        }

        if (Math.abs(nav - nav2) == 10 || Math.abs(nav - nav2) == 1) {
            if (!setOneFlagShip(nav, listToCheckArea)) {
                return false;
            }

            return setOneFlagShip(nav2, listToCheckArea.stream()
                    .filter(num -> num != nav - nav2)
                    .collect(Collectors.toList()));

        }

        System.out.println("Aby statek był integralny, jego części muszą pływać razem :)");
        return false;
    }

    private boolean diagonalOrBendShip(int thirdFromEndNav, int secondFromEndNav, int lastNav){
        if (Math.abs(secondFromEndNav - lastNav) == 11 || Math.abs(secondFromEndNav - lastNav) == 9
                || (Math.abs(thirdFromEndNav - secondFromEndNav) == 10 && Math.abs(secondFromEndNav - lastNav) != 10)
                || (Math.abs(thirdFromEndNav - secondFromEndNav) == 1 && Math.abs(secondFromEndNav - lastNav) != 1)
        ) {
            System.out.println("Niedozwolone miejsce");
            return true;
        } else {
            return false;
        }
    }

    public boolean setThreeFlagShip(int nav1, int nav2, int nav3) {

        if (diagonalOrBendShip(nav1, nav2, nav3)){
            return false;
        }

        if (Math.abs(nav1 - nav2) == 10 || Math.abs(nav1 - nav2) == 1) {
            if (!setTwoFlagShip(nav1, nav2)) {
                return false;
            }

            return setOneFlagShip(nav3, listToCheckArea.stream()
                    .filter(num -> num != nav1 - nav2)
                    .collect(Collectors.toList()));
        }

        System.out.println("Aby statek był integralny, jego części muszą pływać razem :)");
        return false;

    }

    public boolean setFourFlagShip(int nav1, int nav2, int nav3, int nav4) {

        if (diagonalOrBendShip(nav2, nav3, nav4)){
            return false;
        }

        if (Math.abs(nav2 - nav3) == 10 || Math.abs(nav2 - nav3) == 1) {
            if (!setThreeFlagShip(nav1, nav2, nav3)) {
                return false;
            }

            return setOneFlagShip(nav4, listToCheckArea.stream()
            .filter(num -> num != nav2 - nav3)
            .collect(Collectors.toList()));
        }

        System.out.println("Aby statek był integralny, jego części muszą pływać razem :)");
        return false;

    }


}
