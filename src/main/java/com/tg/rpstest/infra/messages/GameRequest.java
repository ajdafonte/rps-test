package com.tg.rpstest.infra.messages;

import java.util.List;
import java.util.Objects;

import com.tg.rpstest.server.domain.RPSMove;


/**
 *
 */
public class GameRequest extends Request
{
    private static final long serialVersionUID = -5130829054118413306L;
    private final int numRounds;
    private String playerName;
    private List<RPSMove> moves;

    public GameRequest(final int numRounds, final String playerName, final List<RPSMove> moves)
    {
        super(MessageType.GAME_REQUEST);
        this.numRounds = numRounds;
        this.playerName = playerName;
        this.moves = moves;
    }

    public GameRequest(final int numRounds, final List<RPSMove> moves)
    {
        super(MessageType.GAME_REQUEST);
        this.numRounds = numRounds;
        this.moves = moves;
    }

    public int getNumRounds()
    {
        return numRounds;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public List<RPSMove> getMoves()
    {
        return moves;
    }

    public void setMoves(final List<RPSMove> moves)
    {
        this.moves = moves;
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
        if (!super.equals(o))
        {
            return false;
        }
        final GameRequest that = (GameRequest) o;
        return numRounds == that.numRounds &&
            Objects.equals(playerName, that.playerName) &&
            moves.equals(that.moves);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), numRounds, playerName, moves);
    }

    @Override
    public String toString()
    {
        return "GameRequest{" +
            "numRounds=" + numRounds +
            ", playerName='" + playerName + '\'' +
            ", moves=" + moves +
            "} " + super.toString();
    }
}
