package com.tg.rpstest.infra.messages;

/**
 *
 */
public enum ResponseStatus
{
    OK(1), NOK(0);

    private final int value;

    public int getValue()
    {
        return value;
    }

    ResponseStatus(final int value)
    {
        this.value = value;
    }
}
