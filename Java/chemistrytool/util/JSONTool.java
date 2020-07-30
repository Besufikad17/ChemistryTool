package chemistrytool.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class JSONTool {

    /**
     * JSONTool class contains functions used to convert JSONArray elements to Map object.
     * Example: JSONTool js = new JSONTool();
     *          js.decode(1); . . . . parses the first element of JSONArray to Map object.
     *          js.decode("He"); . . . parses element of JSONArray which contain "He" to Map object.
     * @param i
     * @return
     */

    public Map decode(int i){
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader("src/chemistrytool/JSON/PeriodicTableJSON.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
       JSONObject jo = (JSONObject)obj;
        JSONArray ja = (JSONArray)jo.get("elements");
        Map m = (Map)ja.get(i - 1);
        return m;
    }

    public Map decode(String symbol){
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader("src/chemistrytool/JSON/PeriodicTableJSON.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject)obj;
        JSONArray ja = (JSONArray)jo.get("elements");
        Map m = null;
        for(int i =0;i < ja.size();i++){
            Map m2 = (Map)ja.get(i);
            if(m2.get("symbol").equals(symbol)){
                m = m2;
            }
        }
        return m;
    }
}
