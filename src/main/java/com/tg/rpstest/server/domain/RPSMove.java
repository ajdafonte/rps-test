package com.tg.rpstest.server.domain;

/**
 *
 */
public enum RPSMove
{
    ROCK
        {
            @Override
            public boolean beats(final RPSMove other)
            {
                return other == SCISSOR;
            }

            @Override
            public String toString()
            {
                return "Rock";
            }
        },
    PAPER
        {
            @Override
            public boolean beats(final RPSMove other)
            {
                return other == ROCK;
            }

            @Override
            public String toString()
            {
                return "Paper";
            }
        },
    SCISSOR
        {
            @Override
            public boolean beats(final RPSMove other)
            {
                return other == PAPER;
            }

            @Override
            public String toString()
            {
                return "Scissor";
            }
        };

    public static RPSMove parseRPSMove(String value)
    {
        if (value != null)
        {
            value = value.toLowerCase().trim();
            switch (value)
            {
                case "r":
                    return ROCK;
                case "p":
                    return PAPER;
                case "s":
                    return SCISSOR;
            }
        }

        return null;
    }

    public static RPSMove randomRPSMove()
    {
        return RPSMove.values()[(int) (Math.random() * RPSMove.values().length)];
    }

    public abstract boolean beats(RPSMove other); //Used to determine who wins?
}
