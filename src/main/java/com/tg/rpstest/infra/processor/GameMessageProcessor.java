package com.tg.rpstest.infra.processor;

import java.util.List;

import com.tg.rpstest.infra.messages.GameRequest;
import com.tg.rpstest.infra.messages.GameResponse;
import com.tg.rpstest.infra.messages.Request;
import com.tg.rpstest.infra.messages.Response;
import com.tg.rpstest.infra.messages.ResponseStatus;
import com.tg.rpstest.server.ServerContext;
import com.tg.rpstest.server.domain.GameResult;
import com.tg.rpstest.server.domain.GuestPlayer;
import com.tg.rpstest.server.domain.Player;
import com.tg.rpstest.server.domain.RPSGame;
import com.tg.rpstest.server.domain.RPSMove;
import com.tg.rpstest.server.domain.RoundResult;


/**
 *
 */
public class GameMessageProcessor implements MessageProcessor
{
    @Override
    public Response processRequestMessage(final Request message, final ServerContext serverContext)
    {
        System.out.println("----- Process game message request -----");

        final GameRequest msg = (GameRequest) message;

        final int numRounds = msg.getNumRounds();
        final List<RPSMove> moves = msg.getMoves();
        final String userName = msg.getPlayerName();

        System.out.println(">> Number of rounds for this game: " + msg.getNumRounds());

        // check if this is a game of a guest or registered player
        Player currentPlayer = serverContext.getCurrentPlayer();
        ResponseStatus status = ResponseStatus.OK;
        if (currentPlayer != null)
        {
            System.out.println(">> Start game for a registered player...");
            // check if game request belongs to registered user
            if (userName.equals(currentPlayer.getName()))
            {
                System.out.println(">> All OK, game request belongs to the registered player...");
            }
            else
            {
                System.out.println(">> ATTENTION!! Game request does not belong to the registered player...");
                status = ResponseStatus.NOK;
            }
        }
        else
        {
            System.out.println(">> Start game for a guest player...");
            currentPlayer = new GuestPlayer();
        }

        // only makes sense to play game if previous validation is ok
        GameResult gameResult = null;
        if (status.equals(ResponseStatus.OK))
        {
            currentPlayer.setMoves(moves);

            // setup and start game
            final RPSGame game = new RPSGame(numRounds, currentPlayer);
            System.out.println(">> Playing game...");
            gameResult = game.start();
        }

        return new GameResponse(status, gameResult);
    }

    @Override
    public void processResponseMessage(final Response message)
    {
        System.out.println("----- Process game message response -----");

        final GameResponse response = (GameResponse) message;

        final GameResult gameResult = response.getGameResult();

        System.out.println(generateGameResultMessage(gameResult));
    }

    private String generateGameResultMessage(final GameResult gameResult)
    {
        final String newLine = System.lineSeparator();
        final StringBuilder gameResultMsg = new StringBuilder();
        gameResultMsg.append(newLine);
        gameResultMsg.append("---------------------- Game Result ----------------------");
        gameResultMsg.append(newLine);
        gameResultMsg.append("Client wins: ").append(gameResult.getClientWins());
        gameResultMsg.append(newLine);
        gameResultMsg.append("Server wins: ").append(gameResult.getServerWins());
        gameResultMsg.append(newLine);
        gameResultMsg.append("Ties: ").append(gameResult.getNumTies());
        gameResultMsg.append(newLine);
        gameResultMsg.append("Details about each round:");
        gameResultMsg.append(newLine);

        final List<RoundResult> roundResults = gameResult.getRoundResults();
        for (int i = 0; i < roundResults.size(); i++)
        {
            final RoundResult roundResult = roundResults.get(i);
            gameResultMsg.append("Round ").append(i + 1)
                .append(" - Client move: ").append(roundResult.getClientMove())
                .append(" - Server move: ").append(roundResult.getServerMove())
                .append(" - Result: ").append(roundResult.getResult())
                .append(newLine);
        }
        gameResultMsg.append("---------------------------------------------------------");
        gameResultMsg.append(newLine);

        return gameResultMsg.toString();
    }
}
