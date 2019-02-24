package com.tg.rpstest.infra.processor;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tg.rpstest.infra.messages.ByeRequest;
import com.tg.rpstest.infra.messages.Request;
import com.tg.rpstest.infra.messages.Response;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


/**
 * ByeMessageProcessorTest class - ByeMessageProcessor test class.
 */
public class ByeMessageProcessorTest
{
    private ByeMessageProcessor byeMessageProcessor;

    @BeforeEach
    public void setUp()
    {
        byeMessageProcessor = new ByeMessageProcessor();
    }

    @Test
    public void givenByeRequestMessage_whenProcessingRequest_thenReturnNullResponse()
    {
        // given
        final Request request = new ByeRequest();

        // when
        final Response response = byeMessageProcessor.processRequestMessage(request, null);

        // then
        assertNull(response);
    }

    @Test
    public void givenByeResponseMessage_whenProcessingResponse_thenThrowSpecificException()
    {
        // when + then
        Assertions.assertThrows(NotImplementedException.class, () -> byeMessageProcessor.processResponseMessage(null));
    }
}
