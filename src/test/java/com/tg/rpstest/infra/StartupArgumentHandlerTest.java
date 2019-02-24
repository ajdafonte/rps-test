package com.tg.rpstest.infra;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.tg.rpstest.error.StartupArgumentException;


/**
 * StartupArgumentHandlerTest class - Test StartupArgumentHandler class.
 */
public class StartupArgumentHandlerTest
{

    //client - test ok args
    @Test
    public void givenValidClientArgs_whenValidatingClientArgs_thenValidationOk()
    {
        // given
        final String[][] testCases = {
            new String[] {"localhost", "8888"},
            new String[] {"127.0.0.1", "8080"}
        };

        // when + then
        for (final String[] mockArgs : testCases)
        {
            assertDoesNotThrow(() -> StartupArgumentHandler.validateClientArguments(mockArgs));
        }
    }

    //server - test ok args
    @Test
    public void givenValidServerArgs_whenValidatingServerArgs_thenValidationOk()
    {
        // given
        final String[][] testCases = {
            new String[] {"8888"},
            new String[] {"1"},
            new String[] {"65535"},
            new String[] {"44444"}
        };

        // when + then
        for (final String[] mockArgs : testCases)
        {
            assertDoesNotThrow(() -> StartupArgumentHandler.validateServerArguments(mockArgs));
        }
    }

    //client - test num infra args invalid
    @Test
    public void givenNumClientArgsInvalid_whenValidatingClientArgs_thenThrowSpecificException()
    {
        // given
        final String[][] testCases = {
            new String[] {"1"},
            new String[] {"1", "2", "3"},
            new String[] {"1", "2", "3", "4"},
            new String[] {},
        };

        // when + then
        for (final String[] mockArgs : testCases)
        {
            assertThrows(StartupArgumentException.class, () -> StartupArgumentHandler.validateClientArguments(mockArgs));
        }
    }

    //server - test num infra args invalid
    @Test
    public void givenNumServerArgsInvalid_whenValidatingServerArgs_thenThrowSpecificException()
    {
        // given
        final String[][] testCases = {
            new String[] {"1", "222"},
            new String[] {"1", "2", "3"},
            new String[] {"1", "2", "3", "4"},
            new String[] {},
        };

        // when + then
        for (final String[] mockArgs : testCases)
        {
            assertThrows(StartupArgumentException.class, () -> StartupArgumentHandler.validateServerArguments(mockArgs));
        }
    }

    //client - invalid host
    @Test
    public void givenClientArgsWithInvalidHost_whenValidatingClientArgs_thenThrowSpecificException()
    {
        // given
        final String[][] testCases = {
            new String[] {"mymachine", "222"},
            new String[] {"local", "4442"},
            new String[] {"300.3.4.6", "4"},
            new String[] {"156.3.4.6.7.9", "4"},
            new String[] {"--", "090"},
        };

        // when + then
        for (final String[] mockArgs : testCases)
        {
            assertThrows(StartupArgumentException.class, () -> StartupArgumentHandler.validateClientArguments(mockArgs));
        }

    }

    //client - invalid port
    @Test
    public void givenClientArgsWithInvalidPort_whenValidatingClientArgs_thenThrowSpecificException()
    {
        // given
        final String[][] testCases = {
            new String[] {"localhost", "0"},
            new String[] {"localhost", "70000"},
            new String[] {"localhost", "-1"},
            new String[] {"localhost", "abc"},
            new String[] {"localhost", ""}
        };

        // when + then
        for (final String[] mockArgs : testCases)
        {
            assertThrows(StartupArgumentException.class, () -> StartupArgumentHandler.validateClientArguments(mockArgs));
        }
    }

    //server - invalid port
    @Test
    public void givenServerArgsWithInvalidPort_whenValidatingServerArgs_thenThrowSpecificException()
    {
        // given
        final String[][] testCases = {
            new String[] {"0"},
            new String[] {"70000"},
            new String[] {"-1"},
            new String[] {"abc"},
            new String[] {""}
        };

        // when + then
        for (final String[] mockArgs : testCases)
        {
            assertThrows(StartupArgumentException.class, () -> StartupArgumentHandler.validateServerArguments(mockArgs));
        }
    }

