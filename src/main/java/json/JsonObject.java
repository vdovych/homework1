package json;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    List<JsonPair> pairs;

    public JsonObject(JsonPair... jsonPairs) {
        pairs = new LinkedList();
        for (JsonPair jsonPair :
                jsonPairs) {
            for (JsonPair pair :
                    pairs) {
                if (jsonPair.key.equals(pair.key))
                    pair = jsonPair;
            }
            if (!pairs.contains(jsonPair))
                pairs.add(jsonPair);
        }
    }

    @Override
    public String toJson() {
        String output = "{";
        for (JsonPair pair :
                pairs) {
            output += "'" + pair.key + "': " + pair.value.toJson() + ", ";
        }
        output += "}";
        output = output.replace(", }", "}");
        return output;
    }

    public void add(JsonPair jsonPair) {
        pairs.add(jsonPair);
    }

    public boolean contaings(String name) {
        return !(find(name)==null);
    }

    public Json find(String name) {
        for (JsonPair pair :
                pairs) {
            if (pair.key.equals(name)) {
                return pair.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject newObject = new JsonObject();
        for (String name :
                names) {
            if (contaings(name))
                newObject.add(new JsonPair(name, find(name)));
        }
        return newObject;
    }
}
