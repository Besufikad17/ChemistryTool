package chemistrytool.util;

import chemistrytool.util.Exceptions.InvalidInputException;
import chemistrytool.util.Exceptions.ElementNotFoundInCompoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    /**
     * Parser class contains function used to extract quantities from user inputs.
     * Example: parse("10kg");  . . . . . . mass = 10kg
     *
     * @param given
     * @return
     */


    /*Checks whether a given quantity is volume or not.*/

    public boolean isVolume(String given){
        return given.contains("litre") || given.contains("millilitre");
    }

    /*Checks whether a given quantity is tempreture or not. */

    public boolean isTemprature(String given){
        return given.contains("C") || given.contains("F") || given.contains("K");
    }

    /*Checks whether a given quantity is pressure or not.*/

    public boolean isPressure(String given){
        return given.contains("atm") || given.contains("pas") || given.contains("torr");
    }

    /*Checks whether a given quantity is mass or not.*/

    public boolean isMass(String given){
        return given.contains("gram") || given.contains("kg");
    }

    /*Checks whether a given quantity is amount of substance or not.*/

    public boolean isMole(String given){
        return given.contains("mol");
    }

    /*Checks whether a given compound is acid or not.*/

    public boolean isAcid(String compound){
        return Arrays.toString(Constants.acids).contains(compound);
    }

    /*Checks whether a given compound is base or not.*/

    public boolean isBase(String compound){
        return Arrays.toString(Constants.bases).contains(compound);
    }

    /*Checks whether a given quantity is number of equivalent or not.*/

    public boolean isNumberOfEquivalent(String given){
        return given.contains("kg/eq") || given.contains("g/eq");
    }

    /* Returns the unit of a given quantity.*/

    public String getUnit(String given){
        String result = "";
        int index = 0;
        if(isVolume(given)){
            index = given.contains("litre") ? given.indexOf("litre") : given.indexOf("millilitre");
        }else if(isMole(given)){
            index = given.indexOf("mol");
        }else if(isMass(given)){
            index = given.contains("kg") ? given.indexOf("kg") : given.indexOf("g");
        }else if(isTemprature(given)){
            index = given.contains("K") ? given.indexOf("K") : given.indexOf("C");
        }else if(isPressure(given)){
            index = given.contains("pas") ? given.indexOf("pas") : given.indexOf("atm");
        }
        return given.substring(index);
    }

    /* Parses user inputs to known physical quantities for calculating unknown quantities by using gas laws(Boyle's , Charle's and Combined).
    *  Example: parse(new String[]{"p1 = 12atm","p2 = 24atm","v1 = 40millilitre"}) . . . . {12.0,24.0,40.0,0.0,0.0,0.0}
    * */

    public double[] parse(String[] givens)throws InvalidInputException {
        double[] result = new double[6];
        for(String given : givens){
            String unit = getUnit(given);
            double value = Double.parseDouble(given.substring(given.indexOf("=") + 1,given.indexOf(unit)));
            if(given.contains("p1")){
                result[0] = value;
            }else if(given.contains("p2")){
                result[1] = value;
            }else if(given.contains("v1")){
                result[2] = value;
            }else if(given.contains("v2")){
                result[3] = value;
            }else if(given.contains("T1")){
                result[4] = value;
            }else if(given.contains("T2")){
                result[5] = value;
            }else{
                throw new InvalidInputException("Invalid Input");
            }
        }
        return result;
    }

    /*Parses user inputs to known physical quantities for calculating unknown quantities by using ideal gas law.
      Example: parse(new String[]{"p = 10atm","v = 12millilitre","n = 2mole"],True) . . . . . . {10.0,12.0,0.0,2.0,0.082}*/

    public double[] parse(String[] givens,boolean isIdeal)throws InvalidInputException {
        double[] result = new double[5];
        double R = 0;
        if (isIdeal){
            for(String given : givens){
                String unit = getUnit(given);
                double value = Double.parseDouble(given.substring(given.indexOf("=") + 1,given.indexOf(unit)));
                if(given.contains("p")){
                    result[0] = value;
                    if(unit.equals("atm")){
                        R = 0.082;
                    }else{
                        R = 8.314;
                    }
                }else if(given.contains("v")){
                    result[1] = value;
                }else if(given.contains("T")){
                    result[2] = value;
                }else if(given.contains("n")){
                    result[3] = value;
                }else{
                    throw new InvalidInputException("Invalid Input");
                }
            }
            result[4] = R;
        }
        return result;
    }

    /* Returns list of elements in a compound.
       Example: get_elements("NaCl") . . . . . . ["Na","Cl"]*/

    public ArrayList<String> getElements(String compound){
        ArrayList<String> elements = new ArrayList<>();
        for (int i =0;i < compound.length();i++){
            if (Character.isUpperCase(compound.charAt(i))){
                if (i != compound.length() - 1){
                    if (Character.isUpperCase(compound.charAt(i + 1))){
                        if (i + 1 != compound.length() - 1){
                            if (Character.isLowerCase(compound.charAt(i + 2))){
                                elements.add(new String(new char[]{compound.charAt(i)}));
                                elements.add(new String(new char[]{compound.charAt(i + 1),compound.charAt(i + 2)}));
                            }else{
                                elements.add(new String(new char[]{compound.charAt(i)}));
                                elements.add(new String(new char[]{compound.charAt(i + 1)}));
                            }
                        }else{
                            elements.add(new String(new char[]{compound.charAt(i)}));
                            elements.add(new String(new char[]{compound.charAt(i + 1)}));
                        }
                    }else if(i == 0){
                        if (Character.isLowerCase(compound.charAt(i + 1))){
                            elements.add(new String(new char[]{compound.charAt(i),compound.charAt(i + 1)}));
                        }else{
                            elements.add(new String(new char[]{compound.charAt(i)}));
                        }
                    }else if(Character.isLowerCase(compound.charAt(i + 1))){
                        elements.add(new String(new char[]{compound.charAt(i),compound.charAt(i + 1)}));
                    }
                }else{
                    elements.add(new String(new char[]{compound.charAt(i)}));
                }
            }
        }
        elements = removeDuplicateElements(elements);
        return elements;
    }

    /*Removes duplicate element from Arraylist.*/

    public ArrayList<String> removeDuplicateElements(ArrayList<String> elements){
        for (int i =0;i < elements.size();i++){
            String temp = elements.get(i);
            if (elements.indexOf(temp) != elements.lastIndexOf(temp)){
                elements.remove(temp);
            }
        }
        return elements;
    }

    /*Calculates molar mass of a given compound. */

    public double getMolarMass(ArrayList<String> elements,String compound){
        JSONTool js = new JSONTool();
        double molar_mass = 0;
        if(compound.length() == 1){
            molar_mass = Constants.getAtomic_mass().get(compound);
        }else {
            for (int i = 0; i < elements.size(); i++) {
                int index = compound.indexOf(elements.get(i));
                double atomic_mass = Constants.getAtomic_mass().get(elements.get(i));
                boolean letter = Character.isLetter(compound.charAt(index + 1));
                double v = atomic_mass * (compound.charAt(index + 1) - 48);
                if (index == 0) {
                    if (letter) {
                        molar_mass = molar_mass + atomic_mass;
                    } else {
                        molar_mass = molar_mass + v;
                    }
                } else {
                    if (index == compound.length() - 1) {
                        if (Character.isLetter(compound.charAt(0))) {
                            molar_mass = molar_mass + atomic_mass;
                        } else {
                            molar_mass = molar_mass + ((compound.charAt(0) - 48) * atomic_mass);
                        }
                    } else {
                        if (letter) {
                            if (Character.isLetter(compound.charAt(0))) {
                                molar_mass = molar_mass + atomic_mass;
                            } else {
                                molar_mass = molar_mass + ((compound.charAt(0) - 48) * atomic_mass);
                            }
                        } else {
                            if (Character.isLetter(compound.charAt(0))) {
                                molar_mass = molar_mass + v;
                            } else {
                                molar_mass = molar_mass + ((compound.charAt(0) - 48) * atomic_mass * (compound.charAt(index + 1) - 48));
                            }
                        }
                    }
                }
            }
        }
        return molar_mass;
    }

    /*Parses the numerical value of user input.*/

    public double parse(String input){
        int index = 0;
        for (int i =0;i < input.length();i++){
            if (Character.isLetter(input.charAt(i))){
                index = i;
                break;
            }
        }
        return Double.parseDouble(input.substring(0,index));
    }

    /*Returns amount of hydroxide ion a given basic compound.*/

    public int getOccurence(String compound,String element)throws ElementNotFoundInCompoundException{
        int occurrence = 0;
        if (compound.contains(element)){
            for(int i =0;i < compound.length();i++){
                if (element.equals(new String(new char[]{compound.charAt(i)}))){
                    if(i == compound.length()- 1){
                        occurrence++;
                    }else{
                        if(Character.isDigit(compound.charAt(i + 1))){
                            occurrence = occurrence + (compound.charAt(i + 1)) - 48;
                        }else{
                            occurrence++;
                        }
                    }
                }
            }
        }else{
            throw new ElementNotFoundInCompoundException("Element no found in the compound.");
        }
        return occurrence;
    }

    /* Calculates molar mass of basic compound with more than 1 hydroxide ion.*/

    public double calculate_molar_mass(int k, ArrayList<String> elements){
        double molar_mass = 0;
        for(int i = 0;i < elements.size();i++){
            if (elements.get(i).equals("H") || elements.get(i).equals("O")){
                molar_mass = molar_mass + k * (Constants.getAtomic_mass().get(elements.get(i)));
            }else{
                molar_mass = molar_mass + Constants.getAtomic_mass().get(elements.get(i));
            }
        }
        return molar_mass;
    }

    /*Calculates equivalent mass of a given compound.*/

    public double CalculateEquivalentMass(String compound){
        double equivalent_mass = 0;
        if (isAcid(compound)){
            double molar_mass = getMolarMass(getElements(compound),compound);
            int index = compound.indexOf("H");
            int number_0f_hydrogen = Character.isDigit(compound.charAt(index + 1)) ? compound.charAt(index + 1) - 48 : 1;
            equivalent_mass = molar_mass/number_0f_hydrogen;
        }else if (isBase(compound)){
            if (compound.contains(")")){
                int k = compound.charAt(compound.length() - 1) - 48;
                double molar_mass = calculate_molar_mass(k,getElements(compound));
                equivalent_mass = molar_mass/k;
            }else{
                equivalent_mass = getMolarMass(getElements(compound),compound);
            }
        }
        return equivalent_mass;
    }


}
