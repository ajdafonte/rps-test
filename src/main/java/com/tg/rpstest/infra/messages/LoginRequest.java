package com.tg.rpstest.infra.messages;

import java.util.Objects;


/**
 *
 */
public class LoginRequest extends Request
{
    private static final long serialVersionUID = -783708580107958368L;
    private final String userName;

    public LoginRequest(final String userName)
    {
        super(MessageType.LOGIN_REQUEST);
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
        final LoginRequest that = (LoginRequest) o;
        return userName.equals(that.userName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), userName);
    }

    @Override
    public String toString()
    {
        return "LoginRequest{" +
            "userName='" + userName + '\'' +
            "} " + super.toString();
    }
}
