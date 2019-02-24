package com.tg.rpstest.infra.processor;

import com.tg.rpstest.infra.messages.HelpResponse;
import com.tg.rpstest.infra.messages.Request;
import com.tg.rpstest.infra.messages.Response;
import com.tg.rpstest.infra.messages.ResponseStatus;
import com.tg.rpstest.server.ServerContext;


/**
 *
 */
public class HelpMessageProcessor implements MessageProcessor
{
    @Override
    public Response processRequestMessage(final Request message, final ServerContext serverContext)
    {
        System.out.println("----- Process help message request -----");
        return new HelpResponse(ResponseStatus.OK, generateHelpMessage());
    }

    private String generateHelpMessage()
    {
        final String newLine = System.lineSeparator();
        return "-------------------- HELP --------------------"
            + newLine
            + "This is a simple Rock, Paper, Scissors game."
            + newLine
            + "You can play as guest or registered user."
            + newLine
            + "For each game, you can define the number of rounds to play and select the following moves in each round:"
            + newLine
            + " - rock (r), paper (p) and scissors (s)"
            + newLine
            + "-----------------------------------------------";
    }

    @Override
    public void processResponseMessage(final Response message)
    {
        System.out.println("----- Process help message response -----");
        final HelpResponse response = (HelpResponse) message;
        System.out.println(response.getHelpMessage());
    }
}
