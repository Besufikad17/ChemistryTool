package chemistrytool.tests;

import chemistrytool.util.Parser;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class ParserTest {
    public Parser p = new Parser();

    @org.junit.Test
    public void isVolume() throws Exception {
        assertTrue(p.isVolume("10litre"));
        assertFalse(p.isVolume("10atm"));
    }

    @Test
    public void isTemprature() throws Exception {
        assertTrue(p.isTemprature("10K"));
        assertFalse(p.isTemprature("10litre"));
    }

    @Test
    public void isPressure() throws Exception {
        assertFalse(p.isPressure("10litre"));
        assertTrue(p.isPressure("10atm"));
    }

    @Test
    public void isMass() throws Exception {
        assertTrue(p.isMass("10kg"));
        assertFalse(p.isMass("10atm"));
    }

    @Test
    public void isMole() throws Exception {
        assertTrue(p.isMole("10mol"));
        assertFalse(p.isMole("10atm"));
    }

    @Test
    public void isAcid() throws Exception {
        assertTrue(p.isAcid("HCl"));
        assertFalse(p.isAcid("NaOH"));
    }

    @Test
    public void isBase() throws Exception {
        assertTrue(p.isBase("NaOH"));
        assertFalse(p.isBase("HCl"));
    }

    @Test
    public void isNumberOfEquivalent() throws Exception {
        assertTrue(p.isNumberOfEquivalent("10kg/eq"));
        assertFalse(p.isNumberOfEquivalent("10kg"));
    }

    @Test
    public void getUnit() throws Exception {
        assertEquals("kg",p.getUnit("10kg"));
    }

    @Test
    public void parse() throws Exception {
        String[] givens = {"p1 = 10atm","p2 = 15atm","T1 = 100K","v2 = 2litre","v1 = 3litre"};
        String[] givens2 = {"p = 10atm","T = 100K","n = 2mol"};
        double[] expected = {10.0,15.0,3.0,2.0,100.0,0.0};
        double[] expected2 = {10.0,0.0,100.0,2.0};
        double[] actual = p.parse(givens);
        double[] actual2 = p.parse(givens2,true);
        for (int i = 0; i < expected.length;i++){
            assertEquals(expected[i],actual[i],0);
        }
        for (int i = 0; i < expected2.length;i++){
            assertEquals(expected2[i],actual2[i],0);
        }
    }

    @Test
    public void getElements() throws Exception {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Na");
        expected.add("Cl");
        ArrayList<String> actual = p.getElements("NaCl");
        for (int i =0;i < expected.size();i++){
            assertEquals(expected.get(i),actual.get(i));
        }
    }

    @Test
    public void getMolarMass() throws Exception {
        assertEquals(98.0,p.getMolarMass(p.getElements("H2SO4"),"H2SO4"),0);
    }

    @Test
    public void parse2() throws Exception {
        assertEquals(3,p.parse("3mol"),0);
    }

    @Test
    public void getOccurence() throws Exception {
        assertEquals(4,p.getOccurence("CH4","H"));
    }

    @Test
    public void calculate_molar_mass() throws Exception {
        assertEquals(58,p.calculate_molar_mass(2,p.getElements("Mg(OH)2")),0);
    }

    @Test
    public void calculateEquivalentMass() throws Exception {
        assertEquals(49,p.CalculateEquivalentMass("H2SO4"),0);
    }

}