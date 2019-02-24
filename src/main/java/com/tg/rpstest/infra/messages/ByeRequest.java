package com.tg.rpstest.infra.messages;

/**
 *
 */
public class ByeRequest extends Request
{
    private static final long serialVersionUID = -5130829054118413306L;

    public ByeRequest()
    {
        super(MessageType.BYE_REQUEST);
    }

    @Override
    public String toString()
    {
        return "ByeRequest{} " + super.toString();
    }
}
