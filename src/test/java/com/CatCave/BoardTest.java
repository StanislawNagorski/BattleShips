package com.CatCave;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class BoardTest {

    Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void shouldCreateArrayOf100FilledWithEMPTY() {
        //then
        assertThat(board.getBoard().size()).isEqualTo(100);
        assertThat(board.getBoard()).containsOnly(Mark.EMPTY);
    }

    @Test
    @Parameters({"-1", "111", "-48", "199"})
    public void shouldReturnFalseForOutOfBoundriesNav(int nav) {
        //when
        board.setOneFlagShipOnBoard(nav, board.getListToCheckArea());
        //then
        assertThat(board.setOneFlagShipOnBoard(nav, board.getListToCheckArea())).isFalse();
    }

    @Test
    @Parameters({"-1, 0", "99,100"})
    public void shouldReturnFalseForOutOfBoundriesFor2Flag(int nav1, int nav2) {

        //then
        assertThat(board.setTwoFlagShipOnBoard(nav1, nav2)).isFalse();
    }

    @Test
    @Parameters({"-1,0,1", "98,99,100"})
    public void shouldReturnFalseForOutOfBoundriesFor3Flag(int nav1, int nav2, int nav3) {
        //then
        assertThat(board.setThreeFlagShipOnBoard(nav1, nav2, nav3)).isFalse();
    }

    @Test
    @Parameters({"-1,0,1,2", "97,98,99,100"})
    public void shouldReturnFalseForOutOfBoundriesFor4Flag(int nav1, int nav2, int nav3, int nav4) {
        //then
        assertThat(board.setFourFlagShipOnBoard(nav1, nav2, nav3, nav4)).isFalse();
    }


    @Test
    @Parameters({"0", "11", "48", "99"})
    public void shouldPutOneWingShipOnBoard(int nav) {
        //when
        board.setOneFlagShipOnBoard(nav, board.getListToCheckArea());
        //then
        assertThat(board.getBoard().get(nav)).isEqualTo(Mark.S);
    }

    @Test
    @Parameters({"0", "11", "48", "99"})
    public void shouldReturnFalseIsBoardIsNotEmpty(int nav) {
        //given
        board.setOneFlagShipOnBoard(nav, board.getListToCheckArea());

        //when
        boolean result = board.setOneFlagShipOnBoard(nav, board.getListToCheckArea());

        //then
        assertThat(result).isFalse();

    }

    @Test
    @Parameters({"0,1,0,1", "10,11,11,12"," 98,99,89,99"})
    public void shouldReturnFalseIsBoardIsNotEmptyFor2FlagShip(int nav1, int nav2, int nav3, int nav4) {
        //given
     board.setTwoFlagShipOnBoard(nav1,nav2);

        //when
        boolean result = board.setTwoFlagShipOnBoard(nav3, nav4);

        //then
        assertThat(result).isFalse();

    }

    @Test
    @Parameters({"0", "11", "48", "99"})
    public void shouldReturnTrueIsBoardIsEmpty(int nav) {

        //when
        boolean result = board.setOneFlagShipOnBoard(nav, board.getListToCheckArea());

        //then
        assertThat(result).isTrue();
    }

    @Test
    @Parameters({"0", "10", "99"})
    public void shouldAllowToPutShipOnEdgesOfBoard(int nav) {


        //when
        boolean result = board.setOneFlagShipOnBoard(nav, board.getListToCheckArea());

        //then
        assertThat(result).isTrue();
    }

    @Test
    @Parameters({"10,0,1","0,1,2","1,2,12","33,55,44","77,88,99"})
    public void shouldReturnFalseWhenSettingShipToCloseToEachOther(int nav1, int nav2, int nav3) {

        //given
        board.setOneFlagShipOnBoard(nav1, board.getListToCheckArea());

        //when
        boolean result = board.setTwoFlagShipOnBoard(nav2,nav3);

        //then
        assertThat(result).isFalse();
    }

    @Test
    @Parameters({"0,1", "98,99", "50,51"})
    public void shouldAllowFor2FlagShip(int nav1, int nav2) {

        //when
        boolean result = board.setTwoFlagShipOnBoard(nav1, nav2);

        //then
        assertThat(result).isTrue();
        assertThat(board.getBoard().get(nav1)).isEqualTo(Mark.S);
        assertThat(board.getBoard().get(nav2)).isEqualTo(Mark.S);
    }

    @Test
    @Parameters({"0,11", "88,99", "40,51"})
    public void shouldReturnFalseForDiagonalShips(int nav1, int nav2) {

        //when
        boolean result = board.setTwoFlagShipOnBoard(nav1, nav2);

        //then
        assertThat(result).isFalse();
        assertThat(board.getBoard().get(nav1)).isEqualTo(Mark.EMPTY);
        assertThat(board.getBoard().get(nav2)).isEqualTo(Mark.EMPTY);
    }

    @Test
    @Parameters({"0,1,2", "97,98,99", "40,50,60"})
    public void shouldAllowFor3FlagShipInStraightLine(int nav1, int nav2, int nav3) {

        //when
        boolean result = board.setThreeFlagShipOnBoard(nav1, nav2, nav3);

        //then
        assertThat(result).isTrue();
        assertThat(board.getBoard().get(nav1)).isEqualTo(Mark.S);
        assertThat(board.getBoard().get(nav2)).isEqualTo(Mark.S);
        assertThat(board.getBoard().get(nav3)).isEqualTo(Mark.S);
    }

    @Test
    @Parameters({"0,1,12", "97,98,89", "50,61,62"})
    public void shouldReturnFalseFor3FlagShipInDiagonalLine(int nav1, int nav2, int nav3) {

        //when
        boolean result = board.setThreeFlagShipOnBoard(nav1, nav2, nav3);

        //then
        assertThat(result).isFalse();
        assertThat(board.getBoard().get(nav1)).isEqualTo(Mark.EMPTY);
        assertThat(board.getBoard().get(nav2)).isEqualTo(Mark.EMPTY);
        assertThat(board.getBoard().get(nav3)).isEqualTo(Mark.EMPTY);
    }

    @Test
    @Parameters({"0,1,11", "88,98,99", "40,50,51"})
    public void shouldReturnFalseFor3FlagShipForNotStraightLine(int nav1, int nav2, int nav3) {

        //when
        boolean result = board.setThreeFlagShipOnBoard(nav1, nav2, nav3);

        //then
        assertThat(result).isFalse();
        assertThat(board.getBoard().get(nav1)).isEqualTo(Mark.EMPTY);
        assertThat(board.getBoard().get(nav2)).isEqualTo(Mark.EMPTY);
        assertThat(board.getBoard().get(nav3)).isEqualTo(Mark.EMPTY);

    }

    @Test
    @Parameters({"0,2", "97,99", "49,51"})
    public void shouldReturnFalseIfNavPointsAreNotConnected(int nav1, int nav2) {

        //when
        boolean result = board.setTwoFlagShipOnBoard(nav1, nav2);

        //then
        assertThat(result).isFalse();
        assertThat(board.getBoard().get(nav1)).isEqualTo(Mark.EMPTY);
        assertThat(board.getBoard().get(nav2)).isEqualTo(Mark.EMPTY);
    }

    @Test
    @Parameters({"0,1,3", "97,98,100", "40,50,70"})
    public void shouldReturnFalseIfNavPointsAreNotConnectedFor3FlagShip(int nav1, int nav2, int nav3) {

        //when
        boolean result = board.setThreeFlagShipOnBoard(nav1, nav2, nav3);

        //then
        assertThat(result).isFalse();


    }

    @Test
    @Parameters({"0,1,2,3","10,20,30,40"})
    public void shouldReturnTrueFor4FlagShip(int nav1, int nav2, int nav3, int nav4){

        //when
        boolean result = board.setFourFlagShipOnBoard(nav1, nav2, nav3,nav4);

        //then
        assertThat(result).isTrue();
        assertThat(board.getBoard().get(nav1)).isEqualTo(Mark.S);
        assertThat(board.getBoard().get(nav2)).isEqualTo(Mark.S);
        assertThat(board.getBoard().get(nav3)).isEqualTo(Mark.S);
        assertThat(board.getBoard().get(nav4)).isEqualTo(Mark.S);

    }

    @Test
    @Parameters({"0,1,2,4", "95,96,97,99", "40,50,60,80"})
    public void shouldReturnFalseIfNavPointsAreNotConnectedFor4FlagShip(int nav1, int nav2, int nav3, int nav4) {

        //when
        boolean result = board.setFourFlagShipOnBoard(nav1, nav2, nav3, nav4);

        //then
        assertThat(result).isFalse();
        assertThat(board.getBoard().get(nav1)).isEqualTo(Mark.EMPTY);
        assertThat(board.getBoard().get(nav2)).isEqualTo(Mark.EMPTY);
        assertThat(board.getBoard().get(nav3)).isEqualTo(Mark.EMPTY);
        assertThat(board.getBoard().get(nav4)).isEqualTo(Mark.EMPTY);
    }

    @Test
    @Parameters({"0", "10", "55", "99"})
    public void shouldReturnTrueIfShipIsHit(int nav1){
        //given
        board.setOneFlagShipOnBoard(nav1,board.getListToCheckArea());
        //when
        boolean result = board.hit(nav1);
        //then
        assertThat(result).isTrue();
        assertThat(board.getBoard().get(nav1)).isEqualTo(Mark.HS);
    }

    @Test
    @Parameters({"0", "10", "55", "99"})
    public void shouldReturnFalseIsShipWasAlreadyHited(int nav1){
        //given
        board.setOneFlagShipOnBoard(nav1,board.getListToCheckArea());
        board.hit(nav1);
        //when
        boolean result = board.hit(nav1);
        //then
        assertThat(result).isFalse();
        assertThat(board.getBoard().get(nav1)).isEqualTo(Mark.HS);
    }

    @Test
    @Parameters({"0", "10", "55", "99"})
    public void shouldReturnFalseIsFieldWasAlreadyHited(int nav1){
        //given
        board.hit(nav1);
        //when
        boolean result = board.hit(nav1);
        //then
        assertThat(result).isFalse();
        assertThat(board.getBoard().get(nav1)).isEqualTo(Mark.X);
    }

    @Test
    @Parameters({"10,10", "35,35"})
    public void shouldReturnTrueIfOneFlagShipsIsSink(int nav1, int hit1){
        //given
        board.setOneFlagShip(nav1);

        //when
        board.hit(hit1);

        //then
        assertThat(board.getLisOfShips().get(0).isItSink()).isTrue();
    }

    @Test
    @Parameters({"10,11", "35,33"})
    public void shouldReturnFalseIfOneFlagShipsIsNotSink(int nav1, int hit1){
        //given
        board.setOneFlagShip(nav1);

        //when
        board.hit(hit1);

        //then
        assertThat(board.getLisOfShips().get(0).isItSink()).isFalse();
    }

    @Test
    @Parameters({"10,11,10,11", "35,45,45,35"})
    public void shouldReturnTrueIfTwoFlagShipsIsSink(int nav1, int nav2, int hit1, int hit2){
        //given
        board.setTwoFlagShip(nav1, nav2);

        //when
        board.hit(hit1);
        board.hit(hit2);

        //then
        assertThat(board.getLisOfShips().get(0).isItSink()).isTrue();
    }

    @Test
    @Parameters({"10,11,10,12", "35,45,45,55"})
    public void shouldReturnFalseIfTwoFlagShipsIsNotSink(int nav1, int nav2, int hit1, int hit2){
        //given
        board.setTwoFlagShip(nav1, nav2);

        //when
        board.hit(hit1);
        board.hit(hit2);

        //then
        assertThat(board.getLisOfShips().get(0).isItSink()).isFalse();
    }

    @Test
    @Parameters({"10,11,12,10,11,12", "35,45,55,45,55,35"})
    public void shouldReturnTrueIfThreeFlagShipsIsSink(int nav1, int nav2,int nav3, int hit1, int hit2, int hit3){
        //given
        board.setThreeFlagShip(nav1, nav2, nav3);

        //when
        board.hit(hit1);
        board.hit(hit2);
        board.hit(hit3);

        //then
        assertThat(board.getLisOfShips().get(0).isItSink()).isTrue();
    }

    @Test
    @Parameters({"10,11,12,10,11,13", "35,45,55,35,25,45"})
    public void shouldReturnFalseIfThreeFlagShipsIsNotSink(int nav1, int nav2,int nav3, int hit1, int hit2, int hit3){
        //given
        board.setThreeFlagShip(nav1, nav2, nav3);

        //when
        board.hit(hit1);
        board.hit(hit2);
        board.hit(hit3);

        //then
        assertThat(board.getLisOfShips().get(0).isItSink()).isFalse();
    }

    @Test
    @Parameters({"10,11,12,13,10,11,12,13", "35,45,55,65,45,55,65,35"})
    public void shouldReturnTrueIfFourFlagShipsIsSink(int nav1, int nav2,int nav3, int nav4,
                                                      int hit1, int hit2, int hit3, int hit4){
        //given
        board.setFourFlagShip(nav1, nav2, nav3, nav4);

        //when
        board.hit(hit1);
        board.hit(hit2);
        board.hit(hit3);
        board.hit(hit4);

        //then
        assertThat(board.getLisOfShips().get(0).isItSink()).isTrue();
    }

    @Test
    @Parameters({"10,11,12,13,10,9,11,12", "35,45,55,65,45,55,65,75"})
    public void shouldReturnFalseIfFourFlagShipsIsNotSink(int nav1, int nav2,int nav3, int nav4,
                                                          int hit1, int hit2, int hit3, int hit4){
        //given
        board.setFourFlagShip(nav1, nav2, nav3, nav4);

        //when
        board.hit(hit1);
        board.hit(hit2);
        board.hit(hit3);
        board.hit(hit4);

        //then
        assertThat(board.getLisOfShips().get(0).isItSink()).isFalse();
    }








}