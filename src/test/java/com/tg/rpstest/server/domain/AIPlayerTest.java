package com.tg.rpstest.server.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;


/**
 * AIPlayerTest class - Test AIPlayer class.
 */
public class AIPlayerTest
{
    private static final String MOCK_NAME1 = "Buzz";
    private static final String MOCK_NAME2 = "Buddy";
    private static final List<RPSMove> MOCK_MOVES1;
    private static final List<RPSMove> MOCK_MOVES2;

    static
    {
        MOCK_MOVES1 = Arrays.asList(RPSMove.ROCK, RPSMove.PAPER);
        MOCK_MOVES2 = Collections.singletonList(RPSMove.SCISSOR);
    }

    private static AIPlayer generateAIPlayer(final String name, final List<RPSMove> moves)
    {
        return new AIPlayer(name, moves);
    }

    @Test
    public void givenTwoEqualAIPlayers_whenCheckIfEquals_thenReturnOk()
    {
        // given
        final AIPlayer mockAIPlayer1 = generateAIPlayer(MOCK_NAME1, MOCK_MOVES1);
        final AIPlayer mockAIPlayer2 = generateAIPlayer(MOCK_NAME1, MOCK_MOVES1);

        // when + then
        assertEquals(mockAIPlayer1.hashCode(), mockAIPlayer2.hashCode());
        assertEquals(mockAIPlayer1, mockAIPlayer2);
    }

    @Test
    public void givenTwoDifferentAIPlayers_whenCheckIfEquals_thenReturnNok()
    {
        // given
        final AIPlayer mockAIPlayer1 = generateAIPlayer(MOCK_NAME1, MOCK_MOVES1);
        final AIPlayer mockAIPlayer2 = generateAIPlayer(MOCK_NAME2, MOCK_MOVES2);

        // when + then
        assertNotEquals(mockAIPlayer1.hashCode(), mockAIPlayer2.hashCode());
        assertNotEquals(mockAIPlayer1, mockAIPlayer2);
    }

    @Test
    public void givenAIPlayer_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final AIPlayer mockAIPlayer1 = generateAIPlayer(MOCK_NAME1, MOCK_MOVES1);
        final String expected = "AIPlayer{" +
            "name='" + MOCK_NAME1 + '\'' +
            ", type=" + PlayerType.AI +
            ", moves=" + MOCK_MOVES1 +
            '}';

        // when
        final String value = mockAIPlayer1.toString();

        // then
        assertEquals(expected, value);
    }
}
