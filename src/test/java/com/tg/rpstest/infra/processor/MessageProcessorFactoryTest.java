package com.tg.rpstest.infra.processor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tg.rpstest.infra.messages.MessageType;


/**
 * MessageProcessorFactoryTest class - MessageProcessorFactory test class.
 */
public class MessageProcessorFactoryTest
{
    private MessageProcessorFactory processorFactory;

    @BeforeEach
    public void setUp()
    {
        this.processorFactory = new MessageProcessorFactory();
    }

    @Test
    public void givenValidLoginMessageType_whenGetSpecificProcessor_thenReturnCorrectProcessor()
    {
        // given
        final MessageType[] messageTypes = {MessageType.LOGIN_REQUEST, MessageType.LOGIN_RESPONSE};

        // when + then
        for (final MessageType messageType : messageTypes)
        {
            final MessageProcessor messageProcessor = processorFactory.getMessageProcessor(messageType);
            assertThat(messageProcessor, instanceOf(LoginMessageProcessor.class));
        }
    }

    @Test
    public void givenValidByeMessageType_whenGetSpecificProcessor_thenReturnCorrectProcessor()
    {
        // given
        final MessageType[] messageTypes = {MessageType.BYE_REQUEST, MessageType.BYE_RESPONSE};

        // when + then
        for (final MessageType messageType : messageTypes)
        {
            final MessageProcessor messageProcessor = processorFactory.getMessageProcessor(messageType);
            assertThat(messageProcessor, instanceOf(ByeMessageProcessor.class));
        }
    }

    @Test
    public void givenValidLogoutMessageType_whenGetSpecificProcessor_thenReturnCorrectProcessor()
    {
        // given
        final MessageType[] messageTypes = {MessageType.LOGOUT_REQUEST, MessageType.LOGOUT_RESPONSE};

        // when + then
        for (final MessageType messageType : messageTypes)
        {
            final MessageProcessor messageProcessor = processorFactory.getMessageProcessor(messageType);
            assertThat(messageProcessor, instanceOf(LogoutMessageProcessor.class));
        }
    }

    @Test
    public void givenValidHelpMessageType_whenGetSpecificProcessor_thenReturnCorrectProcessor()
    {
        // given
        final MessageType[] messageTypes = {MessageType.HELP_REQUEST, MessageType.HELP_RESPONSE};

        // when + then
        for (final MessageType messageType : messageTypes)
        {
            final MessageProcessor messageProcessor = processorFactory.getMessageProcessor(messageType);
            assertThat(messageProcessor, instanceOf(HelpMessageProcessor.class));
        }
    }

    @Test
    public void givenValidGameMessageType_whenGetSpecificProcessor_thenReturnCorrectProcessor()
    {
        // given
        final MessageType[] messageTypes = {MessageType.GAME_REQUEST, MessageType.GAME_RESPONSE};

        // when + then
        for (final MessageType messageType : messageTypes)
        {
            final MessageProcessor messageProcessor = processorFactory.getMessageProcessor(messageType);
            assertThat(messageProcessor, instanceOf(GameMessageProcessor.class));
        }
    }
}
