package com.tg.rpstest.infra.messages;

import java.util.Objects;


/**
 *
 */
public class LoginResponse extends Response
{
    private static final long serialVersionUID = -6740871579773643450L;
    private final String description;

    public LoginResponse(final ResponseStatus status, final String description)
    {
        super(MessageType.LOGIN_RESPONSE, status);
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
        final LoginResponse that = (LoginResponse) o;
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
        return "LoginResponse{" +
            "description='" + description + '\'' +
            "} " + super.toString();
    }
}
