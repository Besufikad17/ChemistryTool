package chemistrytool.gaslaws;

import chemistrytool.util.Parser;
import chemistrytool.util.Exceptions.InvalidInputException;

import java.util.Arrays;

public class GasLaws{

    /**
     * GasLaws class contains functions to calculate unknown quantities of a system by using formulas listed in Gas Laws.
     *  Example :  GasLaws boyle = new GasLaws(given1 : "p1 = 10atm",given2 : "p2 = 40atm",given3 : "v2 = 12litre");
     *             boyle.CalculateBoyles();  . . . . . . .  v1 = (p2 * v2) / p1  = 48litre
     *
     */

    private String given1;
    private String given2;
    private String given3;
    private String given4;
    private String given5;

    public GasLaws(String given1, String given2) {
        this.given1 = given1;
        this.given2 = given2;
    }

    public GasLaws(String given1, String given2, String given3) {
        this.given1 = given1;
        this.given2 = given2;
        this.given3 = given3;
    }

    public GasLaws(String given1, String given2, String given3, String given4, String given5) {
        this.given1 = given1;
        this.given2 = given2;
        this.given3 = given3;
        this.given4 = given4;
        this.given5 = given5;
    }

    Parser p = new Parser();

    /**
     *  Calculates unknown quantities of a system when temperature kept constant
     * @throws InvalidInputException
     */

    public String CalculateBoyles()throws InvalidInputException {
        double result = 0;
        String unit = "";
        double[] values = p.parse(new String[]{given1,given2,given3});  //let values = {10atm,20atm,0litre,12litre};
        for(int i = 0;i < 4;i++){
            if(values[i] == 0){
                if(i == 0){                                         // if p1 = 0 , p1 = (p2 * v2) / v1
                    result = (values[1] * values[3])/values[2];
                }else if(i == 1){                                   // if p2 = 0 , p2 = (p1 * v1) / v2
                    result = (values[0] * values[2])/values[3];
                }else if(i == 2){                                   // if v1 = 0 , v1 = (p2 * v2) / p1
                    result = (values[1] * values[3])/values[0];
                }else{                                              // if v2 = 0 , v2 = (p1 * v1) / p2
                    result = (values[0] * values[2])/values[1];
                }
            }
        }
        return result + unit;
    }

    /**
     *  Calculates unknown quantities of a system when pressure kept constant
     * @throws InvalidInputException
     */

    public String CalculateCharles()throws InvalidInputException {
        double result = 0;
        String unit = "";
        double[] values = p.parse(new String[]{given1,given2,given3});  //let values = {0atm,0atm,10litre,12litre,0K,320K};
        for(int i = 2;i < 6;i++){
            if(values[i] == 0){
                if(i == 2){                                         // if v1 = 0 , v1 = (v2 * T1) / T2
                    result = (values[3] * values[4])/values[5];
                }else if(i == 3){                                   // if v2 = 0 , v2 = (v1 * T2) / T1
                    result = (values[2] * values[5])/values[4];
                }else if(i == 4){                                   // if T1 = 0 , T1 = (v1 * T2) / T1
                    result = (values[2] * values[5])/values[3];
                }else{                                              // if T2 = 0 , T2 = (v2 * T1) / v1
                    result = (values[3] * values[4])/values[2];
                }
            }
        }
        return result + unit;
    }

    /**
     *  Calculates unknown quantities of a system in a fixed amount of gas
     * @throws InvalidInputException
     */

    public String CalculateCombined()throws InvalidInputException {
        double result = 0;
        String unit = "";
        double[] values = p.parse(new String[]{given1,given2,given3,given4,given5});  //let values = {10atm,20atm,10litre,12litre,0K,320K};
        for(int i = 0;i < 6;i++){
            if(values[i] == 0){
                if(i == 0) {                                        // if p1 = 0 , p1 = (p2 * v2 * T1) / (v1 * T2)
                    result = (values[1] * values[3] * values[4])/(values[2] * values[5]);
                }else if(i == 1){                                   // if p2 = 0 , p2 = (p1 * v1 * T2) / (v2 * T1)
                    result = (values[0] * values[2] * values[5])/(values[3] * values[4]);
                }else if(i == 2){                                   // if v1 = 0 , v1 = (p2 * v2 * T1) /(p1 * T2)
                    result = (values[1] * values[3] * values[4])/(values[0] * values[5]);
                }else if(i == 3){                                   // if v2 = 0 , v2 = (p1 * v1 * T2) / (p2 * T1)
                    result = (values[0] * values[2] * values[5])/(values[1] * values[4]);
                }else if(i == 4){                                   // if T1 = 0 , T1 = (p1 * v1 * T2) / (p2 * v2)
                    result = (values[0] * values[2] * values[5])/(values[1] * values[3]);
                }else{                                              // if T2 = 0 , T2 = (p2 * v2 * T1) /(p1 * T2)
                    result = (values[1] * values[3] * values[4])/(values[0] * values[2]);
                }
            }
        }
        System.out.println(Arrays.toString(values));
        return result + unit;
    }

    /**
     *  Calculates unknown quantities of a system by using the relationship between temperature , volume , pressure and amount of substance.
     * @throws InvalidInputException
     */

    public String CalculateIdeal()throws InvalidInputException {
        double result = 0;
        String unit = "";
        double[] values = p.parse(new String[]{given1,given2,given3},true);  //let values = {10atm,130litre,0K,2mol,0.082kj/mol};
        for(int i = 0;i < 4;i++){
            if(values[i] == 0){
                if(i == 0) {                                        // if p = 0 , p = (n * R * T) / v
                    result = (values[3] * values[4] * values[2])/values[1];
                }else if(i == 1){                                   // if v = 0 , v = (n * R * T) / p
                    result = (values[3] * values[4] * values[2])/values[0];
                }else if(i == 2){                                   // if T = 0 , T = (p * v)/(n * R)
                    result = (values[0] * values[1])/(values[3] * values[5]);
                }else{                                             // if n = 0 , n = ((p * v)/(T * R)
                    result = (values[0] * values[1])/(values[2] * values[5]);
                }
            }
        }
        return result + unit;
    }

    /**
     *  Calculates the diffusion rate of one substance compare to another.
     * @return
     */

    public String CalculateGrhams(){
        return given1 + " diffuses " + Math.sqrt(p.getMolarMass(p.getElements(given2),given2))/Math.sqrt(p.getMolarMass(p.getElements(given1),given1))
                + " times than " + given2;
    }
}
