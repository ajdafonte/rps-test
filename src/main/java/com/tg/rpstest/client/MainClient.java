package com.tg.rpstest.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


/**
 *
 */
public class MainClient
{
    public static void main(final String[] args)
    {
        InetAddress ipAddress;
        final int portNumber;

        if (args.length == 2)
        {
            try
            {
                ipAddress = InetAddress.getByName(args[0]);
            }
            catch (final UnknownHostException e)
            {
                System.out.println(e.getMessage());
                ipAddress = null;
            }
            portNumber = Integer.parseInt(args[1]);

            try
            {
                startClient(ipAddress, portNumber);

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

    private static void startClient(final InetAddress ipAddress, final int portNumber)
    {

        int rounds;
        String roundNumber = "a";
        Scanner scanner = null;

        while (!roundNumber.toLowerCase().equals("q"))
        {
            scanner = new Scanner(System.in);
            try
            {
                final Socket sock = new Socket(ipAddress, portNumber);
                final PrintStream pr = new PrintStream(sock.getOutputStream());

                System.out.println("Enter the number of rounds (Press Q to quit): ");

                roundNumber = scanner.nextLine();

                if (roundNumber.toLowerCase().equals("q"))
                {
                    break;
                }
                else
                {
                    rounds = Integer.parseInt(roundNumber);
                    if (rounds <= 0)
                    {
                        System.out.println("You cannot enter 0 or lower.");
                    }
                }

                final String[] userEntries = new String[rounds];
                for (int i = 0; i < rounds; i++)
                {
                    System.out.print("Round-" + (i + 1) + ": ");
                    userEntries[i] = scanner.nextLine();
                }

                final StringBuilder msgToServer = new StringBuilder(roundNumber);
                for (int i = 0; i < rounds; i++)
                {
                    msgToServer.append('/').append(userEntries[i]);
                }

                final InputStream stream = new ByteArrayInputStream(msgToServer.toString().getBytes(StandardCharsets.UTF_8));

                final InputStreamReader rd = new InputStreamReader(stream);
                final BufferedReader ed = new BufferedReader(rd);

                final String temp = ed.readLine();

                pr.println(temp);

                // Getting the result from server
                final BufferedReader gt = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                final String tm = gt.readLine();
                final String[] tokens = tm.split("/");

                for (final String t : tokens)
                {
                    System.out.println(t);
                }

                sock.close();

            }

            catch (final Exception e)
            {
                System.out.println(e.getMessage());

            }
        }

        System.out.println("Client is terminated");

        scanner.close();
    }
}
