package com.tg.rpstest.infra.messages;

import java.util.UUID;


/**
 *
 */
public abstract class Request extends Message
{
    public Request(final MessageType type)
    {
        super(UUID.randomUUID(), type);
    }
}
