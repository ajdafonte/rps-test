package com.tg.rpstest.infra.processor;

import com.tg.rpstest.infra.messages.LoginRequest;
import com.tg.rpstest.infra.messages.LoginResponse;
import com.tg.rpstest.infra.messages.Request;
import com.tg.rpstest.infra.messages.Response;
import com.tg.rpstest.infra.messages.ResponseStatus;
import com.tg.rpstest.server.ServerContext;
import com.tg.rpstest.server.domain.Player;
import com.tg.rpstest.server.domain.RegisteredPlayer;


/**
 *
 */
public class LoginMessageProcessor implements MessageProcessor
{
    @Override
    public Response processRequestMessage(final Request message, final ServerContext serverContext)
    {
        System.out.println("----- Process login message request -----");
        final LoginRequest msg = (LoginRequest) message;
        final String userNameToRegister = msg.getUserName();

        // check is possible to register user in server context
        final Player currentPlayer = serverContext.getCurrentPlayer();
        ResponseStatus status = ResponseStatus.NOK;
        String description = "Sorry, but there's already another mighty player registered.";
        if (currentPlayer == null)
        {
            serverContext.setCurrentPlayer(new RegisteredPlayer(userNameToRegister));
            status = ResponseStatus.OK;
            System.out.println(">> Username " + userNameToRegister + " was successfully logged in.");
            description = "Greetings mighty " + userNameToRegister;
        }
        else
        {
            System.out.println(">> There's another player already registered. Player: " + currentPlayer.toString());
        }

        return new LoginResponse(status, description);
    }

    @Override
    public void processResponseMessage(final Response message)
    {
        System.out.println("----- Process login message response -----");
        final LoginResponse response = (LoginResponse) message;
        System.out.println(response.getDescription());
    }
}
