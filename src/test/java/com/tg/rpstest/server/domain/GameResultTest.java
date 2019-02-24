package com.tg.rpstest.server.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;


/**
 * GameResultTest class - Test GameResultTest class.
 */
public class GameResultTest
{
    private static final int MOCK_CLIENT_WINS1 = 2;
    private static final int MOCK_CLIENT_WINS2 = 1;
    private static final int MOCK_SERVER_WINS1 = 1;
    private static final int MOCK_SERVER_WINS2 = 2;
    private static final int MOCK_NUM_TIES = 0;
    private static final RoundResult MOCK_ROUND_RESULT1;
    private static final RoundResult MOCK_ROUND_RESULT2;
    private static final List<RoundResult> MOCK_SET_ROUNDS1;
    private static final List<RoundResult> MOCK_SET_ROUNDS2;

    static
    {
        MOCK_ROUND_RESULT1 = new RoundResult(RPSMove.PAPER, RPSMove.ROCK, RPSResult.PLAYER1_WIN);
        MOCK_ROUND_RESULT2 = new RoundResult(RPSMove.ROCK, RPSMove.ROCK, RPSResult.TIE);
        MOCK_SET_ROUNDS1 = Arrays.asList(MOCK_ROUND_RESULT1, MOCK_ROUND_RESULT2);
        MOCK_SET_ROUNDS2 = Collections.singletonList(MOCK_ROUND_RESULT2);
    }

    @Test
    public void givenTwoEqualGameResults_whenCheckIfEquals_thenReturnOk()
    {
        // given
        final GameResult mockGameResult1 =
            generateGameResult(MOCK_CLIENT_WINS1, MOCK_NUM_TIES, MOCK_SERVER_WINS1, MOCK_SET_ROUNDS1);
        final GameResult mockGameResult2 = generateGameResult(MOCK_CLIENT_WINS1, MOCK_NUM_TIES, MOCK_SERVER_WINS1, MOCK_SET_ROUNDS1);

        // when + then
        assertEquals(mockGameResult1.hashCode(), mockGameResult2.hashCode());
        assertEquals(mockGameResult1, mockGameResult2);
    }

    @Test
    public void givenTwoDifferentGameResults_whenCheckIfEquals_thenReturnNok()
    {
        // given
        final GameResult mockGameResult1 = generateGameResult(MOCK_CLIENT_WINS1, MOCK_NUM_TIES, MOCK_SERVER_WINS1, MOCK_SET_ROUNDS1);
        final GameResult mockGameResult2 = generateGameResult(MOCK_CLIENT_WINS2, MOCK_NUM_TIES, MOCK_SERVER_WINS2, MOCK_SET_ROUNDS2);

        // when + then
        assertNotEquals(mockGameResult1.hashCode(), mockGameResult2.hashCode());
        assertNotEquals(mockGameResult1, mockGameResult2);
    }

    @Test
    public void givenGameResult_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final GameResult mockGameResult1 = generateGameResult(MOCK_CLIENT_WINS1, MOCK_NUM_TIES, MOCK_SERVER_WINS1, MOCK_SET_ROUNDS1);
        final String expected = "GameResult{" +
            "clientWins=" + MOCK_CLIENT_WINS1 +
            ", numTies=" + MOCK_NUM_TIES +
            ", serverWins=" + MOCK_SERVER_WINS1 +
            ", roundResults=" + MOCK_SET_ROUNDS1 +
            '}';

        // when
        final String value = mockGameResult1.toString();

        // then
        assertEquals(expected, value);
    }

    private static GameResult generateGameResult(int clientWins, int numTies, int serverWins, final List<RoundResult> roundResults)
    {
        final GameResult gameResult = new GameResult();

        while (clientWins > 0)
        {
            gameResult.incrementNumberOfClientWins();
            clientWins--;
        }

        while (numTies > 0)
        {
            gameResult.incrementNumberOfTies();
            numTies--;
        }

        while (serverWins > 0)
        {
            gameResult.incrementNumberOfServerWins();
            serverWins--;
        }

        gameResult.setRoundResults(roundResults);

        return gameResult;
    }
}
