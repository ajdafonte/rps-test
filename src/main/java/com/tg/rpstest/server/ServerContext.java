package com.tg.rpstest.server;

import com.tg.rpstest.server.domain.Player;


/**
 *
 */
public class ServerContext
{
    private Player currentPlayer;

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void setCurrentPlayer(final Player currentPlayer)
    {
        this.currentPlayer = currentPlayer;
    }
}
