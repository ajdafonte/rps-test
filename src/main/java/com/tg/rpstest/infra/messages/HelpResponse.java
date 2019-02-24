package com.tg.rpstest.infra.messages;

import java.util.Objects;


/**
 *
 */
public class HelpResponse extends Response
{
    private static final long serialVersionUID = 4577485519120038924L;
    private final String helpMessage;

    public HelpResponse(final ResponseStatus status, final String helpMessage)
    {
        super(MessageType.HELP_RESPONSE, status);
        this.helpMessage = helpMessage;
    }

    public String getHelpMessage()
    {
        return helpMessage;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        if (!super.equals(o))
        {
            return false;
        }
        final HelpResponse that = (HelpResponse) o;
        return Objects.equals(helpMessage, that.helpMessage);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), helpMessage);
    }

    @Override
    public String toString()
    {
        return "HelpResponse{" +
            "helpMessage='" + helpMessage + '\'' +
            "} " + super.toString();
    }
}
