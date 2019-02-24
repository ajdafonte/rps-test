package com.tg.rpstest.infra.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


/**
 * LogoutRequestTest class - Test LogoutRequest class.
 */
public class LogoutRequestTest
{
    private static final String MOCK_USERNAME1 = "Buzz";
    private static final String MOCK_USERNAME2 = "Buddy";

    private static LogoutRequest generateLogoutRequest(final String userName)
    {
        return new LogoutRequest(userName);
    }

    @Test
    public void givenLogoutRequest_whenCheckTypeValue_thenReturnLogoutRequestType()
    {
        // given
        final LogoutRequest mockLogoutRequest1 = generateLogoutRequest(MOCK_USERNAME1);
        final MessageType expectedMessageType = MessageType.LOGOUT_REQUEST;

        // when + then
        assertEquals(expectedMessageType, mockLogoutRequest1.type);
    }

    @Test
    public void givenTwoDifferentLogoutRequests_whenCheckIfEquals_thenReturnNok()
    {
        // given
        final LogoutRequest mockLogoutRequest1 = generateLogoutRequest(MOCK_USERNAME1);
        final LogoutRequest mockLogoutRequest2 = generateLogoutRequest(MOCK_USERNAME2);

        // when + then
        assertNotEquals(mockLogoutRequest1.hashCode(), mockLogoutRequest2.hashCode());
        assertNotEquals(mockLogoutRequest1, mockLogoutRequest2);
    }

    @Test
    public void givenLogoutRequest_whenCallToString_thenCheckIfContainsExpectedValue()
    {
        // given
        final LogoutRequest mockLogoutRequest1 = generateLogoutRequest(MOCK_USERNAME1);
        final String[] expectedSubtrings = {
            "LogoutRequest{" +
                "userName='" + MOCK_USERNAME1 + '\'' +
                "} ", "type=" + MessageType.LOGOUT_REQUEST
        };

        // when
        final String value = mockLogoutRequest1.toString();

        // then
        assertTrue(Arrays.stream(expectedSubtrings)
            .allMatch(value::contains));
    }
}
