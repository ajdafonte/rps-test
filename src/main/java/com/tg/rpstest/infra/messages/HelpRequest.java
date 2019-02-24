package com.tg.rpstest.infra.messages;

/**
 *
 */
public class HelpRequest extends Request
{
    public HelpRequest()
    {
        super(MessageType.HELP_REQUEST);
    }

    @Override
    public String toString()
    {
        return "HelpRequest{} " + super.toString();
    }
}
