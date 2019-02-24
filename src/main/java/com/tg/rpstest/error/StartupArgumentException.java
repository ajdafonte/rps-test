package com.tg.rpstest.error;

/**
 *
 */
public class StartupArgumentException extends Exception
{
    private static final long serialVersionUID = 5020723124259164288L;

    public StartupArgumentException(final String message)
    {
        super(message);
    }
}
