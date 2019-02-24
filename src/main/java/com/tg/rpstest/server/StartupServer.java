package com.tg.rpstest.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import com.tg.rpstest.error.RPSException;
import com.tg.rpstest.error.StartupArgumentException;
import com.tg.rpstest.infra.CommunicationHandler;
import com.tg.rpstest.infra.StartupArgumentHandler;
import com.tg.rpstest.infra.processor.MessageProcessorFactory;


/**
 *
 */
public class StartupServer
{
    private ServerSocket serverSocket;
    private CommunicationHandler communicationHandler;

    private void init(final String[] args)
    {
        try
        {
            // validate and retrieve infra args
            StartupArgumentHandler.validateServerArguments(args);
            final String port = StartupArgumentHandler.getServerPort(args);

            // start server
            serverSocket = new ServerSocket(Integer.parseInt(port));
            System.out.println(">> Server listening on port " + port);
            System.out.println(">> Waiting for a client...");
            final Socket clientSocket = serverSocket.accept();

            // only when client connects it makes sense to define streams
            assert clientSocket != null;
            System.out.println(">> Client Connected");

            // setup and start server session
            communicationHandler = new CommunicationHandler(clientSocket);
            final ServerContext serverContext = new ServerContext();
            final MessageProcessorFactory processorFactory = new MessageProcessorFactory();
            final ServerSession serverSession = new ServerSession(communicationHandler, serverContext, processorFactory);
            // will be processing messages (until client says bye)
            serverSession.start();
        }
        catch (final SocketException e)
        {
            System.err.println(e.getMessage());
            System.out.println(">> A problem has occurred because client has terminated the connection abruptly.");
        }
        catch (final IOException e)
        {
            System.err.println(e.getMessage());
            System.out.println(">> A problem has occurred during server initialization.");
        }
        catch (final RPSException e)
        {
            System.err.println(e.getMessage());
            System.out.println(">> A problem has occurred establishing connection with client.");
        }
        catch (final StartupArgumentException e)
        {
            System.err.println(e.getMessage());
            System.out.println(StartupArgumentHandler.generateServerStartupHelpMessage());
        }
        catch (final ClassNotFoundException e)
        {
            System.err.println(e.getMessage());
            System.out.println(">> A problem has occurred when receiving a message.");
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
        System.out.println(">> Client has disconnected");
        System.out.println(">> Server is going to shutdown...");
        communicationHandler.closeStreams();
        serverSocket.close();
    }

    public static void main(final String[] args)
    {
        final StartupServer server = new StartupServer();
        server.init(args);
    }
}
