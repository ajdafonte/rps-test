package com.tg.rpstest.infra.messages;

import java.util.Objects;


/**
 *
 */
public class LogoutRequest extends Request
{
    private static final long serialVersionUID = 2796071270175977483L;
    private final String userName;

    public LogoutRequest(final String userName)
    {
        super(MessageType.LOGOUT_REQUEST);
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
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
        final LogoutRequest that = (LogoutRequest) o;
        return Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), userName);
    }

    @Override
    public String toString()
    {
        return "LogoutRequest{" +
            "userName='" + userName + '\'' +
            "} " + super.toString();
    }
}
