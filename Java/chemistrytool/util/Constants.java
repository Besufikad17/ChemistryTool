package chemistrytool.util;

import java.util.HashMap;

public class Constants {

    /**
     * Constants class contains constant variables which used to store constant values used in calculation.
     */
    public static final String[] acids = {"HNO3","HCN","H2SO4","H2SO3","HNO2","HCl","HF","HI","HBr"};
    public static final String[] bases = {"NaOH","KOH","Mg(OH)2","Ca(OH)2"};
    public static final String[] prefix = {"Meth","Eth","Prop","But","Pent","Hex","Hep","Oct","Non","Dec"};

    public static HashMap<String,Double> getAtomic_mass(){
        HashMap<String,Double> result = new HashMap<>();
        String[] keys = {"H","C","N","B","F","O","P","K","I","S","Na","Mg","Ca","Cl","Br","Li","Fe","Cu","Mn","Zn"};
        double[] values = {1,12,14,11,18,16,31,39,129,32,23,24,40,35.5,80,7,56,63.5,55,65};
        for(int i =0;i < keys.length;i++){
            result.put(keys[i],values[i]);
        }
        return result;
    }


}
