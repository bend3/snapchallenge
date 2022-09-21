package hu.duck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
class AppTest {
    /**
     * Rigorous Test.
     */
    @Test
    void testApp() {
        MadScientist madScientist = new MadScientist(new int[] {1, 1, 2, 3, 3, 3, 2, 10 });
        assertEquals(13, madScientist.getMinFood());
    }

}
