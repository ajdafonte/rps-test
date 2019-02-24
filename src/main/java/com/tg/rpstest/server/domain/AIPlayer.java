package com.tg.rpstest.server.domain;

import java.util.List;

import com.tg.rpstest.util.Util;


/**
 *
 */
public class AIPlayer extends Player
{
    public AIPlayer()
    {
        this.name = generateAIPlayerName();
        this.type = PlayerType.AI;
    }

    public AIPlayer(final String name, final List<RPSMove> moves)
    {
        this.name = name;
        this.moves = moves;
        this.type = PlayerType.AI;
    }

    private String generateAIPlayerName()
    {
        return "AI-" + Util.randomAlphaNumeric(6);
    }

    @Override
    public String toString()
    {
        return "AIPlayer{" +
            "name='" + name + '\'' +
            ", type=" + type +
            ", moves=" + moves +
            '}';
    }
}
