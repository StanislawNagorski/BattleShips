package com.CatCave;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

//test nie działa


@RunWith(MockitoJUnitRunner.class)
public class AIplayerTest {

    Board board;
    List<BoardMark> boardList;
    AIplayer aIplayer;

    @Mock
    RandomNav randomNav;

    @Before
    public void setUp() {
        aIplayer = new AIplayer();
        board = new Board();
        boardList = board.getBoard();
    }

    @Test
    public void shouldPut4OneFlagShipsOnBoard() {

        //given

        aIplayer.putOneFlagShips(board);
        int shipCounter = 0;

        //when
        for (BoardMark boardMark : boardList) {
            if (boardMark.equals(BoardMark.S)) {
                shipCounter++;
            }
        }

        //then
        Assertions.assertThat(shipCounter).isEqualTo(4);
    }

    @Test
    public void shouldFireOnBoard() {
        //given
        Mockito.when(randomNav.getNavPointToFire(board)).thenReturn(5);
        board.setOneFlagShip(5);

        //when
        board.hit(randomNav.getNavPointToFire(board));

        //then
        Assertions.assertThat(boardList.get(5)).isEqualTo(BoardMark.O);
    }


}