package com.CatCave;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class PlayerTest {

    @Test
    @Parameters({"a1,a,1", "b-2,b, 2", "c   9   ,c, 9", "j   10,j,10"})
    public void playerInputToStringList(String input, String aBC, String num) {
        //given
        Player p = new Player();
        //when
        List<String> result = p.playerInputToStringList(input);
        //then
        Assertions.assertThat(result.get(0)).isEqualTo(aBC);
        Assertions.assertThat(result.get(1)).isEqualTo(num);
    }


    @Test
    @Parameters({"a_10, 9", "b-2, 11", "c    10, 29", "j10, 99"})
    public void stringListToNavPoints(String input, int expectedNav) {
        //given
        Player p = new Player();
        //when
        int result = p.stringToNavPoints(input);
        //then
        Assertions.assertThat(result).isEqualTo(expectedNav);
    }

    @Test
    @Parameters({"a10,9", "b-2,11", "c   10   ,29", "j   10,99"})
    public void integrationOfProcessConvertingUserInputToNavPoints(String input, int expectedNav) {
        //given
        Player p = new Player();
        //when
       int result =p.stringToNavPoints(input);
        //then
        Assertions.assertThat(result).isEqualTo(expectedNav);
    }





}