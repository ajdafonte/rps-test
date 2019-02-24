package com.tg.rpstest.server;

import java.io.IOException;

import com.tg.rpstest.infra.CommunicationHandler;
import com.tg.rpstest.infra.messages.MessageType;
import com.tg.rpstest.infra.messages.Request;
import com.tg.rpstest.infra.messages.Response;
import com.tg.rpstest.infra.processor.MessageProcessor;
import com.tg.rpstest.infra.processor.MessageProcessorFactory;


/**
 *
 */
public class ServerSession
{
    private final CommunicationHandler communicationHandler;
    private final ServerContext serverContext;
    private final MessageProcessorFactory processorFactory;

    public ServerSession(final CommunicationHandler communicationHandler,
                         final ServerContext serverContext,
                         final MessageProcessorFactory processorFactory)
    {
        this.communicationHandler = communicationHandler;
        this.serverContext = serverContext;
        this.processorFactory = processorFactory;
    }

    public void start() throws IOException, ClassNotFoundException
    {
        boolean processMessages = true;
        while (processMessages)
        {
            final Request request;
            // waiting to receive request from client
            request = (Request) communicationHandler.readMessage();

            // process request
            final MessageProcessor processor = processorFactory.getMessageProcessor(request.getType());
            final Response response = processor.processRequestMessage(request, serverContext);

            // send response to client
            communicationHandler.writeMessage(response);

            // check if should continue to processing messages
            processMessages = !(request.getType() == MessageType.BYE_REQUEST);
        }
    }
}
