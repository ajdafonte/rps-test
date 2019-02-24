package com.tg.rpstest.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tg.rpstest.infra.CommunicationHandler;
import com.tg.rpstest.infra.messages.ByeRequest;
import com.tg.rpstest.infra.messages.GameRequest;
import com.tg.rpstest.infra.messages.HelpRequest;
import com.tg.rpstest.infra.messages.LoginRequest;
import com.tg.rpstest.infra.messages.LogoutRequest;
import com.tg.rpstest.infra.messages.Message;
import com.tg.rpstest.infra.messages.Response;
import com.tg.rpstest.infra.processor.MessageProcessor;
import com.tg.rpstest.infra.processor.MessageProcessorFactory;
import com.tg.rpstest.server.domain.RPSMove;


/**
 *
 */
class ClientSession
{
    private final Scanner scanner;
    private final CommunicationHandler communicationHandler;
    private final MessageProcessorFactory processorFactory;

    ClientSession(final Scanner scanner,
                  final CommunicationHandler communicationHandler,
                  final MessageProcessorFactory processorFactory)
    {
        this.scanner = scanner;
        this.communicationHandler = communicationHandler;
        this.processorFactory = processorFactory;
    }

    void start()
    {
        String userOpt;
        do
        {
            showMainMenu();
            userOpt = scanner.nextLine();
            switch (userOpt)
            {
                case "1":
                {
                    processLoginRegisterOption();
                    break;
                }
                case "2":
                    processPlayGameOption(null);
                    break;
                case "3":
                    processHelpOption();
                    break;
                case "4":
                    processByeOption();
                    break;
                default:
                    System.out.println(">> Invalid option.");
                    break;
            }
        }
        while (!userOpt.equals("4"));
    }

    private void processLoginRegisterOption()
    {
        System.out.println(">> Please, introduce your name: ");
        final String userName = scanner.nextLine();

        // send request, wait for answer and process it
        sendMessage(new LoginRequest(userName));
        handleResponseMessage();

        // show menu for registered user
        handleRegisteredClientOptions(userName);
    }

    private void handleRegisteredClientOptions(final String userName)
    {
        String userOpt;
        do
        {
            showRegisteredClientMenu();
            userOpt = scanner.nextLine();
            switch (userOpt)
            {
                case "1":
                {
                    processPlayGameOption(userName);
                    break;
                }
                case "2":
                    processLogoutOption(userName);
                    break;
                default:
                    System.out.println(">> Invalid option.");
                    break;
            }
        }
        while (!userOpt.equals("2"));
    }

    private void processLogoutOption(final String userName)
    {
        // send request, wait for answer and process it
        sendMessage(new LogoutRequest(userName));
        handleResponseMessage();
    }

    private void processPlayGameOption(final String userName)
    {
        // get number of moves
        String rounds;
        int numRounds;
        while (true)
        {
            try
            {
                System.out.println(">> Introduce the number of desired rounds: ");
                rounds = scanner.nextLine();
                numRounds = Integer.parseInt(rounds);
                break;
            }
            catch (final NumberFormatException e)
            {
                System.out.println(">> Invalid number of rounds!");
            }
        }

        // get moves
        final List<RPSMove> moves = new ArrayList<>(numRounds);
        System.out.println(">> Introduce your moves...");
        System.out.println(">> The following moves are allowed: Rock (r), Paper (p), Scissors (s)");
        for (int i = 0; i < numRounds; i++)
        {
            String move;
            RPSMove rpsMove;
            while (true)
            {
                System.out.println(">>  For round " + (i + 1) + " your move will be: ");
                move = scanner.nextLine();
                rpsMove = RPSMove.parseRPSMove(move);
                if (rpsMove != null)
                {
                    break;
                }
                System.out.println(">> Invalid move!");
            }
            moves.add(rpsMove);
        }

        // send msg to server
        sendMessage(userName != null ?
            new GameRequest(numRounds, userName, moves) :
            new GameRequest(numRounds, moves));

        // wait for answer and present result
        handleResponseMessage();
    }

    private void showRegisteredClientMenu()
    {
        System.out.println("Please choose one of the following options:");
        System.out.println("1 Start Game");
        System.out.println("2 Logout");
    }

    private void processByeOption()
    {
        sendMessage(new ByeRequest());
    }

    private void processHelpOption()
    {
        sendMessage(new HelpRequest());
        handleResponseMessage();
    }

    private void sendMessage(final Message request)
    {
        try
        {
            communicationHandler.writeMessage(request);
        }
        catch (final IOException e)
        {
            System.err.println(e.getMessage());
            System.out.println("A problem has occurred when sending a message.");
        }
    }

    private void handleResponseMessage()
    {
        final Response response;
        try
        {
            response = (Response) communicationHandler.readMessage();
            final MessageProcessor processor = processorFactory.getMessageProcessor(response.getType());
            processor.processResponseMessage(response);
        }
        catch (final IOException | ClassNotFoundException e)
        {
            System.err.println(e.getMessage());
            System.out.println("A problem has occurred when receiving a message.");
        }
    }

    private void showMainMenu()
    {
        showClientMenuHeader();
        System.out.println("Please choose one of the following options:");
        System.out.println("1 Login/Register");
        System.out.println("2 Play as Guest");
        System.out.println("3 Help");
        System.out.println("4 Quit");
    }

    private void showClientMenuHeader()
    {
        System.out.println(">>>>>>> Welcome to Rock, Paper and Scissors (RPS) Client <<<<<<<<");
        System.out.println("-----------------------------------------------------------------");
    }
}
