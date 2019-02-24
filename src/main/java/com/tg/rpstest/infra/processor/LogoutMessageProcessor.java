package com.tg.rpstest.infra.processor;

import com.tg.rpstest.infra.messages.LogoutRequest;
import com.tg.rpstest.infra.messages.LogoutResponse;
import com.tg.rpstest.infra.messages.Request;
import com.tg.rpstest.infra.messages.Response;
import com.tg.rpstest.infra.messages.ResponseStatus;
import com.tg.rpstest.server.ServerContext;
import com.tg.rpstest.server.domain.Player;


/**
 *
 */
public class LogoutMessageProcessor implements MessageProcessor
{
    @Override
    public Response processRequestMessage(final Request message, final ServerContext serverContext)
    {
        System.out.println("----- Process logout message request -----");
        final LogoutRequest msg = (LogoutRequest) message;
        final String userNameToLogout = msg.getUserName();

        // check is possible to unregister user in server context
        final Player currentPlayer = serverContext.getCurrentPlayer();
        ResponseStatus status = ResponseStatus.NOK;
        String description = "Sorry, but you're currently unregistered.";
        if (currentPlayer != null && currentPlayer.getName().equals(userNameToLogout))
        {
            serverContext.setCurrentPlayer(null);
            status = ResponseStatus.OK;
            System.out.println(">> Username " + userNameToLogout + " was successfully logged out.");
            description = "Goodbye mighty " + userNameToLogout;
        }
        else
        {
            System.out.println(">> Username " + userNameToLogout + " is not currently logged in.");
        }

        return new LogoutResponse(status, description);
    }

    @Override
    public void processResponseMessage(final Response message)
    {
        System.out.println("----- Process logout message response -----");
        final LogoutResponse response = (LogoutResponse) message;
        System.out.println(response.getDescription());
    }
}
