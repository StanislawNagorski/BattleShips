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

    private boolean diagonalShip(int secondFromEndNav, int lastNav) {
        if (Math.abs(secondFromEndNav - lastNav) == 11 || Math.abs(secondFromEndNav - lastNav) == 9) {
            System.out.println("Niedozwolone miejsce");
            return true;
        } else {
            return false;
        }
    }

    private boolean bendShip(int thirdFromEndNav, int secondFromEndNav, int lastNav) {
        if (
                (Math.abs(thirdFromEndNav - secondFromEndNav) == 10 && Math.abs(secondFromEndNav - lastNav) != 10)
                        || (Math.abs(thirdFromEndNav - secondFromEndNav) == 1 && Math.abs(secondFromEndNav - lastNav) != 1)) {
            System.out.println("Niedozwolone miejsce");
            return true;
        } else {
            return false;
        }
    }

    public boolean setOneFlagShip(int nav1, List<Integer> list) {
        if (nav1 < 0 || nav1 > 99
                || !board.get(nav1).equals(Mark.EMPTY)
                || thereIsShipNearby(nav1, list)) {
            return false;
        }

        board.set(nav1, Mark.S);
        return true;
    }

    public boolean setTwoFlagShip(int nav1, int nav2) {
        if (diagonalShip(nav1, nav2)) {
            System.out.println("Niedozwolone miejsce");
            return false;
        }

        if (Math.abs(nav1 - nav2) == 10 || Math.abs(nav1 - nav2) == 1) {
            if (!setOneFlagShip(nav1, listToCheckArea)) {
                return false;
            }

            return setOneFlagShip(nav2, listToCheckArea.stream()
                    .filter(num -> num != nav1 - nav2)
                    .collect(Collectors.toList()));

        }

        System.out.println("Aby statek był integralny, jego części muszą pływać razem :)");
        return false;
    }


    public boolean setThreeFlagShip(int nav1, int nav2, int nav3) {

        if (bendShip(nav1, nav2, nav3) || diagonalShip(nav2, nav3)) {
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

        if (bendShip(nav2, nav3, nav4) || diagonalShip(nav3, nav4)) {
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
