package com.tg.rpstest.server.domain;

import java.util.List;

import com.tg.rpstest.util.Util;


/**
 *
 */
public class GuestPlayer extends Player
{
    public GuestPlayer()
    {
        this.name = generateGuestPlayerName();
        this.type = PlayerType.GUEST;
    }

    public GuestPlayer(final String name, final List<RPSMove> moves)
    {
        this.name = name;
        this.moves = moves;
        this.type = PlayerType.GUEST;
    }

    private String generateGuestPlayerName()
    {
        return "GUEST-" + Util.randomAlphaNumeric(6);
    }

    @Override
    public String toString()
    {
        return "GuestPlayer{" +
            "name='" + name + '\'' +
            ", type=" + type +
            ", moves=" + moves +
            '}';
    }
}
