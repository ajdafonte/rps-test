package com.tg.rpstest.server.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


/**
 * RPSMoveTest class - Test RPSMove enum.
 */
public class RPSMoveTest
{
    // test method that parses moves - ok
    @Test
    public void givenValidStringValues_whenParsingRPSMoves_returnCorrectValue()
    {
        // given
        final String[] testCases = {
            "r", "p", "s"
        };
        final RPSMove[] expectedValues = {
            RPSMove.ROCK, RPSMove.PAPER, RPSMove.SCISSOR
        };

        // when + then
        for (int i = 0; i < testCases.length; i++)
        {
            assertEquals(expectedValues[i], RPSMove.parseRPSMove(testCases[i]));
        }
    }

    // test method that parses moves - nok
    @Test
    public void givenInvalidStringValues_whenParsingRPSMoves_returnDefaultValue()
    {
        // given
        final String[] testCases = {
            null, "lol", "scissor"
        };

        // when + then
        for (final String testCase : testCases)
        {
            assertNull(RPSMove.parseRPSMove(testCase));
        }
    }

    // test rock method beat
    @Test
    public void givenRockEnumValue_whenCheckingWhatMoveBeats_returnCorrectValue()
    {
        assertFalse(RPSMove.ROCK.beats(RPSMove.PAPER));
        assertTrue(RPSMove.ROCK.beats(RPSMove.SCISSOR));
    }

    // test paper methods
    @Test
    public void givenPaperEnumValue_whenCheckingWhatMoveBeats_returnCorrectValue()
    {
        assertFalse(RPSMove.PAPER.beats(RPSMove.SCISSOR));
        assertTrue(RPSMove.PAPER.beats(RPSMove.ROCK));
    }

    // test scissor methods
    @Test
    public void givenScissorEnumValue_whenCheckingWhatMoveBeats_returnCorrectValue()
    {
        assertFalse(RPSMove.SCISSOR.beats(RPSMove.ROCK));
        assertTrue(RPSMove.SCISSOR.beats(RPSMove.PAPER));
    }

    // test method toString
    @Test
    public void givenEnumValues_whenCallToString_returnCorrectValue()
    {
        assertEquals("Rock", RPSMove.ROCK.toString());
        assertEquals("Paper", RPSMove.PAPER.toString());
        assertEquals("Scissor", RPSMove.SCISSOR.toString());
    }

}
