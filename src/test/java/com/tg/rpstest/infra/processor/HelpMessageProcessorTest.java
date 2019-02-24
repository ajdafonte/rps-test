package com.tg.rpstest.infra.processor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tg.rpstest.infra.messages.HelpRequest;
import com.tg.rpstest.infra.messages.HelpResponse;
import com.tg.rpstest.infra.messages.Request;
import com.tg.rpstest.infra.messages.Response;
import com.tg.rpstest.infra.messages.ResponseStatus;


/**
 * HelpMessageProcessorTest class - helpMessageProcessor test class.
 */
public class HelpMessageProcessorTest
{
    private HelpMessageProcessor helpMessageProcessor;

    @BeforeEach
    public void setUp()
    {
        helpMessageProcessor = new HelpMessageProcessor();
    }

    @Test
    public void givenHelpRequestMessage_whenProcessingRequest_thenReturnValidResponse()
    {
        // given
        final Request request = new HelpRequest();
        final String expectedHelpMessage = generateHelpMessage();
        final HelpResponse expectedResponse = new HelpResponse(ResponseStatus.OK, expectedHelpMessage);

        // when
        final Response response = helpMessageProcessor.processRequestMessage(request, null);

        // then

        assertNotNull(response);
        assertThat(response, instanceOf(HelpResponse.class));
        final HelpResponse helpResponse = (HelpResponse) response;
        assertEquals(expectedResponse.getType(), helpResponse.getType());
        assertEquals(expectedResponse.getHelpMessage(), helpResponse.getHelpMessage());
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
}
