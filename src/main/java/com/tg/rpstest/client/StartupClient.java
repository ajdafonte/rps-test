package com.tg.rpstest.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.tg.rpstest.error.RPSException;
import com.tg.rpstest.error.StartupArgumentException;
import com.tg.rpstest.infra.CommunicationHandler;
import com.tg.rpstest.infra.StartupArgumentHandler;
import com.tg.rpstest.infra.processor.MessageProcessorFactory;


/**
 *
 */
public class StartupClient
{
    private Scanner scanner;
    private CommunicationHandler communicationHandler;

    private void init(final String[] args)
    {
        try
        {
            // validate and retrieve infra args
            StartupArgumentHandler.validateClientArguments(args);
            final String host = StartupArgumentHandler.getHost(args);
            final String port = StartupArgumentHandler.getClientPort(args);

            // Create connection with server
            final Socket clientSocket = new Socket(host, Integer.parseInt(port));
            final MessageProcessorFactory processorFactory = new MessageProcessorFactory();
            communicationHandler = new CommunicationHandler(clientSocket);
            scanner = new Scanner(System.in);

            // start client session
            final ClientSession clientSession = new ClientSession(scanner, communicationHandler, processorFactory);
            clientSession.start();
        }
        catch (final UnknownHostException e)
        {
            System.err.println(e.getMessage());
            System.out.println(">> The IP address of a host could not be determined.");
        }
        catch (final IOException e)
        {
            System.err.println(e.getMessage());
            System.out.println(">> A problem has occurred during client initialization.");
        }
        catch (final RPSException e)
        {
            System.err.println(e.getMessage());
            System.out.println(">> A problem has occurred establishing connection with server.");
        }
        catch (final StartupArgumentException e)
        {
            System.err.println(e.getMessage());
            System.out.println(StartupArgumentHandler.generateClientStartupHelpMessage());
        }
        finally
        {
            try
            {
                shutdown();
            }
            catch (final IOException e)
            {
                System.err.println(e.getMessage());
                System.out.println(">> A problem has occurred during shutdown.");
            }
        }
    }

    private void shutdown() throws IOException
    {
        System.out.println(">> Shutdown client.");
        scanner.close();
        communicationHandler.closeStreams();
    }

    public static void main(final String[] args)
    {
        final StartupClient client = new StartupClient();
        client.init(args);
    }
}
