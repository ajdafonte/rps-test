package com.tg.rpstest.server.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;


/**
 * RegisteredPlayerTest class - Test RegisteredPlayer class.
 */
public class RegisteredPlayerTest
{
    private static final String MOCK_NAME1 = "Buzz";
    private static final String MOCK_NAME2 = "Buddy";

    private static RegisteredPlayer generateRegisteredPlayer(final String name)
    {
        return new RegisteredPlayer(name);
    }

    @Test
    public void givenTwoEqualRegisteredPlayers_whenCheckIfEquals_thenReturnOk()
    {
        // given
        final RegisteredPlayer mockRegisteredPlayer1 = generateRegisteredPlayer(MOCK_NAME1);
        final RegisteredPlayer mockRegisteredPlayer2 = generateRegisteredPlayer(MOCK_NAME1);

        // when + then
        assertEquals(mockRegisteredPlayer1.hashCode(), mockRegisteredPlayer2.hashCode());
        assertEquals(mockRegisteredPlayer1, mockRegisteredPlayer2);
    }

    @Test
    public void givenTwoDifferentRegisteredPlayers_whenCheckIfEquals_thenReturnNok()
    {
        // given
        final RegisteredPlayer mockRegisteredPlayer1 = generateRegisteredPlayer(MOCK_NAME1);
        final RegisteredPlayer mockRegisteredPlayer2 = generateRegisteredPlayer(MOCK_NAME2);

        // when + then
        assertNotEquals(mockRegisteredPlayer1.hashCode(), mockRegisteredPlayer2.hashCode());
        assertNotEquals(mockRegisteredPlayer1, mockRegisteredPlayer2);
    }

    @Test
    public void givenRegisteredPlayer_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final RegisteredPlayer mockRegisteredPlayer1 = generateRegisteredPlayer(MOCK_NAME1);
        final String expected = "RegisteredPlayer{" +
            "name='" + MOCK_NAME1 + '\'' +
            ", type=" + PlayerType.REGISTERED +
            ", moves=" + null +
            '}';

        // when
        final String value = mockRegisteredPlayer1.toString();

        // then
        assertEquals(expected, value);
    }
}
