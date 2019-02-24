package com.tg.rpstest.infra.messages;

import java.util.Objects;
import java.util.UUID;


/**
 *
 */
public abstract class Response extends Message
{
    private static final long serialVersionUID = 8400857872267856373L;
    private final ResponseStatus status;

    public Response(final MessageType type, final ResponseStatus status)
    {
        super(UUID.randomUUID(), type);
        this.status = status;
    }

    public ResponseStatus getStatus()
    {
        return status;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        if (!super.equals(o))
        {
            return false;
        }
        final Response response = (Response) o;
        return status == response.status;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), status);
    }

    @Override
    public String toString()
    {
        return "Response{" +
            "status=" + status +
            "} " + super.toString();
    }
}
