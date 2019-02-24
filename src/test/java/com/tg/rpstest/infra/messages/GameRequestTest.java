package com.tg.rpstest.infra.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.tg.rpstest.server.domain.RPSMove;


/**
 * GameRequestTest class - Test GameRequest class.
 */
public class GameRequestTest
{
    private static final int MOCK_NUM_ROUNDS1 = 3;
    private static final int MOCK_NUM_ROUNDS2 = 2;
    private static final String MOCK_PLAYER_NAME = "Buzz";
    private static final List<RPSMove> MOCK_MOVES1;
    private static final List<RPSMove> MOCK_MOVES2;

    static
    {
        MOCK_MOVES1 = Arrays.asList(RPSMove.ROCK, RPSMove.ROCK, RPSMove.ROCK);
        MOCK_MOVES2 = Arrays.asList(RPSMove.SCISSOR, RPSMove.ROCK, RPSMove.PAPER);
    }

    private static GameRequest generateGameRequest(final int numRounds, final String playerName, final List<RPSMove> moves)
    {
        return new GameRequest(numRounds, playerName, moves);
    }

    private static GameRequest generateGameRequest(final int numRounds, final List<RPSMove> moves)
    {
        return new GameRequest(numRounds, moves);
    }

    @Test
    public void givenGameRequest_whenCheckTypeValue_thenReturnGameRequestType()
    {
        // given
        final GameRequest mockGameRequest1 = generateGameRequest(MOCK_NUM_ROUNDS1, MOCK_PLAYER_NAME, MOCK_MOVES1);
        final MessageType expectedMessageType = MessageType.GAME_REQUEST;

        // when + then
        assertEquals(expectedMessageType, mockGameRequest1.type);
    }

    @Test
    public void givenTwoDifferentGameRequests_whenCheckIfEquals_thenReturnNok()
    {
        // given
        final GameRequest mockGameRequest1 = generateGameRequest(MOCK_NUM_ROUNDS1, MOCK_PLAYER_NAME, MOCK_MOVES1);
        final GameRequest mockGameRequest2 = generateGameRequest(MOCK_NUM_ROUNDS2, MOCK_MOVES2);

        // when + then
        assertNotEquals(mockGameRequest1.hashCode(), mockGameRequest2.hashCode());
        assertNotEquals(mockGameRequest1, mockGameRequest2);
    }

    @Test
    public void givenGameRequest_whenCallToString_thenCheckIfContainsExpectedValue()
    {
        // given
        final GameRequest mockGameRequest1 = generateGameRequest(MOCK_NUM_ROUNDS1, MOCK_PLAYER_NAME, MOCK_MOVES1);
        final String[] expectedSubtrings = {
            "GameRequest{" +
                "numRounds=" + MOCK_NUM_ROUNDS1 +
                ", playerName='" + MOCK_PLAYER_NAME + '\'' +
                ", moves=" + MOCK_MOVES1 +
                "} ", "type=" + MessageType.GAME_REQUEST
        };

        // when
        final String value = mockGameRequest1.toString();

        // then
        assertTrue(Arrays.stream(expectedSubtrings)
            .allMatch(value::contains));
    }
}
