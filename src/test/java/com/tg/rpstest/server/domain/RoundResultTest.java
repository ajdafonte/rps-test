package com.tg.rpstest.server.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;


/**
 * RoundResultTest class - Test RoundResult class.
 */
public class RoundResultTest
{
    private static final RPSMove MOCK_MOVE1 = RPSMove.ROCK;
    private static final RPSMove MOCK_MOVE2 = RPSMove.PAPER;
    private static final RPSResult MOCK_RESULT1 = RPSResult.PLAYER2_WIN;
    private static final RPSResult MOCK_RESULT2 = RPSResult.TIE;

    private static RoundResult generateRoundResult(final RPSMove clientMove, final RPSMove serverMove, final RPSResult result)
    {
        return new RoundResult(clientMove, serverMove, result);
    }

    @Test
    public void givenTwoEqualRoundResults_whenCheckIfEquals_thenReturnOk()
    {
        // given
        final RoundResult mockRoundResult1 = generateRoundResult(MOCK_MOVE1, MOCK_MOVE2, MOCK_RESULT1);
        final RoundResult mockRoundResult2 = generateRoundResult(MOCK_MOVE1, MOCK_MOVE2, MOCK_RESULT1);

        // when + then
        assertEquals(mockRoundResult1.hashCode(), mockRoundResult2.hashCode());
        assertEquals(mockRoundResult1, mockRoundResult2);
    }

    @Test
    public void givenTwoDifferentRoundResults_whenCheckIfEquals_thenReturnNok()
    {
        // given
        final RoundResult mockRoundResult1 = generateRoundResult(MOCK_MOVE1, MOCK_MOVE2, MOCK_RESULT1);
        final RoundResult mockRoundResult2 = generateRoundResult(MOCK_MOVE2, MOCK_MOVE2, MOCK_RESULT2);

        // when + then
        assertNotEquals(mockRoundResult1.hashCode(), mockRoundResult2.hashCode());
        assertNotEquals(mockRoundResult1, mockRoundResult2);
    }

    @Test
    public void givenRoundResult_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final RoundResult mockRoundResult1 = generateRoundResult(MOCK_MOVE1, MOCK_MOVE2, MOCK_RESULT1);
        final String expected = "RoundResult{" +
            "clientMove=" + MOCK_MOVE1 +
            ", serverMove=" + MOCK_MOVE2 +
            ", result=" + MOCK_RESULT1 +
            '}';

        // when
        final String value = mockRoundResult1.toString();

        // then
        assertEquals(expected, value);
    }
}
