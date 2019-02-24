package com.tg.rpstest.server.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;


/**
 * GuestPlayerTest class - Test GuestPlayer class.
 */
public class GuestPlayerTest
{
    private static final String MOCK_NAME1 = "Buzz";
    private static final String MOCK_NAME2 = "Buddy";
    private static final List<RPSMove> MOCK_MOVES1;

    static
    {
        MOCK_MOVES1 = Arrays.asList(RPSMove.ROCK, RPSMove.PAPER);
    }

    private static GuestPlayer generateGuestPlayer(final String name, final List<RPSMove> moves)
    {
        return new GuestPlayer(name, moves);
    }

    @Test
    public void givenTwoEqualGuestPlayers_whenCheckIfEquals_thenReturnOk()
    {
        // given
        final GuestPlayer mockGuestPlayer1 = generateGuestPlayer(MOCK_NAME1, MOCK_MOVES1);
        final GuestPlayer mockGuestPlayer2 = generateGuestPlayer(MOCK_NAME1, MOCK_MOVES1);

        // when + then
        assertEquals(mockGuestPlayer1.hashCode(), mockGuestPlayer2.hashCode());
        assertEquals(mockGuestPlayer1, mockGuestPlayer2);
    }

    @Test
    public void givenTwoDifferentGuestPlayers_whenCheckIfEquals_thenReturnNok()
    {
        // given
        final GuestPlayer mockGuestPlayer1 = generateGuestPlayer(MOCK_NAME1, MOCK_MOVES1);
        final GuestPlayer mockGuestPlayer2 = generateGuestPlayer(MOCK_NAME2, MOCK_MOVES1);

        // when + then
        assertNotEquals(mockGuestPlayer1.hashCode(), mockGuestPlayer2.hashCode());
        assertNotEquals(mockGuestPlayer1, mockGuestPlayer2);
    }

    @Test
    public void givenGuestPlayer_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final GuestPlayer mockGuestPlayer1 = generateGuestPlayer(MOCK_NAME1, MOCK_MOVES1);
        final String expected = "GuestPlayer{" +
            "name='" + MOCK_NAME1 + '\'' +
            ", type=" + PlayerType.GUEST +
            ", moves=" + MOCK_MOVES1 +
            '}';

        // when
        final String value = mockGuestPlayer1.toString();

        // then
        assertEquals(expected, value);
    }
}
