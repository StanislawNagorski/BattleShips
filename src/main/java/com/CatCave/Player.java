package com.CatCave;

public interface Player {

    String getPlayerName();

    void putOneFlagShip(Board board);

    void putTwoFlagShip(Board board);

    void putThreeFlagShip(Board board);

    void putFourFlagShip(Board board);

    void putShipsOnBoard(Board board);

    void fire(Board board);

}
