package com.tg.rpstest.domain;

import java.util.Objects;


/**
 *
 */
public class RegisteredPlayer extends Player
{
    public RegisteredPlayer(final String name)
    {
        this.name = name;
        this.type = PlayerType.REGISTERED;
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
        final RegisteredPlayer that = (RegisteredPlayer) obj;
        return Objects.equals(name, that.name) &&
            Objects.equals(type, that.type);
    }

    @Override
    public String toString()
    {
        return "RegisteredPlayer{" +
            "name='" + name + '\'' +
            ", type=" + type +
            '}';
    }
}
