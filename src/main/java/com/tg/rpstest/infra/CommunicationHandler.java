package com.tg.rpstest.infra;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.tg.rpstest.error.RPSException;


/**
 *
 */
public class CommunicationHandler
{
    // TODO - Reader and writer should be injected through constructor (to facilitate unit testing)
    private final ObjectOutputStream writer;
    private final ObjectInputStream reader;
    private final Socket socket;

    public CommunicationHandler(final Socket socket) throws RPSException
    {
        try
        {
            this.socket = socket;
            this.writer = new ObjectOutputStream(socket.getOutputStream());
            this.reader = new ObjectInputStream(socket.getInputStream());
        }
        catch (final IOException e)
        {
            throw new RPSException("Unable to establish connection.", e);
        }
    }

    public void writeMessage(final Object message) throws IOException
    {
        writer.writeObject(message);
        writer.flush();
    }

    public Object readMessage() throws IOException, ClassNotFoundException
    {
        return reader.readObject();
    }

    public void closeStreams() throws IOException
    {
        socket.close();
        writer.close();
        reader.close();
    }
}
