package com.tg.rpstest.infra;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.tg.rpstest.error.StartupArgumentException;


/**
 *
 */
public class StartupArgumentHandler
{
    private static final int MIN_PORT_VALUE = 1;
    private static final int MAX_PORT_VALUE = 65535;
    private static final String NEW_LINE = System.lineSeparator();

    public static void validateClientArguments(final String[] args) throws StartupArgumentException
    {
        if (!validNumberOfClientArgs(args))
        {
            throw new StartupArgumentException("Invalid number of infra args.");
        }

        final String host = args[0];
        final String port = args[1];

        if (!(validHost(host) && validPort(port)))
        {
            throw new StartupArgumentException("Invalid definition of infra args.");
        }
    }

    public static void validateServerArguments(final String[] args) throws StartupArgumentException
    {
        if (!validNumberOfServerArgs(args))
        {
            throw new StartupArgumentException("Invalid number of infra args.");
        }

        final String port = args[0];

        if (!validPort(port))
        {
            throw new StartupArgumentException("Invalid definition of infra args.");
        }
    }

    public static String getHost(final String[] args)
    {
        return validNumberOfClientArgs(args) ? args[ClientArgumentsType.HOST.position] : "";
    }

    public static String getClientPort(final String[] args)
    {
        return validNumberOfClientArgs(args) ? args[ClientArgumentsType.PORT.position] : "";
    }

    public static String getServerPort(final String[] args)
    {
        return validNumberOfServerArgs(args) ? args[ServerArgumentsType.PORT.position] : "";
    }

    public static String generateClientStartupHelpMessage()
    {
        return ">> Invalid arguments to start the RPS Client."
            + NEW_LINE
            + ">> Arguments allowed: <host> <port>"
            + NEW_LINE
            + ">> Example to start RPS Client: <appName> <host> <port>"
            + NEW_LINE
            + ">> Constraints:"
            + NEW_LINE
            + ">> - <host> : Can either be a machine name or a textual representation of its IP address"
            + NEW_LINE
            + ">> - <port> : Should be a number that belong to the following range ["
            + MIN_PORT_VALUE + ";" + MAX_PORT_VALUE + "]"
            + NEW_LINE;
    }

    public static String generateServerStartupHelpMessage()
    {
        return ">> Invalid arguments to start the RPS Server."
            + NEW_LINE
            + ">> Arguments allowed: <port>"
            + NEW_LINE
            + ">> Example to start RPS Port: <appName> <port>"
            + NEW_LINE
            + ">> Constraints:"
            + NEW_LINE
            + ">> - <port> : Should be a number that belong to the following range ["
            + MIN_PORT_VALUE + ";" + MAX_PORT_VALUE + "]"
            + NEW_LINE;
    }

    private static boolean validNumberOfClientArgs(final String[] args)
    {
        return args.length == ClientArgumentsType.getNumberArgs();
    }

    private static boolean validNumberOfServerArgs(final String[] args)
    {
        return args.length == ServerArgumentsType.getNumberArgs();
    }

    private static boolean validHost(final String host)
    {
        try
        {
            InetAddress.getByName(host);
        }
        catch (final UnknownHostException e)
        {
            return false;
        }
        return true;
    }

    private static boolean validPort(final String port)
    {
        try
        {
            final int value = Integer.parseInt(port);
            return value >= MIN_PORT_VALUE && value <= MAX_PORT_VALUE;
        }
        catch (final NumberFormatException ex)
        {
            return false;
        }
    }

    private enum ClientArgumentsType
    {
        HOST(0), PORT(1);

        private final int position;

        ClientArgumentsType(final int position)
        {
            this.position = position;
        }

        private static int getNumberArgs()
        {
            return ClientArgumentsType.values().length;
        }

    }

    private enum ServerArgumentsType
    {

        PORT(0);

        private final int position;

        ServerArgumentsType(final int position)
        {
            this.position = position;
        }

        private static int getNumberArgs()
        {
            return ServerArgumentsType.values().length;
        }
    }

}
