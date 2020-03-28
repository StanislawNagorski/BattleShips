package com.CatCave;

public interface Player {

    String getPlayerName();

    void putOneFlagShips(Board board);

    void putTwoFlagShips(Board board);

    void putThreeFlagShips(Board board);

    void putFourFlagShips(Board board);

    void putShipsOnBoards(Board board);

    boolean fire(Board board);

}
