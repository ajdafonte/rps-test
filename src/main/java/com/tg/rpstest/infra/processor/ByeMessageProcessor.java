package com.tg.rpstest.infra.processor;

import com.tg.rpstest.infra.messages.Request;
import com.tg.rpstest.infra.messages.Response;
import com.tg.rpstest.server.ServerContext;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


/**
 *
 */
public class ByeMessageProcessor implements MessageProcessor
{
    @Override
    public Response processRequestMessage(final Request message, final ServerContext serverContext)
    {
        System.out.println("----- Processing bye message request -----");
        System.out.println(">> The client has indicated that is going to disconnect.");
        return null;
    }

    @Override
    public void processResponseMessage(final Response message)
    {
        throw new NotImplementedException();
    }
}
