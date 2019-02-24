package com.tg.rpstest.infra.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


/**
 * LoginResponseTest class - Test LoginResponse class.
 */
public class LoginResponseTest
{
    private static final String MOCK_DESCRIPTION1 = "dummy msg";
    private static final String MOCK_DESCRIPTION2 = "dummy2 msg";

    private static LoginResponse generateLoginResponse(final ResponseStatus status, final String description)
    {
        return new LoginResponse(status, description);
    }

    @Test
    public void givenLoginResponse_whenCheckTypeValue_thenReturnLoginResponseType()
    {
        // given
        final LoginResponse mockLoginResponse1 = generateLoginResponse(ResponseStatus.OK, MOCK_DESCRIPTION1);
        final MessageType expectedMessageType = MessageType.LOGIN_RESPONSE;

        // when + then
        assertEquals(expectedMessageType, mockLoginResponse1.type);
    }

    @Test
    public void givenTwoDifferentLoginResponses_whenCheckIfEquals_thenReturnNok()
    {
        // given
        final LoginResponse mockLoginResponse1 = generateLoginResponse(ResponseStatus.OK, MOCK_DESCRIPTION1);
        final LoginResponse mockLoginResponse2 = generateLoginResponse(ResponseStatus.OK, MOCK_DESCRIPTION2);

        // when + then
        assertNotEquals(mockLoginResponse1.hashCode(), mockLoginResponse2.hashCode());
        assertNotEquals(mockLoginResponse1, mockLoginResponse2);
    }

    @Test
    public void givenLoginResponse_whenCallToString_thenCheckIfContainsExpectedValue()
    {
        // given
        final LoginResponse mockLoginResponse1 = generateLoginResponse(ResponseStatus.OK, MOCK_DESCRIPTION1);
        final String[] expectedSubtrings = {
            "LoginResponse{" +
                "description='" + MOCK_DESCRIPTION1 + '\'' +
                "} ", "type=" + MessageType.LOGIN_RESPONSE
        };

        // when
        final String value = mockLoginResponse1.toString();

        // then
        assertTrue(Arrays.stream(expectedSubtrings)
            .allMatch(value::contains));
    }
}
