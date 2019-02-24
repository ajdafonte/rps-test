package com.tg.rpstest.infra.messages;

import java.util.Objects;

import com.tg.rpstest.server.domain.GameResult;


/**
 *
 */
public class GameResponse extends Response
{
    private static final long serialVersionUID = -7525034074820010339L;
    private final GameResult gameResult;

    public GameResponse(final ResponseStatus status, final GameResult gameResult)
    {
        super(MessageType.GAME_RESPONSE, status);
        this.gameResult = gameResult;
    }

    public GameResult getGameResult()
    {
        return gameResult;
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
        final GameResponse that = (GameResponse) o;
        return Objects.equals(gameResult, that.gameResult);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), gameResult);
    }

    @Override
    public String toString()
    {
        return "GameResponse{" +
            "gameResult=" + gameResult +
            "} " + super.toString();
    }
}
