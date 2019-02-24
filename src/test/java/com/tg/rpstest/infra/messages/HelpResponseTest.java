package com.tg.rpstest.infra.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


/**
 * HelpResponseTest class - Test HelpResponse class.
 */
public class HelpResponseTest
{
    private static final String MOCK_HELP_MSG1 = "dummy msg";
    private static final String MOCK_HELP_MSG2 = "dummy2 msg";

    private static HelpResponse generateHelpResponse(final ResponseStatus status, final String helpMsg)
    {
        return new HelpResponse(status, helpMsg);
    }

    @Test
    public void givenHelpResponse_whenCheckTypeValue_thenReturnHelpResponseType()
    {
        // given
        final HelpResponse mockHelpResponse1 = generateHelpResponse(ResponseStatus.OK, MOCK_HELP_MSG1);
        final MessageType expectedMessageType = MessageType.HELP_RESPONSE;

        // when + then
        assertEquals(expectedMessageType, mockHelpResponse1.type);
    }

    @Test
    public void givenTwoDifferentHelpResponses_whenCheckIfEquals_thenReturnNok()
    {
        // given
        final HelpResponse mockHelpResponse1 = generateHelpResponse(ResponseStatus.OK, MOCK_HELP_MSG1);
        final HelpResponse mockHelpResponse2 = generateHelpResponse(ResponseStatus.OK, MOCK_HELP_MSG2);

        // when + then
        assertNotEquals(mockHelpResponse1.hashCode(), mockHelpResponse2.hashCode());
        assertNotEquals(mockHelpResponse1, mockHelpResponse2);
    }

    @Test
    public void givenHelpResponse_whenCallToString_thenCheckIfContainsExpectedValue()
    {
        // given
        final HelpResponse mockHelpResponse1 = generateHelpResponse(ResponseStatus.OK, MOCK_HELP_MSG1);
        final String[] expectedSubtrings = {
            "HelpResponse{" +
                "helpMessage='" + MOCK_HELP_MSG1 + '\'' +
                "} ", "type=" + MessageType.HELP_RESPONSE
        };

        // when
        final String value = mockHelpResponse1.toString();

        // then
        assertTrue(Arrays.stream(expectedSubtrings)
            .allMatch(value::contains));
    }
}
