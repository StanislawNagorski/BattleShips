package com.CatCave;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNav {

    private final Random random = new Random();

    public Integer generateNavFromList(List<Integer> listOfAvaliableNavPoints) {

        return listOfAvaliableNavPoints.get(random.nextInt(listOfAvaliableNavPoints.size()));
    }

    public Integer getNavPointToFire(Board board){
        List<Integer> navPointsToFire = new ArrayList<>();
        for (int i = 0; i < board.getBoard().size(); i++) {
            if (board.getBoard().get(i).equals(BoardMark.EMPTY)){
                navPointsToFire.add(i);
            }
        }
        return generateNavFromList(navPointsToFire);
    }




}
