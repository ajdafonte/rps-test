package com.tg.rpstest.infra.processor;

import com.tg.rpstest.infra.messages.Request;
import com.tg.rpstest.infra.messages.Response;
import com.tg.rpstest.server.ServerContext;


/**
 *
 */
public interface MessageProcessor
{
    Response processRequestMessage(Request request, ServerContext serverContext);

    void processResponseMessage(Response response);
}
