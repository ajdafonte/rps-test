package com.tg.rpstest.infra.messages;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


/**
 *
 */
public abstract class Message implements Serializable
{
    private static final long serialVersionUID = -827524405647260216L;
    UUID id;
    MessageType type;

    Message(final UUID id, final MessageType type)
    {
        this.id = id;
        this.type = type;
    }

    public MessageType getType()
    {
        return type;
    }

    public void setType(final MessageType type)
    {
        this.type = type;
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
        final Message that = (Message) o;
        return id == that.id &&
            type == that.type;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, type);
    }

    @Override
    public String toString()
    {
        return "Message{" +
            "id=" + id +
            ", type=" + type +
            '}';
    }
}
