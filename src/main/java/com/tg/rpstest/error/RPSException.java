package com.tg.rpstest.error;

/**
 *
 */
public class RPSException extends Exception
{
    private static final long serialVersionUID = 8729203425066900867L;

    public RPSException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
