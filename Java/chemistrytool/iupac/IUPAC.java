package chemistrytool.iupac;

import chemistrytool.util.Constants;
import chemistrytool.util.Parser;
import chemistrytool.util.Exceptions.ElementNotFoundInCompoundException;
import static chemistrytool.util.Parser.*;

public class IUPAC {

    /**
     * IUPAC class contains a function used to name organic compounds.
     * Example: IUPAC i = new IUPAC("CH4");
     *          i.getIUPACName();   . . . . . . methane
     */

    Parser p = new Parser();
    private String compound;

    public IUPAC(String compound) {
        this.compound = compound;
    }

    /**
     * Returns IUPAC Name of organic compound by determining the group of the compound.
     * Example: IUPAC i = new IUPAC("CH4");
     *          i.getIUPACName();   . . . . . . number of hydrogen = number of carbon * 2 + 2 -> Alkane
     *          CH4 -> Methane
     * @return
     * @throws ElementNotFoundInCompoundException
     */

    public String getIUPACName()throws ElementNotFoundInCompoundException{
        String iupac_name = "";
        int number_of_hydrogen = p.getOccurence(this.compound,"H");
        int number_of_carbon = p.getOccurence(this.compound,"C");
        if(number_of_hydrogen == number_of_carbon * 2 + 2){
            iupac_name = Constants.prefix[number_of_carbon - 1] + "ane";
        }else if(number_of_hydrogen == 2 * number_of_carbon){
            iupac_name = Constants.prefix[number_of_carbon - 1] + "ene";
        }else if(number_of_hydrogen == number_of_carbon * 2 - 2){
            iupac_name = Constants.prefix[number_of_carbon - 1] + "yne";
        }
        return iupac_name;
    }
}
