package json;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonArray extends Json {
    private List<Json> jsons;

    public JsonArray(){
        this.jsons = new LinkedList<>();
    }
    public JsonArray(Json... jsons) {
        this.jsons = new LinkedList<>();
        for (Json json:
             jsons) {
            this.jsons.add(json);
        }
    }

    public void add(Json json){
        jsons.add(json);
    }
    @Override
    public String toJson() {
        return "[" + getJsonArrBody() + "]";
    }

    private String getJsonArrBody() {
        String jsonStr = "";
        Iterator<Json> jsonIterator = jsons.iterator();
        while (jsonIterator.hasNext()) {
            Json json = jsonIterator.next();
            jsonStr += json.toJson();
            if (jsonIterator.hasNext())
                jsonStr += ", ";
        }
        return jsonStr;
    }
}
