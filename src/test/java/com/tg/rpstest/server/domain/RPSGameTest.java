package com.tg.rpstest.server.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;


/**
 * RPSGameTest class - Test RPSGame class.
 */
public class RPSGameTest
{
    private static final int MOCK_NUM_ROUNDS = 3;
    private static final String MOCK_PLAYER_NAME1 = "buzz";
    private static final String MOCK_PLAYER_NAME2 = "AI-QWERTY";
    private static final String MOCK_PLAYER_NAME3 = "GUEST-ASDASD";
    private static final Player MOCK_PLAYER_REGISTERED;
    private static final Player MOCK_PLAYER_GUEST;
    private static final Player MOCK_PLAYER_AI;
    private static final List<RPSMove> MOCK_MOVES1;
    private static final List<RPSMove> MOCK_MOVES2;
    private static final RPSGame MOCK_GAME1;
    private static final RPSGame MOCK_GAME2;

    static
    {
        MOCK_MOVES1 = Arrays.asList(RPSMove.ROCK, RPSMove.ROCK, RPSMove.ROCK);
        MOCK_MOVES2 = Arrays.asList(RPSMove.SCISSOR, RPSMove.ROCK, RPSMove.PAPER);
        MOCK_PLAYER_REGISTERED = generatePlayer(PlayerType.REGISTERED, MOCK_PLAYER_NAME1, MOCK_MOVES1);
        MOCK_PLAYER_GUEST = generatePlayer(PlayerType.GUEST, MOCK_PLAYER_NAME3, MOCK_MOVES1);
        MOCK_PLAYER_AI = generatePlayer(PlayerType.AI, MOCK_PLAYER_NAME2, MOCK_MOVES2);
        MOCK_GAME1 = generateRPSGame(MOCK_NUM_ROUNDS, MOCK_PLAYER_REGISTERED);
        MOCK_GAME2 = generateRPSGame(MOCK_NUM_ROUNDS, MOCK_PLAYER_GUEST);
    }

    private static RPSGame generateRPSGame(final int numRounds, final Player player1)
    {
        return new RPSGame(numRounds, player1, MOCK_PLAYER_AI);
    }

    private static Player generatePlayer(final PlayerType playerType, final String name, final List<RPSMove> moves)
    {
        Player player = null;
        switch (playerType)
        {
            case AI:
            {
                player = new AIPlayer();
                break;
            }
            case GUEST:
            {
                player = new GuestPlayer();
                break;
            }
            case REGISTERED:
            {
                player = new RegisteredPlayer(name);
                break;
            }
        }
        player.setMoves(moves);

        return player;
    }

    private static List<RoundResult> generateRoundResults(final List<RPSMove> player1Moves, final List<RPSMove> player2Moves)
    {
        final List<RoundResult> results = new ArrayList<>();
        for (int i = 0; i < player1Moves.size(); i++)
        {
            final RPSMove player1Move = player1Moves.get(i);
            final RPSMove player2Move = player2Moves.get(i);
            final RPSResult result = (player1Move == player2Move) ? RPSResult.TIE :
                (player1Move.beats(player2Move) ? RPSResult.PLAYER1_WIN : RPSResult.PLAYER2_WIN);
            final RoundResult roundResult = new RoundResult(player1Move, player2Move, result);
            results.add(roundResult);
        }
        return results;
    }

    @Test
    public void givenTwoEqualRPSGames_whenCheckIfEquals_thenReturnOk()
    {
        // given
        final RPSGame mockRPSGame1 = MOCK_GAME1;
        final RPSGame mockRPSGame2 = MOCK_GAME1;

        // when + then
        assertEquals(mockRPSGame1.hashCode(), mockRPSGame2.hashCode());
        assertEquals(mockRPSGame1, mockRPSGame2);
    }

    @Test
    public void givenTwoDifferentRPSGames_whenCheckIfEquals_thenReturnNok()
    {
        // given
        final RPSGame mockRPSGame1 = MOCK_GAME1;
        final RPSGame mockRPSGame2 = MOCK_GAME2;

        // when + then
        assertNotEquals(mockRPSGame1.hashCode(), mockRPSGame2.hashCode());
        assertNotEquals(mockRPSGame1, mockRPSGame2);
    }

    @Test
    public void givenRPSGame_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final RPSGame mockRPSGame1 = MOCK_GAME1;
        final String expected = "RPSGame{" +
            "numRounds=" + MOCK_NUM_ROUNDS +
            ", player1=" + MOCK_PLAYER_REGISTERED +
            ", player2=" + MOCK_PLAYER_AI +
            '}';

        // when
        final String value = mockRPSGame1.toString();

        // then
        assertEquals(expected, value);
    }

    // test start - with several rounds and different wins
    @Test
    public void givenValidRPSGame_whenCallStart_thenReturnExpectedGameResult()
    {
        // given
        final RPSGame game = MOCK_GAME1;
        final List<RoundResult> expectedRoundResults = generateRoundResults(MOCK_MOVES1, MOCK_MOVES2);

        // when
        final GameResult result = game.start();

        // then
        assertNotNull(result);
        assertEquals(1, result.getClientWins());
        assertEquals(1, result.getNumTies());
        assertEquals(1, result.getServerWins());
        assertEquals(expectedRoundResults, result.getRoundResults());
    }
}
