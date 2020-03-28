package com.CatCave;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class HumanPlayerTest {



    @Test
    @Parameters({"a_10, 9", "b-2, 11", "c    10, 29", "j10, 99", "a1, 0"})
    public void stringListToNavPoints(String input, int expectedNav) {
        //given
        HumanPlayer p = new HumanPlayer(Game.PLAYER_ONE_NAME);
        //when
        int result = p.playerInputToNavPoints(input);
        //then
        Assertions.assertThat(result).isEqualTo(expectedNav);
    }

    @Test
    @Parameters({"a10,9", "b-2,11", "c   10   ,29", "j   10,99"})
    public void integrationOfProcessConvertingUserInputToNavPoints(String input, int expectedNav) {
        //given
        HumanPlayer p = new HumanPlayer(Game.PLAYER_ONE_NAME);
        //when
       int result =p.playerInputToNavPoints(input);
        //then
        Assertions.assertThat(result).isEqualTo(expectedNav);
    }





}