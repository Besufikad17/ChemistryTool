package chemistrytool.tests;

import chemistrytool.gaslaws.GasLaws;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class GasLawsTest {
    @org.junit.Test
    public void calculateBoyles() throws Exception {
        GasLaws g = new GasLaws("p1 = 10atm","p2 = 15atm","v1 = 3litre");
        assertEquals(2,Double.parseDouble(g.CalculateBoyles()),0);
    }

    @Test
    public void calculateCharles() throws Exception {
        GasLaws g = new GasLaws("T1 = 100K","v2 = 2litre","v1 = 3litre");
        assertEquals(66.66666666666667,Double.parseDouble(g.CalculateCharles()),0);
    }

    @Test
    public void calculateCombined() throws Exception {
        GasLaws g = new GasLaws("p1 = 10atm","p2 = 15atm","T1 = 100K","v2 = 2litre","v1 = 3litre");
        assertEquals(100,Double.parseDouble(g.CalculateCombined()),0);
    }

    @Test
    public void calculateIdeal() throws Exception {
        GasLaws g = new GasLaws("p = 10atm","T = 100K","n = 2mol");
        assertEquals(1.6400000000000001,Double.parseDouble(g.CalculateIdeal()),0);
    }

    @Test
    public void calculateGrhams() throws Exception {
        GasLaws g = new GasLaws("H","CH4");
        assertEquals("", "H" + " diffuses " + 4.0
                + " times than " + "CH4",g.CalculateGrhams());
    }

}
