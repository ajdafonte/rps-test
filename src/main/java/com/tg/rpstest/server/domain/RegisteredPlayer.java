package com.tg.rpstest.server.domain;

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
    public String toString()
    {
        return "RegisteredPlayer{" +
            "name='" + name + '\'' +
            ", type=" + type +
            ", moves=" + moves +
            '}';
    }
}
