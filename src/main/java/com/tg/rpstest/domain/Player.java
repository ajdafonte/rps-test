package com.tg.rpstest.domain;

/**
 *
 */
public abstract class Player
{
    String name;
    PlayerType type;

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public PlayerType getType()
    {
        return type;
    }

    public void setType(final PlayerType type)
    {
        this.type = type;
    }
}
