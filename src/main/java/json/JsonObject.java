package json;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    private ArrayList<JsonPair> pairs;

    public JsonObject(JsonPair... jsonPairs) {
        pairs = new ArrayList<>();
        for (JsonPair pair : jsonPairs) {
            add(pair);
        }
    }

    @Override
    public String toJson() {
        StringBuilder str = new StringBuilder("{");
        for (int ind = 0; ind < pairs.size(); ind++) {
            if (ind > 0) {
                str.append(", ");
            }
            str.append(pairs.get(ind).toString());
        }
        str.append("}");
        System.out.println(str.toString());
        return str.toString();
    }

    private void overwrite(JsonPair pair) {
        for (int i = 0; i < pairs.size(); i++) {
            if (pairs.get(i).key.equals(pair.key)) {
                pairs.set(i, pair);
            }

        }
    }

    public void add(JsonPair jsonPair) {
        Json f = find(jsonPair.key);
        if (f == null) {
            pairs.add(jsonPair);
        } else {
            overwrite(jsonPair);
        }

    }

    public Json find(String name) {
        for (JsonPair pair : pairs) {
            if (pair.key.equals(name)) {
                return pair.value;
            }
        }
        return null;
    }

    public boolean contains(String name) {
        for (JsonPair jsonPair : pairs) {
            if (jsonPair.key.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public JsonObject projection(String... names) {
        JsonObject result = new JsonObject();
        for (String name : names) {
            Json f = find(name);
            if (f != null) {
                result.add(new JsonPair(name, f));
            }
        }
        return result;
    }
}
