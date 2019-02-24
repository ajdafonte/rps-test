package com.tg.rpstest.infra.messages;

import java.util.Objects;


/**
 *
 */
public class LogoutResponse extends Response
{
    private static final long serialVersionUID = 3639728209771118527L;
    private final String description;

    public LogoutResponse(final ResponseStatus status, final String description)
    {
        super(MessageType.LOGOUT_RESPONSE, status);
        this.description = description;
    }

    public String getDescription()
    {
        return description;
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
        final LogoutResponse that = (LogoutResponse) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), description);
    }

    @Override
    public String toString()
    {
        return "LogoutResponse{" +
            "description='" + description + '\'' +
            "} " + super.toString();
    }
}
