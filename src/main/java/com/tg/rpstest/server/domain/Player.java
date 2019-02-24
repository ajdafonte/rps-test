package com.tg.rpstest.server.domain;

import java.util.List;
import java.util.Objects;


/**
 *
 */
public abstract class Player
{
    String name;
    PlayerType type;
    List<RPSMove> moves;

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

    public List<RPSMove> getMoves()
    {
        return moves;
    }

    public void setMoves(final List<RPSMove> moves)
    {
        this.moves = moves;
    }

    public RPSMove getMoveOfRound(final int numRound)
    {
        if (numRound >= 0 && numRound <= moves.size())
        {
            return moves.get(numRound);
        }
        return null;
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
        final Player player = (Player) o;
        return name.equals(player.name) &&
            type == player.type &&
            Objects.equals(moves, player.moves);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, type, moves);
    }

    @Override
    public String toString()
    {
        return "Player{" +
            "name='" + name + '\'' +
            ", type=" + type +
            ", moves=" + moves +
            '}';
    }
}
