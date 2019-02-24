package com.tg.rpstest.server.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 *
 */
public class RPSGame
{
    private final int numRounds;
    private final Player player1;
    private final Player player2;

    public RPSGame(final int numRounds, final Player player1)
    {
        this.numRounds = numRounds;
        this.player1 = player1;
        this.player2 = generateAIPlayer();
    }

    public RPSGame(final int numRounds, final Player player1, final Player player2)
    {
        this.numRounds = numRounds;
        this.player1 = player1;
        this.player2 = player2;
    }

    private Player generateAIPlayer()
    {
        final AIPlayer player = new AIPlayer();
        player.setMoves(generateAIMoves());
        return player;
    }

    private List<RPSMove> generateAIMoves()
    {
        final java.util.List<RPSMove> aiMoves = new ArrayList<>();
        for (int i = 0; i < numRounds; i++)
        {
            aiMoves.add(RPSMove.randomRPSMove());
        }
        return aiMoves;
    }

    public GameResult start()
    {
        final GameResult gameResult = new GameResult();
        final List<RoundResult> roundResults = new ArrayList<>();
        for (int i = 0; i < numRounds; i++)
        {
            final RPSMove player1Move = player1.getMoveOfRound(i);
            final RPSMove player2Move = player2.getMoveOfRound(i);
            RPSResult result = RPSResult.TIE;
            if (player1Move == player2Move)
            {
                gameResult.incrementNumberOfTies();
            }
            else if (player1Move.beats(player2Move))
            {
                gameResult.incrementNumberOfClientWins();
                result = RPSResult.PLAYER1_WIN;
            }
            else if (player2Move.beats(player1Move))
            {
                gameResult.incrementNumberOfServerWins();
                result = RPSResult.PLAYER2_WIN;
            }

            roundResults.add(new RoundResult(player1Move, player2Move, result));
        }

        gameResult.setRoundResults(roundResults);

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
        final RPSGame game = (RPSGame) o;
        return numRounds == game.numRounds &&
            player1.equals(game.player1) &&
            player2.equals(game.player2);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(numRounds, player1, player2);
    }

    @Override
    public String toString()
    {
        return "RPSGame{" +
            "numRounds=" + numRounds +
            ", player1=" + player1 +
            ", player2=" + player2 +
            '}';
    }

}
