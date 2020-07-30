package chemistrytool.tests;

import chemistrytool.iupac.IUPAC;

import static org.junit.Assert.assertEquals;

public class IUPACTest {
    @org.junit.Test
    public void getIUPACName() throws Exception {
        IUPAC i = new IUPAC("CH3CH3");
        assertEquals("Ethane",i.getIUPACName());
    }
}
