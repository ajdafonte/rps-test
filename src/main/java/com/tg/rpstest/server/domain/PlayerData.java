package com.tg.rpstest.server.domain;

import java.util.Objects;


/**
 *
 */
public class PlayerData
{
    private final String name;
    private final int wins;
    private final int losses;

    public static class PlayerDataBuilder
    {
        private String name;
        private int wins;
        private int losses;

        public PlayerDataBuilder withName(final String name)
        {
            this.name = name;
            return this;
        }

        public PlayerDataBuilder withWins(final int wins)
        {
            this.wins = wins;
            return this;
        }

        public PlayerDataBuilder withLosses(final int losses)
        {
            this.losses = losses;
            return this;
        }

        public PlayerData build()
        {
            // call the private constructor in the outer class
            return new PlayerData(this);
        }
    }

    private PlayerData(final PlayerDataBuilder builder)
    {
        this.name = builder.name;
        this.wins = builder.wins;
        this.losses = builder.losses;
    }

    public String getName()
    {
        return name;
    }

    public int getWins()
    {
        return wins;
    }

    public int getLosses()
    {
        return losses;
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
        final PlayerData that = (PlayerData) o;
        return wins == that.wins &&
            losses == that.losses &&
            name.equals(that.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, wins, losses);
    }

    @Override
    public String toString()
    {
        return "PlayerData{" +
            "name='" + name + '\'' +
            ", wins=" + wins +
            ", losses=" + losses +
            '}';
    }
}
