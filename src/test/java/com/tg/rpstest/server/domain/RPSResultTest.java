package com.tg.rpstest.server.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * RPSResultTest class - Test RPSResult enum.
 */
public class RPSResultTest
{
    // test value
    @Test
    public void givenEnumValues_whenGetValue_returnCorrectValue()
    {
        assertEquals(0, RPSResult.TIE.getValue());
        assertEquals(1, RPSResult.PLAYER1_WIN.getValue());
        assertEquals(2, RPSResult.PLAYER2_WIN.getValue());
    }
}
