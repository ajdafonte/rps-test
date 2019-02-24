package com.tg.rpstest.infra.processor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tg.rpstest.infra.messages.LogoutRequest;
import com.tg.rpstest.infra.messages.LogoutResponse;
import com.tg.rpstest.infra.messages.Request;
import com.tg.rpstest.infra.messages.Response;
import com.tg.rpstest.infra.messages.ResponseStatus;
import com.tg.rpstest.server.ServerContext;
import com.tg.rpstest.server.domain.RegisteredPlayer;


/**
 * LogoutMessageProcessorTest class - logoutMessageProcessor test class.
 */
public class LogoutMessageProcessorTest
{
    private LogoutMessageProcessor logoutMessageProcessor;

    @BeforeEach
    public void setUp()
    {
        this.logoutMessageProcessor = new LogoutMessageProcessor();
    }

    @Test
    public void givenLogoutRequestMessageWithUserAlreadyRegistered_whenProcessingRequest_thenReturnValidResponse()
    {
        // given
        final String mockUserName = "Buzz";
        final Request request = new LogoutRequest(mockUserName);
        final ServerContext serverContext = new ServerContext();
        serverContext.setCurrentPlayer(new RegisteredPlayer(mockUserName));
        final String expectedDescription = "Goodbye mighty " + mockUserName;

        // when
        final Response response = logoutMessageProcessor.processRequestMessage(request, serverContext);

        // then
        assertNotNull(response);
        assertThat(response, instanceOf(LogoutResponse.class));
        final LogoutResponse LogoutResponse = (LogoutResponse) response;
        assertEquals(LogoutResponse.getStatus(), ResponseStatus.OK);
        assertEquals(LogoutResponse.getDescription(), expectedDescription);
    }

    @Test
    public void givenLogoutRequestMessageWithNoUserRegistered_whenProcessingRequest_thenReturnInvalidValidResponse()
    {
        // given
        final String mockUserName = "Buzz";
        final Request request = new LogoutRequest(mockUserName);
        final ServerContext serverContext = new ServerContext();
        final String expectedDescription = "Sorry, but you're currently unregistered.";

        // when
        final Response response = logoutMessageProcessor.processRequestMessage(request, serverContext);

        // then
        assertNotNull(response);
        assertThat(response, instanceOf(LogoutResponse.class));
        final LogoutResponse LogoutResponse = (LogoutResponse) response;
        assertEquals(LogoutResponse.getStatus(), ResponseStatus.NOK);
        assertEquals(LogoutResponse.getDescription(), expectedDescription);
        assertNull(serverContext.getCurrentPlayer());
    }
}
