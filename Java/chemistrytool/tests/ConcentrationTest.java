package chemistrytool.tests;

import chemistrytool.concentration.Concentration;

import static junit.framework.TestCase.assertEquals;

public class ConcentrationTest {
    @org.junit.Test
    public void calculateMolarity() throws Exception {
        Concentration c = new Concentration("16gram","CH3OH","200millilitre");
        assertEquals(2.5806451612903225,c.CalculateMolarity(),0);
    }

    @org.junit.Test
    public void calculateMolality() throws Exception {
        Concentration c = new Concentration("32gram","NaCl","271gram solvent");
        assertEquals(2.018481723278771,c.CalculateMolality(),0);
    }

    @org.junit.Test
    public void calculateNormality() throws Exception {
        Concentration c = new Concentration("49gram","H2SO4","500millilitre");
        assertEquals(2,c.CalculateNormality(),0);
    }

    @org.junit.Test
    public void calculatePercentageComposition() throws Exception {
        Concentration c = new Concentration("Na","NaCl");
        assertEquals("","There is " + 39.31623931623932 + "% of Na " + "in NaCl",c.CalculatePercentageComposition());
    }

    @org.junit.Test
    public void getElectronicConfiguration() throws Exception {
        Concentration c = new Concentration("Li");
        assertEquals("","1s2 2s1",c.getElectronicConfiguration());
    }

}
