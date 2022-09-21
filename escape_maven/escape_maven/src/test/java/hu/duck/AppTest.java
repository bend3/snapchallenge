package hu.duck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

/**
 * Unit test for simple App.
 */
class AppTest {
    /**
     * Rigorous Test.
     */
    @Test
    void testApp() {
        String result = "[{ \"x\": 1, \"y\": 4 },{ \"x\": 1, \"y\": 3 },{ \"x\": 2, \"y\": 3 },{ \"x\": 3, \"y\": 3 },{ \"x\": 3, \"y\": 2 },{ \"x\": 3, \"y\": 1 },{ \"x\": 3, \"y\": 2 },{ \"x\": 3, \"y\": 3 },{ \"x\": 2, \"y\": 3 },{ \"x\": 1, \"y\": 3 },{ \"x\": 1, \"y\": 2 },{ \"x\": 1, \"y\": 1 },{ \"x\": 1, \"y\": 0 }]";
        JSONArray jsonArray = new JSONArray(result);
        List<Position> expected = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            expected.add(new Position(jsonArray.getJSONObject(i).getInt("x"), jsonArray.getJSONObject(i).getInt("y")));
        }
        String[][] map = {
            {"#", "E", "#", "#", "#"},
            {"#", ".", "#", "K", "#"},
            {"#", ".", "#", ".", "#"},
            {"#", ".", ".", ".", "#"},
            {"#", "S", "#", "#", "#"},

        };
        assertEquals(expected, new Escape(map).getEscapeRoute());

    }
}
