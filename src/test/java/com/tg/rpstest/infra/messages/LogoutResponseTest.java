package com.tg.rpstest.infra.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


/**
 * LogoutResponseTest class - Test LogoutResponse class.
 */
public class LogoutResponseTest
{
    private static final String MOCK_DESCRIPTION1 = "dummy msg";
    private static final String MOCK_DESCRIPTION2 = "dummy2 msg";

    private static LogoutResponse generateLogoutResponse(final ResponseStatus status, final String description)
    {
        return new LogoutResponse(status, description);
    }

    @Test
    public void givenLogoutResponse_whenCheckTypeValue_thenReturnLogoutResponseType()
    {
        // given
        final LogoutResponse mockLogoutResponse1 = generateLogoutResponse(ResponseStatus.OK, MOCK_DESCRIPTION1);
        final MessageType expectedMessageType = MessageType.LOGOUT_RESPONSE;

        // when + then
        assertEquals(expectedMessageType, mockLogoutResponse1.type);
    }

    @Test
    public void givenTwoDifferentLogoutResponses_whenCheckIfEquals_thenReturnNok()
    {
        // given
        final LogoutResponse mockLogoutResponse1 = generateLogoutResponse(ResponseStatus.OK, MOCK_DESCRIPTION1);
        final LogoutResponse mockLogoutResponse2 = generateLogoutResponse(ResponseStatus.OK, MOCK_DESCRIPTION2);

        // when + then
        assertNotEquals(mockLogoutResponse1.hashCode(), mockLogoutResponse2.hashCode());
        assertNotEquals(mockLogoutResponse1, mockLogoutResponse2);
    }

    @Test
    public void givenLogoutResponse_whenCallToString_thenCheckIfContainsExpectedValue()
    {
        // given
        final LogoutResponse mockLogoutResponse1 = generateLogoutResponse(ResponseStatus.OK, MOCK_DESCRIPTION1);
        final String[] expectedSubtrings = {
            "LogoutResponse{" +
                "description='" + MOCK_DESCRIPTION1 + '\'' +
                "} ", "type=" + MessageType.LOGOUT_RESPONSE
        };

        // when
        final String value = mockLogoutResponse1.toString();

        // then
        assertTrue(Arrays.stream(expectedSubtrings)
            .allMatch(value::contains));
    }
}
