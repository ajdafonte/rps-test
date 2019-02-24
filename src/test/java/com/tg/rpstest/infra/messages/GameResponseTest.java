package com.tg.rpstest.infra.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.tg.rpstest.server.domain.GameResult;
import com.tg.rpstest.server.domain.RPSMove;
import com.tg.rpstest.server.domain.RPSResult;
import com.tg.rpstest.server.domain.RoundResult;


/**
 * GameResponseTest class - Test GameResponse class.
 */
public class GameResponseTest
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
    private static final GameResult MOCK_GAME_RESULT1;
    private static final GameResult MOCK_GAME_RESULT2;

    static
    {
        MOCK_ROUND_RESULT1 = new RoundResult(RPSMove.PAPER, RPSMove.ROCK, RPSResult.PLAYER1_WIN);
        MOCK_ROUND_RESULT2 = new RoundResult(RPSMove.ROCK, RPSMove.ROCK, RPSResult.TIE);
        MOCK_SET_ROUNDS1 = Arrays.asList(MOCK_ROUND_RESULT1, MOCK_ROUND_RESULT2);
        MOCK_SET_ROUNDS2 = Collections.singletonList(MOCK_ROUND_RESULT2);
        MOCK_GAME_RESULT1 = generateGameResult(MOCK_CLIENT_WINS1, MOCK_NUM_TIES, MOCK_SERVER_WINS1, MOCK_SET_ROUNDS1);
        MOCK_GAME_RESULT2 = generateGameResult(MOCK_CLIENT_WINS2, MOCK_NUM_TIES, MOCK_SERVER_WINS2, MOCK_SET_ROUNDS2);
    }

    private static GameResponse generateGameResponse(final ResponseStatus status, final GameResult gameResult)
    {
        return new GameResponse(status, gameResult);
    }

    @Test
    public void givenGameResponse_whenCheckTypeValue_thenReturnGameResponseType()
    {
        // given
        final GameResponse mockGameResponse1 = generateGameResponse(ResponseStatus.OK, MOCK_GAME_RESULT1);
        final MessageType expectedMessageType = MessageType.GAME_RESPONSE;

        // when + then
        assertEquals(expectedMessageType, mockGameResponse1.type);
    }

    @Test
    public void givenTwoDifferentGameResponses_whenCheckIfEquals_thenReturnNok()
    {
        // given
        final GameResponse mockGameResponse1 = generateGameResponse(ResponseStatus.OK, MOCK_GAME_RESULT1);
        final GameResponse mockGameResponse2 = generateGameResponse(ResponseStatus.OK, MOCK_GAME_RESULT2);

        // when + then
        assertNotEquals(mockGameResponse1.hashCode(), mockGameResponse2.hashCode());
        assertNotEquals(mockGameResponse1, mockGameResponse2);
    }

    @Test
    public void givenGameResponse_whenCallToString_thenCheckIfContainsExpectedValue()
    {
        // given
        final GameResponse mockGameResponse1 = generateGameResponse(ResponseStatus.OK, MOCK_GAME_RESULT1);
        final String[] expectedSubtrings = {
            "GameResponse{" +
                "gameResult=" + MOCK_GAME_RESULT1 +
                "} ", "type=" + MessageType.GAME_RESPONSE
        };

        // when
        final String value = mockGameResponse1.toString();

        // then
        assertTrue(Arrays.stream(expectedSubtrings)
            .allMatch(value::contains));
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
