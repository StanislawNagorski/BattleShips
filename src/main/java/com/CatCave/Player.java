package com.CatCave;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Player {


  List<String> playerInputToStringList(String input){

        return Stream.of(input.trim()
                .toLowerCase()
                .split("\\W|(?<=\\d)(?=\\D)|(?=\\d)(?<=\\D)"))
                .filter(sign -> !sign.isBlank())
                .collect(Collectors.toList());

    }

    public int stringToNavPoints(String input){

        List<String> list = playerInputToStringList(input);

        int tens = ((list.get(0).charAt(0)) - 'a') * 10;
        int num = Integer.parseInt(list.get(1));

        return tens+num-1;
    }


}
