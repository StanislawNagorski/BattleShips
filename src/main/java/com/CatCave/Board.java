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

    private boolean thereIsShipNearby(int nav, List<Integer> list){

        boolean flag = false;

        for (Integer num : list) {
            if (nav+num < 0 || nav+num > 99){
                flag = false;

            } else if(!board.get(nav+num).equals(Mark.EMPTY)) {
                return true;
            }
        }
        return flag;
    }

    public boolean setOneFlagShip(int nav, List<Integer> list) {
        if (nav<0 || nav > 99){
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

    public boolean setTwoFlagShip(int nav, int nav2){

        if (nav-nav2 == -11 || nav-nav2 == -9 || nav-nav2 == 11 || nav-nav2 == 9){
            System.out.println("Diagonalne statki są niedozwolone");
            return false;
        }

        if (!setOneFlagShip(nav, listToCheckArea)){
            return false;
        }

        List<Integer> nextShipModulNavListCheck = new ArrayList<>();
        for (Integer integer : listToCheckArea) {
            if (integer != nav-nav2){
                nextShipModulNavListCheck.add(integer);
            }
        }

        return setOneFlagShip(nav2, nextShipModulNavListCheck);
    }

    public boolean setThreeFlagShip(int nav1, int nav2, int nav3){

        if (nav2-nav3 == -11 || nav2-nav3 == -9 || nav2-nav3 == 11 || nav2-nav3 == 9){
            System.out.println("Diagonalne statki są niedozwolone");
            return false;
        }

        if ((Math.abs(nav1-nav2) == 10 && Math.abs(nav2-nav3) != 10) || (Math.abs(nav1-nav2) == 1 && Math.abs(nav2-nav3) != 1)){
            System.out.println("Łamane statki są niedozwolone");
            return false;
        }

       if (!setTwoFlagShip(nav1, nav2)){
           return false;
       }

       //to wydzielić do metody?
        List<Integer> nextShipModulNavListCheck = new ArrayList<>();
        for (Integer integer : listToCheckArea) {
            if (integer != nav2-nav3){
                nextShipModulNavListCheck.add(integer);
            }
        }

        return setOneFlagShip(nav3, nextShipModulNavListCheck);

    }

    public boolean setFourFlagShip(int nav1, int nav2, int nav3, int nav4){

        return false;
    }




}
