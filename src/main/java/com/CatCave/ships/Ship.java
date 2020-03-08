package com.CatCave.ships;

import java.util.Arrays;
import java.util.List;

public class Ship {

    int healthPoints;
    List<Integer> listofShipNavPoints;


    public Ship(int nav1){
        this.healthPoints = 1;
        this.listofShipNavPoints = Arrays.asList(nav1);
    }
    public Ship(int nav1, int nav2){
        this.healthPoints = 2;
        this.listofShipNavPoints = Arrays.asList(nav1, nav2);
    }
    public Ship(int nav1, int nav2, int nav3){
        this.healthPoints = 3;
        this.listofShipNavPoints = Arrays.asList(nav1, nav2, nav3);
    }
    public Ship(int nav1, int nav2, int nav3, int nav4){
        this.healthPoints = 4;
        this.listofShipNavPoints = Arrays.asList(nav1, nav2, nav3, nav4);
    }

    public List<Integer> getListofShipNavPoints(){
        return this.listofShipNavPoints;
    }

    public void reduceHealthPointsByOne() {
        this.healthPoints -= 1;
    }

    public boolean isItSink() {
        return this.healthPoints <= 0;
    }
}
