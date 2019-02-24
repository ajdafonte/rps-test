package com.tg.rpstest.infra.processor;

import com.tg.rpstest.infra.messages.MessageType;


/**
 *
 */
public class MessageProcessorFactory
{
    GameMessageProcessor gameMessageProcessor = new GameMessageProcessor();
    ByeMessageProcessor byeMessageProcessor = new ByeMessageProcessor();
    LoginMessageProcessor loginMessageProcessor = new LoginMessageProcessor();
    HelpMessageProcessor helpMessageProcessor = new HelpMessageProcessor();
    LogoutMessageProcessor logoutMessageProcessor = new LogoutMessageProcessor();

    public MessageProcessor getMessageProcessor(final MessageType type)
    {
        switch (type)
        {
            case GAME_REQUEST:
            case GAME_RESPONSE:
                return gameMessageProcessor;
            case BYE_REQUEST:
            case BYE_RESPONSE:
                return byeMessageProcessor;
            case LOGIN_REQUEST:
            case LOGIN_RESPONSE:
                return loginMessageProcessor;
            case HELP_REQUEST:
            case HELP_RESPONSE:
                return helpMessageProcessor;
            case LOGOUT_REQUEST:
            case LOGOUT_RESPONSE:
                return logoutMessageProcessor;
            default:
                return null;
        }
    }
}
