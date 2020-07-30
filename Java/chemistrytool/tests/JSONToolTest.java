package chemistrytool.tests;

import chemistrytool.util.JSONTool;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JSONToolTest {
    @org.junit.Test
    public void decode() throws Exception {
        JSONTool js = new JSONTool();
        assertEquals("Hydrogen",js.decode(1).get("name"));
    }

    @Test
    public void decode1() throws Exception {
        JSONTool js = new JSONTool();
        assertEquals("Hydrogen",js.decode("H").get("name"));
    }

}