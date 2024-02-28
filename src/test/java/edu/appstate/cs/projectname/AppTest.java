package edu.appstate.cs.projectname;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest
{
    @Test
    public void testApp()
    {
        assertTrue(true, "This should never fail");
    }

    @Test
    public void testSum()
    {
        assertEquals(3, App.sum(1,2), "1 + 2 should be 3");
    }
}