package com.tg.rpstest.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


/**
 *
 */
public class MainServer
{
    public static void main(final String[] args)
    {
        final int portNumber;

        if (args.length == 1)
        {
            portNumber = Integer.parseInt(args[0]);
            try
            {
                startServer(portNumber);

            }
            catch (final Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            System.out.println("Invalid arguments!");
        }
    }

    private static void startServer(final int portNumber)
    {

        System.out.println("Waiting for client...");

        while (true)
        {

            int tie = 0, client = 0, server = 0;
            final int rounds;

            try
            {

                final ServerSocket serverSocket = new ServerSocket(portNumber);
                final Socket socket = serverSocket.accept();

                if (socket != null)
                {
                    System.out.println("Client connected");
                }

                final BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                final String tmp = lector.readLine();
                final String delims = "/";
                final String[] tokens = tmp.split(delims);
                rounds = Integer.parseInt(tokens[0]);
                System.out.println("Client has sent " + rounds + " shapes...");
                final String[] userEntries = tmp.split(delims);
                if (rounds + 1 - 1 >= 0)
                {
                    System.arraycopy(tokens, 1, userEntries, 0, rounds + 1 - 1);
                }

                final String[] programEntries = generateMoves(rounds);

                System.out.println("Shapes are chosen...");

                final String[] resultArray = new String[rounds];

                for (int i = 0; i < rounds; i++)
                {

                    switch (userEntries[i].toLowerCase())
                    {
                        case "rock":
                            switch (programEntries[i])
                            {
                                case "rock":
                                    resultArray[i] = "(tie)";
                                    tie++;
                                    break;
                                case "scissors":
                                    resultArray[i] = "(client wins)";
                                    client++;
                                    break;
                                case "paper":
                                    resultArray[i] = "(server wins)";
                                    server++;
                                    break;
                            }
                            break;
                        case "scissors":
                            switch (programEntries[i])
                            {
                                case "rock":
                                    resultArray[i] = "(server wins)";
                                    server++;
                                    break;
                                case "scissors":
                                    resultArray[i] = "(tie)";
                                    tie++;
                                    break;
                                case "paper":
                                    resultArray[i] = "(client wins)";
                                    client++;
                                    break;
                            }
                            break;
                        case "paper":
                            switch (programEntries[i])
                            {
                                case "rock":
                                    resultArray[i] = "(client wins)";
                                    client++;
                                    break;
                                case "scissors":
                                    resultArray[i] = "(server wins)";
                                    server++;
                                    break;
                                case "paper":
                                    resultArray[i] = "(tie)";
                                    tie++;
                                    break;
                            }
                            break;
                    }
                }

                printResults(tie, client, server, rounds, programEntries, resultArray);

                sendResultsToClient(tie, client, server, rounds, socket, programEntries, resultArray);

                serverSocket.close();

            }
            catch (final Exception ex)
            {
                break;
            }
        }

        System.out.println("Client disconnected");
        System.out.println("Server terminating...");
    }

    private static void sendResultsToClient(final int tie,
                                            final int client,
                                            final int server,
                                            final int rounds,
                                            final Socket socket,
                                            final String[] programEntries, final String[] resultArray) throws IOException
    {
        final PrintStream pr = new PrintStream(socket.getOutputStream());

        final StringBuilder msgToClient = new StringBuilder();
        for (int i = 0; i < rounds; i++)
        {
            msgToClient.append("Round-")
                .append(i + 1)
                .append(": ")
                .append("server chooses ")
                .append(programEntries[i])
                .append(" ")
                .append(resultArray[i]).append("/");
        }
        msgToClient.append("Client: ")
            .append(client)
            .append("/")
            .append("Tie: ")
            .append(tie)
            .append("/")
            .append("Server: ")
            .append(server);
        pr.println(msgToClient);
    }

    private static void printResults(final int tie,
                                     final int client,
                                     final int server,
                                     final int rounds,
                                     final String[] programEntries,
                                     final String[] resultArray)
    {
        System.out.println("Results are as follows: ");
        for (int i = 0; i < rounds; i++)
        {
            System.out.println("Round-" + (i + 1) + ": " + "server chooses " + programEntries[i] + " " + resultArray[i]);
        }
        System.out.println("Client: " + client + "\n" + "Tie: " + tie + "\n" + "Server: " + server);
    }

    private static String[] generateMoves(final int rounds)
    {
        final Random rg = new Random();
        final int[] randomArray = new int[rounds];
        for (int i = 0; i < rounds; i++)
        {
            randomArray[i] = rg.nextInt(3) + 1;
        }

        final String[] programEntries = new String[rounds];
        for (int i = 0; i < rounds; i++)
        {
            if (randomArray[i] == 1)
            {
                programEntries[i] = "rock";
            }
            else if (randomArray[i] == 2)
            {
                programEntries[i] = "paper";
            }
            else if (randomArray[i] == 3)
            {
                programEntries[i] = "scissors";
            }
        }
        return programEntries;
    }
}
