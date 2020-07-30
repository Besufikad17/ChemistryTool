package chemistrytool.concentration;

import chemistrytool.util.Exceptions.ElementNotFoundInCompoundException;
import chemistrytool.util.Exceptions.MissingInputException;
import chemistrytool.util.JSONTool;
import chemistrytool.util.Parser;

public class Concentration {

    /**
     * Concentration class contains functions to calculate unknown concentration of substance.
     *  Example:  Concentration concentration = new Concentration("10kg","12millilitre","CH4");
     *            concentration.CalculateMolarity();   . . . .  Molarity = given mass / (Molar mass * volume of solution)
     */

    Parser p = new Parser();
    private String given1;
    private String given2;
    private String given3 = "";

    public Concentration(String given1) {
        this.given1 = given1;
    }

    public Concentration(String given1, String given2) {
        this.given1 = given1;
        this.given2 = given2;
    }

    public Concentration(String given1, String given2, String given3) {
        this.given1 = given1;
        this.given2 = given2;
        this.given3 = given3;
    }

    /**
     * Returns value that express unknown concentration of a substance in terms of molarity.
     * @return
     * @throws MissingInputException
     */

    public double CalculateMolarity()throws MissingInputException {
        double molarity = 0, mass = 0,mole = 0,volume = 0,molar_mass = 0;
        String[] givens = {given1,given2,given3};
        for(String given : givens) {
            if (p.isMass(given)) {
                mass = p.parse(given);
                if (given.contains("kg")){
                    mass = mass * 1000;
                }
            }else if(p.isMole(given)){
                mole = p.parse(given);
            }else if(p.isVolume(given)){
                volume = p.parse(given);
                if (given.contains("millilitre")){
                    volume = volume / 1000;
                }
            }else{
                molar_mass = p.getMolarMass(p.getElements(given),given);
            }
        }
        if(mass == 0 && molar_mass == 0){
            molarity = mole/volume;
        }else if(mole == 0){
            molarity = mass / (molar_mass * volume);
        }else{
            throw new MissingInputException("Missing input!");
        }
        return molarity;
    }

    /**
     * Returns value that express unknown concentration of a substance in terms of molality.
     * @return
     * @throws MissingInputException
     */

    public double CalculateMolality()throws MissingInputException {
        double molality = 0, mass_of_solute = 0,mole = 0,mass_of_solvent = 0,molar_mass = 0;
        String[] givens = {given1,given2,given3};
        for(String given : givens) {
            if (p.isMass(given)) {
                if (given.contains("solvent")){
                    mass_of_solvent = p.parse(given);
                    if (given.contains("gram")){
                        mass_of_solvent = mass_of_solvent/1000;
                    }
                }else {
                    mass_of_solute = p.parse(given);
                }
            }else if(p.isMole(given)){
                mole = p.parse(given);
            }else{
                molar_mass = p.getMolarMass(p.getElements(given),given);
            }
        }
        System.out.println(molar_mass);
        if(mass_of_solute == 0 && molar_mass == 0){
            molality = mole/mass_of_solvent;
        }else if(mole == 0){
            molality = mass_of_solute / (molar_mass * mass_of_solvent);
        }else{
            throw new MissingInputException("Missing input!");
        }
        return molality;
    }

    /**
     * Returns value that express unknown concentration of a substance in terms of normality.
     * @return
     * @throws MissingInputException
     */

    public double CalculateNormality()throws MissingInputException {
        double normality = 0, mass = 0,volume = 0,number_of_equivalent_mass = 0,equivalent_mass = 0;
        String[] givens = {given1,given2,given3};
        for(String given : givens) {
            if (p.isMass(given)) {
                mass = p.parse(given);
            }else if(p.isVolume(given)){
                volume = p.parse(given);
                if (given.contains("millilitre")){
                    volume = volume / 1000;
                }
            }else if (p.isNumberOfEquivalent(given)){
                number_of_equivalent_mass = p.parse(given);
            }else{
                equivalent_mass = p.CalculateEquivalentMass(given);
            }
        }
        if(mass == 0 && equivalent_mass == 0){
            normality = number_of_equivalent_mass/volume;
        }else if(number_of_equivalent_mass == 0){
            normality = mass / (equivalent_mass * volume);
        }else{
            throw new MissingInputException("Missing input!");
        }
        return normality;
    }


    /**
     * Calculates amount of element in a compound.
     * @throws ElementNotFoundInCompoundException
     */

    public String CalculatePercentageComposition()throws ElementNotFoundInCompoundException {
        String compound = given2,element = given1;
        if (compound.contains(element)) {
            int coefficient = Character.isDigit(compound.charAt(0)) ? compound.charAt(0) - 48 : 1;
            double percentage_composition = (p.getMolarMass(p.getElements(element), element) / p.getMolarMass(p.getElements(compound), compound)) * 100;
            return "There is " + percentage_composition + "% of " + element + " in " + compound;
        }else{
            throw new ElementNotFoundInCompoundException("Element not found in the given compound!");
        }
    }

    /**
     *  Returns the electronic configuration of an element.
     */

    public String getElectronicConfiguration(){
        JSONTool js = new JSONTool();
        return String.valueOf(js.decode(given1).get("electron_configuration"));
    }




}
