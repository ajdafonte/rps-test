package com.tg.rpstest.infra.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


/**
 * HelpRequestTest class - Test HelpRequest class.
 */
public class HelpRequestTest
{
    private static HelpRequest generateHelpRequest()
    {
        return new HelpRequest();
    }

    @Test
    public void givenHelpRequest_whenCheckTypeValue_thenReturnHelpRequestType()
    {
        // given
        final HelpRequest mockHelpRequest1 = generateHelpRequest();
        final MessageType expectedMessageType = MessageType.HELP_REQUEST;

        // when + then
        assertEquals(expectedMessageType, mockHelpRequest1.type);
    }

    @Test
    public void givenTwoDifferentHelpRequests_whenCheckIfEquals_thenReturnNok()
    {
        // given
        final HelpRequest mockHelpRequest1 = generateHelpRequest();
        final HelpRequest mockHelpRequest2 = generateHelpRequest();

        // when + then
        assertNotEquals(mockHelpRequest1.hashCode(), mockHelpRequest2.hashCode());
        assertNotEquals(mockHelpRequest1, mockHelpRequest2);
    }

    @Test
    public void givenHelpRequest_whenCallToString_thenCheckIfContainsExpectedValue()
    {
        // given
        final HelpRequest mockHelpRequest1 = generateHelpRequest();
        final String[] expectedSubtrings = {
            "HelpRequest{}", "type=" + MessageType.HELP_REQUEST
        };

        // when
        final String value = mockHelpRequest1.toString();

        // then
        assertTrue(Arrays.stream(expectedSubtrings)
            .allMatch(value::contains));
    }
}