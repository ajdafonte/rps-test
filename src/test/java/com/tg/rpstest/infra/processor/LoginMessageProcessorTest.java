package com.tg.rpstest.infra.processor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tg.rpstest.infra.messages.LoginRequest;
import com.tg.rpstest.infra.messages.LoginResponse;
import com.tg.rpstest.infra.messages.Request;
import com.tg.rpstest.infra.messages.Response;
import com.tg.rpstest.infra.messages.ResponseStatus;
import com.tg.rpstest.server.ServerContext;
import com.tg.rpstest.server.domain.RegisteredPlayer;


/**
 * LoginMessageProcessorTest class - LoginMessageProcessor test class.
 */
public class LoginMessageProcessorTest
{
    private LoginMessageProcessor loginMessageProcessor;

    @BeforeEach
    public void setUp()
    {
        this.loginMessageProcessor = new LoginMessageProcessor();
    }

    @Test
    public void givenLoginRequestMessageWithNoUserRegistered_whenProcessingRequest_thenReturnValidResponse()
    {
        // given
        final String mockUserName = "Buzz";
        final Request request = new LoginRequest(mockUserName);
        final ServerContext serverContext = new ServerContext();
        final String expectedDescription = "Greetings mighty " + mockUserName;

        // when
        final Response response = loginMessageProcessor.processRequestMessage(request, serverContext);

        // then
        assertNotNull(response);
        assertThat(response, instanceOf(LoginResponse.class));
        final LoginResponse loginResponse = (LoginResponse) response;
        assertEquals(loginResponse.getStatus(), ResponseStatus.OK);
        assertEquals(loginResponse.getDescription(), expectedDescription);
    }

    @Test
    public void givenLoginRequestMessageWithUserAlreadyRegistered_whenProcessingRequest_thenReturnInvalidValidResponse()
    {
        // given
        final String mockUserName = "Buzz";
        final Request request = new LoginRequest(mockUserName);
        final ServerContext serverContext = new ServerContext();
        serverContext.setCurrentPlayer(new RegisteredPlayer(mockUserName));
        final String expectedDescription = "Sorry, but there's already another mighty player registered.";

        // when
        final Response response = loginMessageProcessor.processRequestMessage(request, serverContext);

        // then
        assertNotNull(response);
        assertThat(response, instanceOf(LoginResponse.class));
        final LoginResponse loginResponse = (LoginResponse) response;
        assertEquals(loginResponse.getStatus(), ResponseStatus.NOK);
        assertEquals(loginResponse.getDescription(), expectedDescription);
    }
}
