package com.tg.rpstest.domain;

import java.util.Objects;


/**
 *
 */
public class GuestPlayer extends Player
{
    public GuestPlayer(final String name)
    {
        this.name = name;
        this.type = PlayerType.GUEST;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, type);
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        final GuestPlayer that = (GuestPlayer) obj;
        return Objects.equals(name, that.name) &&
            Objects.equals(type, that.type);
    }

    @Override
    public String toString()
    {
        return "GuestPlayer{" +
            "name='" + name + '\'' +
            ", type=" + type +
            '}';
    }
}
