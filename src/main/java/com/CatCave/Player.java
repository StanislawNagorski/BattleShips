package com.CatCave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Player {

    String getPlayerName();

    void putOneFlagShip(Board board);

    void putTwoFlagShip(Board board);

    void putThreeFlagShip(Board board);

    void putFourFlagShip(Board board);

    void putShipsOnBoard(Board board);

    void fire(Board board);

}
