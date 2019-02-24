package com.tg.rpstest.infra.processor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tg.rpstest.infra.messages.GameRequest;
import com.tg.rpstest.infra.messages.GameResponse;
import com.tg.rpstest.infra.messages.Request;
import com.tg.rpstest.infra.messages.Response;
import com.tg.rpstest.infra.messages.ResponseStatus;
import com.tg.rpstest.server.ServerContext;
import com.tg.rpstest.server.domain.RPSMove;
import com.tg.rpstest.server.domain.RegisteredPlayer;


/**
 * GameMessageProcessorTest class - GameMessageProcessor test class.
 */
public class GameMessageProcessorTest
{
    private GameMessageProcessor GameMessageProcessor;

    @BeforeEach
    public void setUp()
    {
        this.GameMessageProcessor = new GameMessageProcessor();
    }

    @Test
    public void givenGameRequestMessageWithNoUserRegistered_whenProcessingRequest_thenGuestPlayerAndReturnGameResult()
    {
        // given
        final int mockNumRounds = 2;
        final List<RPSMove> mockMoves = Arrays.asList(RPSMove.ROCK, RPSMove.PAPER);
        final Request request = new GameRequest(mockNumRounds, mockMoves);
        final ServerContext serverContext = new ServerContext();

        // when
        final Response response = GameMessageProcessor.processRequestMessage(request, serverContext);

        // then
        assertNotNull(response);
        assertThat(response, instanceOf(GameResponse.class));
        final GameResponse gameResponse = (GameResponse) response;
        assertEquals(gameResponse.getStatus(), ResponseStatus.OK);
        assertNotNull(gameResponse.getGameResult());
        assertNull(serverContext.getCurrentPlayer());
    }

    @Test
    public void givenGameRequestMessageWithUserRegistered_whenProcessingRequest_thenRegisteredPlayerAndReturnGameResult()
    {
        // given
        final int mockNumRounds = 2;
        final List<RPSMove> mockMoves = Arrays.asList(RPSMove.ROCK, RPSMove.PAPER);
        final String mockUserName = "Buddy";
        final Request request = new GameRequest(mockNumRounds, mockUserName, mockMoves);
        final ServerContext serverContext = new ServerContext();
        serverContext.setCurrentPlayer(new RegisteredPlayer(mockUserName));

        // when
        final Response response = GameMessageProcessor.processRequestMessage(request, serverContext);

        // then
        assertNotNull(response);
        assertThat(response, instanceOf(GameResponse.class));
        final GameResponse gameResponse = (GameResponse) response;
        assertEquals(gameResponse.getStatus(), ResponseStatus.OK);
        assertNotNull(gameResponse.getGameResult());
        assertNotNull(serverContext.getCurrentPlayer());
    }

    @Test
    public void givenGameRequestMessageWithOtherRegistered_whenProcessingRequest_thenReturnNullGameResult()
    {
        // given
        final int mockNumRounds = 2;
        final List<RPSMove> mockMoves = Arrays.asList(RPSMove.ROCK, RPSMove.PAPER);
        final String mockUserName = "Buddy";
        final String mockRegisteredUserName = "Buzz";
        final Request request = new GameRequest(mockNumRounds, mockUserName, mockMoves);
        final ServerContext serverContext = new ServerContext();
        serverContext.setCurrentPlayer(new RegisteredPlayer(mockRegisteredUserName));

        // when
        final Response response = GameMessageProcessor.processRequestMessage(request, serverContext);

        // then
        assertNotNull(response);
        assertThat(response, instanceOf(GameResponse.class));
        final GameResponse gameResponse = (GameResponse) response;
        assertEquals(gameResponse.getStatus(), ResponseStatus.NOK);
        assertNull(gameResponse.getGameResult());
        assertNotNull(serverContext.getCurrentPlayer());
    }
}