    //client - test getHost ok
    @Test
    public void givenValidClientArgsNumber_whenGetHostArg_thenReturnHostValue()
    {
        // given
        final String[] mockArgs = {"localhost", "500"};
        final String expectedHost = "localhost";

        // when
        final String host = StartupArgumentHandler.getHost(mockArgs);

        // then
        assertFalse(host.isEmpty());
        assertEquals(expectedHost, host);
    }

    //client - test getHost nok
    @Test
    public void givenInvalidClientArgsNumber_whenGetHostArg_thenReturnEmptyValue()
    {
        // given
        final String[] mockArgs = {"localhost"};

        // when
        final String host = StartupArgumentHandler.getHost(mockArgs);

        // then
        assertTrue(host.isEmpty());
    }

    //client - test getPort ok
    @Test
    public void givenValidClientArgsNumber_whenGetClientPortArg_thenReturnPortValue()
    {
        // given
        final String[] mockArgs = {"localhost", "500"};
        final String expectedPort = "500";

        // when
        final String port = StartupArgumentHandler.getClientPort(mockArgs);

        // then
        assertFalse(port.isEmpty());
        assertEquals(expectedPort, port);
    }

    //client - test getPort nok
    @Test
    public void givenInvalidClientArgsNumber_whenGetClientPortArg_thenReturnEmptyValue()
    {
        // given
        final String[] mockArgs = {"local"};

        // when
        final String port = StartupArgumentHandler.getClientPort(mockArgs);

        // then
        assertTrue(port.isEmpty());
    }

    //server - test getPort ok
    @Test
    public void givenValidServerArgsNumber_whenGetServerPortArg_thenReturnPortValue()
    {
        // given
        final String[] mockArgs = {"500"};
        final String expectedPort = "500";

        // when
        final String port = StartupArgumentHandler.getServerPort(mockArgs);

        // then
        assertFalse(port.isEmpty());
        assertEquals(expectedPort, port);
    }

    //server - test getPort nok
    @Test
    public void givenInvalidServerArgsNumber_whenGetServerPortArg_thenReturnEmptyValue()
    {
        // given
        final String[] mockArgs = {"localhost", "500"};

        // when
        final String port = StartupArgumentHandler.getServerPort(mockArgs);

        // then
        assertTrue(port.isEmpty());
    }

    //test clientStartupMessage
    @Test
    public void whenGetClientStartupHelpMessage_thenReturnCorrectMessageValue()
    {
        // given
        final String newLine = System.lineSeparator();
        final String expectedMessage = ">> Invalid arguments to start the RPS Client."
            + newLine
            + ">> Arguments allowed: <host> <port>"
            + newLine
            + ">> Example to start RPS Client: <appName> <host> <port>"
            + newLine
            + ">> Constraints:"
            + newLine
            + ">> - <host> : Can either be a machine name or a textual representation of its IP address"
            + newLine
            + ">> - <port> : Should be a number that belong to the following range [1;65535]"
            + newLine;

        // when
        final String message = StartupArgumentHandler.generateClientStartupHelpMessage();

        // then
        assertFalse(message.isEmpty());
        assertEquals(expectedMessage, message);
    }

    //test serverStartupMessage
    @Test
    public void whenGetServerStartupHelpMessage_thenReturnCorrectMessageValue()
    {
        // given
        final String newLine = System.lineSeparator();
        final String expectedMessage = ">> Invalid arguments to start the RPS Server."
            + newLine
            + ">> Arguments allowed: <port>"
            + newLine
            + ">> Example to start RPS Port: <appName> <port>"
            + newLine
            + ">> Constraints:"
            + newLine
            + ">> - <port> : Should be a number that belong to the following range [1;65535]"
            + newLine;

        // when
        final String message = StartupArgumentHandler.generateServerStartupHelpMessage();

        // then
        assertFalse(message.isEmpty());
        assertEquals(expectedMessage, message);
    }
}
