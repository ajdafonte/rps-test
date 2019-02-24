package com.tg.rpstest.server.domain;

import java.io.Serializable;
import java.util.Objects;


/**
 *
 */
public class RoundResult implements Serializable
{
    private static final long serialVersionUID = -7847455545304232821L;
    private final RPSMove clientMove;
    private final RPSMove serverMove;
    private final RPSResult result;

    public RoundResult(final RPSMove clientMove, final RPSMove serverMove, final RPSResult result)
    {
        this.clientMove = clientMove;
        this.serverMove = serverMove;
        this.result = result;
    }

    public RPSMove getClientMove()
    {
        return clientMove;
    }

    public RPSMove getServerMove()
    {
        return serverMove;
    }

    public RPSResult getResult()
    {
        return result;
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
        final RoundResult that = (RoundResult) o;
        return clientMove == that.clientMove &&
            serverMove == that.serverMove &&
            result == that.result;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(clientMove, serverMove, result);
    }

    @Override
    public String toString()
    {
        return "RoundResult{" +
            "clientMove=" + clientMove +
            ", serverMove=" + serverMove +
            ", result=" + result +
            '}';
    }
}
