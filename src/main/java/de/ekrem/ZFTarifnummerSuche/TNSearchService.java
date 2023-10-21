package de.ekrem.ZFTarifnummerSuche;

import org.json.JSONArray;
import org.json.JSONObject;


public class TNSearchService {

    public JSONObject tnSucheMaterialNr(int materialNumber, JSONArray json) {
        /** return json.toList().stream() FORTGESCHRITTENEN METHODE
         .map(o -> (JSONObject) o)
         .filter(o -> o.has("Material") && o.getInt("Material") == materialNumber)
         .findFirst().orElse(null);
         **/

        for (int i = 0; i < json.length(); i++) {
            JSONObject empObject = json.getJSONObject(i);
            if (empObject.has("Material") && empObject.getInt("Material") == materialNumber) {
                return empObject;
            }
        }
        return null;
    }

    public JSONObject tnSucheKurzText(String kurzText, JSONArray json) {
        for (int i = 0; i < json.length(); i++) {
            JSONObject empObject = json.getJSONObject(i);
            if (empObject.has("Materialkurztext") && empObject.getString("Materialkurztext").equals(kurzText)) {
                return empObject;
            }
        }
        return null; //wenn kein Eintrag gefunden wurde, wird null zurückgegeben.
    }
}
