package com.CatCave;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class PlayerTest {



    @Test
    @Parameters({"a_10, 9", "b-2, 11", "c    10, 29", "j10, 99", "a1, 0"})
    public void stringListToNavPoints(String input, int expectedNav) {
        //given
        Player p = new Player();
        //when
        int result = p.playerInputToNavPoints(input);
        //then
        Assertions.assertThat(result).isEqualTo(expectedNav);
    }

    @Test
    @Parameters({"a10,9", "b-2,11", "c   10   ,29", "j   10,99"})
    public void integrationOfProcessConvertingUserInputToNavPoints(String input, int expectedNav) {
        //given
        Player p = new Player();
        //when
       int result =p.playerInputToNavPoints(input);
        //then
        Assertions.assertThat(result).isEqualTo(expectedNav);
    }





}