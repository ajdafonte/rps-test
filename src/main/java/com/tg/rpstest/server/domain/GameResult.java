package com.tg.rpstest.server.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 *
 */
public class GameResult implements Serializable
{
    private static final long serialVersionUID = -6559217664824298559L;
    int clientWins;
    int numTies;
    int serverWins;
    List<RoundResult> roundResults;

    public GameResult()
    {
        this.roundResults = new ArrayList<>();
    }

    public int getClientWins()
    {
        return clientWins;
    }

    public int getNumTies()
    {
        return numTies;
    }

    public int getServerWins()
    {
        return serverWins;
    }

    public List<RoundResult> getRoundResults()
    {
        return roundResults;
    }

    public void setRoundResults(final List<RoundResult> roundResults)
    {
        this.roundResults = roundResults;
    }

    public void incrementNumberOfClientWins()
    {
        this.clientWins++;
    }

    public void incrementNumberOfServerWins()
    {
        this.serverWins++;
    }

    public void incrementNumberOfTies()
    {
        this.numTies++;
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
        final GameResult that = (GameResult) o;
        return clientWins == that.clientWins &&
            numTies == that.numTies &&
            serverWins == that.serverWins &&
            roundResults.equals(that.roundResults);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(clientWins, numTies, serverWins, roundResults);
    }

    @Override
    public String toString()
    {
        return "GameResult{" +
            "clientWins=" + clientWins +
            ", numTies=" + numTies +
            ", serverWins=" + serverWins +
            ", roundResults=" + roundResults +
            '}';
    }

}
