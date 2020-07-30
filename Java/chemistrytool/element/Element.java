package chemistrytool.element;

import chemistrytool.util.JSONTool;

public class Element {

    /**
     * Element class contains data fields that give detail information about a given element.
     * Example: Element hydrogen = new Element(symbol: "H");
     *          hydrogen.getAtomic_number(); . . . . . 1
     *          hydrogen.getBoiling_point(); . . . . . 20.271
     *          hydrogen.getDensity(); . . . . . . . . 0.08988
     *          hydrogen.getMelting_point(); . . . . . 13.99
     *          hydrogen.getElectronic_configuration();. 1s1
     *          hydrogen.getDiscovered_by(); . . . . . . Henry Cavendish
     */

    JSONTool js = new JSONTool();
    private String name;
    private long atomic_number;
    private double mass_number;
    private String discovered_by;
    private String electronic_configuration;
    private double density;
    private double melting_point;
    private double boiling_point;

    public Element(int atomic_number) {
        this.name = (String) js.decode(atomic_number).get("name");
        this.atomic_number = atomic_number;
        this.mass_number = (double) js.decode(atomic_number).get("atomic_mass");
        this.discovered_by = (String) js.decode(atomic_number).get("discovered_by");
        this.electronic_configuration = (String) js.decode(atomic_number).get("electron_configuration");
        this.density = (double) js.decode(atomic_number).get("density");
        this.melting_point = (double) js.decode(atomic_number).get("melt");
        this.boiling_point = (double) js.decode(atomic_number).get("boil");
    }

    public Element(String symbol) {
        this.name = (String) js.decode(symbol).get("name");
        this.atomic_number = (long) js.decode(symbol).get("number");
        this.mass_number = (double) js.decode(symbol).get("atomic_mass");
        this.discovered_by = (String) js.decode(symbol).get("discovered_by");
        this.electronic_configuration = (String) js.decode(symbol).get("electron_configuration");
        this.density = (double) js.decode(symbol).get("density");
        this.melting_point = (double) js.decode(symbol).get("melt");
        this.boiling_point = (double) js.decode(symbol).get("boil");
    }

    public String getName() {
        return name;
    }

    public long getAtomic_number() {
        return atomic_number;
    }

    public double getMass_number() {
        return mass_number;
    }

    public String getDiscovered_by() {
        return discovered_by;
    }

    public String getElectronic_configuration() {
        return electronic_configuration;
    }

    public double getDensity() {
        return density;
    }

    public double getMelting_point() {
        return melting_point;
    }

    public double getBoiling_point() {
        return boiling_point;
    }
}
