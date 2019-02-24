package com.tg.rpstest.server.domain;

/**
 *
 */
public enum RPSResult
{
    PLAYER1_WIN(1), TIE(0), PLAYER2_WIN(2);

    private final int value;

    public int getValue()
    {
        return value;
    }

    RPSResult(final int value)
    {
        this.value = value;
    }}
