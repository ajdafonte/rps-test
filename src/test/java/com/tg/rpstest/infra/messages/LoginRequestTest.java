package com.tg.rpstest.infra.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


/**
 * LoginRequestTest class - Test LoginRequest class.
 */
public class LoginRequestTest
{
    private static final String MOCK_USERNAME1 = "Buzz";
    private static final String MOCK_USERNAME2 = "Buddy";

    private static LoginRequest generateLoginRequest(final String userName)
    {
        return new LoginRequest(userName);
    }

    @Test
    public void givenLoginRequest_whenCheckTypeValue_thenReturnLoginRequestType()
    {
        // given
        final LoginRequest mockLoginRequest1 = generateLoginRequest(MOCK_USERNAME1);
        final MessageType expectedMessageType = MessageType.LOGIN_REQUEST;

        // when + then
        assertEquals(expectedMessageType, mockLoginRequest1.type);
    }

    @Test
    public void givenTwoDifferentLoginRequests_whenCheckIfEquals_thenReturnNok()
    {
        // given
        final LoginRequest mockLoginRequest1 = generateLoginRequest(MOCK_USERNAME1);
        final LoginRequest mockLoginRequest2 = generateLoginRequest(MOCK_USERNAME2);

        // when + then
        assertNotEquals(mockLoginRequest1.hashCode(), mockLoginRequest2.hashCode());
        assertNotEquals(mockLoginRequest1, mockLoginRequest2);
    }

    @Test
    public void givenLoginRequest_whenCallToString_thenCheckIfContainsExpectedValue()
    {
        // given
        final LoginRequest mockLoginRequest1 = generateLoginRequest(MOCK_USERNAME1);
        final String[] expectedSubtrings = {
            "LoginRequest{" +
                "userName='" + MOCK_USERNAME1 + '\'' +
                "} ", "type=" + MessageType.LOGIN_REQUEST
        };

        // when
        final String value = mockLoginRequest1.toString();

        // then
        assertTrue(Arrays.stream(expectedSubtrings)
            .allMatch(value::contains));
    }
}
