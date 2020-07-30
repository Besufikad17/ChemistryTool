package chemistrytool.tests;

import chemistrytool.element.Element;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElementTest {

    public Element hydrogen = new Element("H");
    public Element helium = new Element(2);

    @Test
    public void getName() throws Exception {
        assertEquals("hydrogen",hydrogen.getName(),"Hydrogen");
        assertEquals("helium",helium.getName(),"Helium");
    }

    @Test
    public void getAtomic_number() throws Exception {
        assertEquals("atomic number",hydrogen.getAtomic_number(),1);
        assertEquals("atomic number",helium.getAtomic_number(),2);
    }

    @Test
    public void getMass_number() throws Exception {
        assertEquals(hydrogen.getMass_number(),1.008,0);
        assertEquals(helium.getMass_number(),4.0026022,0);
    }

    @Test
    public void getDiscovered_by() throws Exception {
        assertEquals("discovered by",hydrogen.getDiscovered_by(),"Henry Cavendish");
        assertEquals("discovered by",helium.getDiscovered_by(),"Pierre Janssen");
    }

    @Test
    public void getElectronic_configuration() throws Exception {
        assertEquals("electron configuration",hydrogen.getElectronic_configuration(),"1s1");
        assertEquals("electron configuration",helium.getElectronic_configuration(),"1s2");
    }

    @Test
    public void getDensity() throws Exception {
        assertEquals(0.08988,hydrogen.getDensity(),0);
        assertEquals(0.1786,helium.getDensity(),0);
    }

    @Test
    public void getMelting_point() throws Exception {
        assertEquals(13.99,hydrogen.getMelting_point(),0);
        assertEquals(0.95,helium.getMelting_point(),0);
    }

    @Test
    public void getBoiling_point() throws Exception {
        assertEquals(20.271,hydrogen.getBoiling_point(),0);
        assertEquals(4.222,helium.getBoiling_point(),0);
    }


}
