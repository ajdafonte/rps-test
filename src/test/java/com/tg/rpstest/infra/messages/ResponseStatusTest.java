package com.tg.rpstest.infra.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * ResponseStatusTest class - Test ResponseStatus enum.
 */
public class ResponseStatusTest
{
    // test value
    @Test
    public void givenEnumValues_whenGetValue_returnCorrectValue()
    {
        assertEquals(0, ResponseStatus.NOK.getValue());
        assertEquals(1, ResponseStatus.OK.getValue());
    }
}
