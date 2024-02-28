package edu.appstate.cs.projectname;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AnotherTest {
    
    @Test
    public void testNormalPercentComputation() {
        float result = App.computePercent(50, 100);
        assertEquals(50.0, result, "50 out of 100 is 50%");
    }

    @Test
    public void testZeroDenominator() {
        float result = App.computePercent(50, 0);
        assertEquals(0.0, result, "Should get a 0.0 with 0.0 for denominator");
    }
}
