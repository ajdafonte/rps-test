package com.tg.rpstest.infra.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


/**
 * ByeRequestTest class - Test ByeRequest class.
 */
public class ByeRequestTest
{
    private static ByeRequest generateByeRequest()
    {
        return new ByeRequest();
    }

    @Test
    public void givenByeRequest_whenCheckTypeValue_thenReturnByeRequestType()
    {
        // given
        final ByeRequest mockByeRequest1 = generateByeRequest();
        final MessageType expectedMessageType = MessageType.BYE_REQUEST;

        // when + then
        assertEquals(expectedMessageType, mockByeRequest1.type);
    }

    @Test
    public void givenTwoDifferentByeRequests_whenCheckIfEquals_thenReturnNok()
    {
        // given
        final ByeRequest mockByeRequest1 = generateByeRequest();
        final ByeRequest mockByeRequest2 = generateByeRequest();

        // when + then
        assertNotEquals(mockByeRequest1.hashCode(), mockByeRequest2.hashCode());
        assertNotEquals(mockByeRequest1, mockByeRequest2);
    }

    @Test
    public void givenByeRequest_whenCallToString_thenCheckIfContainsExpectedValue()
    {
        // given
        final ByeRequest mockByeRequest1 = generateByeRequest();
        final String[] expectedSubtrings = {
            "ByeRequest{}", "type=" + MessageType.BYE_REQUEST
        };

        // when
        final String value = mockByeRequest1.toString();

        // then
        assertTrue(Arrays.stream(expectedSubtrings)
            .allMatch(value::contains));
    }
}
