package com.CatCave;


import com.CatCave.ships.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
public class Board {

    private List<Ship> lisOfShips;
    private List<Mark> board;
    private List<Integer> listToCheckArea = Arrays.asList(-11, -10, -9, -1, 1, 9, 10, 11);
    private static final int BOARD_SIZE = 100;


    public Board() {
        board = new ArrayList<>();
        lisOfShips = new ArrayList<>();
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
        return Math.abs(secondFromEndNav - lastNav) == 11 || Math.abs(secondFromEndNav - lastNav) == 9;
    }

    private boolean bendShip(int thirdFromEndNav, int secondFromEndNav, int lastNav) {
        return (Math.abs(thirdFromEndNav - secondFromEndNav) == 10 && Math.abs(secondFromEndNav - lastNav) != 10)
                || (Math.abs(thirdFromEndNav - secondFromEndNav) == 1 && Math.abs(secondFromEndNav - lastNav) != 1);

    }

    public boolean setOneFlagShipOnBoard(int nav1, List<Integer> list) {
        if (nav1 < 0 || nav1 > 99
                || !board.get(nav1).equals(Mark.EMPTY)
                || thereIsShipNearby(nav1, list)) {
            return false;
        }
        board.set(nav1, Mark.S);

        return true;
    }

    public boolean setOneFlagShip(int nav1) {
        if (setOneFlagShipOnBoard(nav1, listToCheckArea)) {
            lisOfShips.add(new Ship(nav1));
            return true;
        }
        return false;
    }

    public boolean setTwoFlagShipOnBoard(int nav1, int nav2) {
        if (diagonalShip(nav1, nav2)) {
            return false;
        }

        if (Math.abs(nav1 - nav2) == 10 || Math.abs(nav1 - nav2) == 1) {
            if (!setOneFlagShipOnBoard(nav1, listToCheckArea)) {
                return false;
            }

            return setOneFlagShipOnBoard(nav2, listToCheckArea.stream()
                    .filter(num -> num != nav1 - nav2)
                    .collect(Collectors.toList()));
        }
        return false;
    }

    public boolean setTwoFlagShip(int nav1, int nav2) {

        if (setTwoFlagShipOnBoard(nav1, nav2)) {
            lisOfShips.add(new Ship(nav1, nav2));
            return true;
        }
        return false;
    }


    public boolean setThreeFlagShipOnBoard(int nav1, int nav2, int nav3) {

        if (bendShip(nav1, nav2, nav3) || diagonalShip(nav2, nav3)) {
            return false;
        }

        if (Math.abs(nav1 - nav2) == 10 || Math.abs(nav1 - nav2) == 1) {
            if (!setTwoFlagShipOnBoard(nav1, nav2)) {
                return false;
            }

            return setOneFlagShipOnBoard(nav3, listToCheckArea.stream()
                    .filter(num -> num != nav1 - nav2)
                    .collect(Collectors.toList()));
        }
        return false;
    }

    public boolean setThreeFlagShip(int nav1, int nav2, int nav3) {

        if (setThreeFlagShipOnBoard(nav1, nav2, nav3)) {
            lisOfShips.add(new Ship(nav1, nav2, nav3));
            return true;
        }
        return false;
    }

    public boolean setFourFlagShipOnBoard(int nav1, int nav2, int nav3, int nav4) {

        if (bendShip(nav2, nav3, nav4) || diagonalShip(nav3, nav4)) {
            return false;
        }

        if (Math.abs(nav2 - nav3) == 10 || Math.abs(nav2 - nav3) == 1) {
            if (!setThreeFlagShipOnBoard(nav1, nav2, nav3)) {
                return false;
            }

            return setOneFlagShipOnBoard(nav4, listToCheckArea.stream()
                    .filter(num -> num != nav2 - nav3)
                    .collect(Collectors.toList()));
        }
        return false;
    }
    public boolean setFourFlagShip(int nav1, int nav2, int nav3, int nav4) {

        if (setFourFlagShipOnBoard(nav1, nav2, nav3, nav4)) {
            lisOfShips.add(new Ship(nav1, nav2, nav3, nav4));
            return true;
        }
        return false;
    }


    public boolean hit(int nav) {

        if (board.get(nav).equals(Mark.EMPTY)) {
            board.set(nav, Mark.X);
            return false;
        }

        if (board.get(nav).equals(Mark.X) || board.get(nav).equals(Mark.HS)) {
            return false;
        }

        if (board.get(nav).equals(Mark.S)) {
            lisOfShips.stream()
                    .filter(ship -> ship.getListofShipNavPoints().contains(nav))
                    .forEach(Ship::reduceHealthPointsByOne);

            board.set(nav, Mark.HS);

            return true;
        }
        return false;
    }
}
