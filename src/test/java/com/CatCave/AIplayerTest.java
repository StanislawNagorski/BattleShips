package com.CatCave;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class AIplayerTest {

    Board board;
    List<BoardMark> boardList;

    @Mock
    AIplayer aIplayer;

    @Before
    public void setUp(){
        board = new Board();
        boardList= board.getBoard();
    }

    @Test
    public void shouldPutOneFlagShipOnBoard() {

        //given
        Mockito.when(aIplayer.generateNavPoint()).thenReturn(5);

        //when
       // System.out.println(aIplayer.avaliableNavPoints); //lista się nie generuje
        aIplayer.putOneFlagShip(board);

        //then

        System.out.println(boardList);
        Assertions.assertThat(boardList.get(5)).isEqualTo(BoardMark.S);

    }
}