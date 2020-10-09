package pl.igormaculewicz.weatheraverager.utils;

import lombok.experimental.UtilityClass;
import org.json.JSONObject;

@UtilityClass
public class JsonUtils {

    /**
     * Finding a value in json tree recursively, based on given path(keys delimited by dot).
     *
     * @param jsonString given json tree
     * @param path       given path
     * @param <T>        type of value that method will return.
     * @return
     */
    public <T> T findRecursively(String jsonString, String path) {

        Object jsonObject = new JSONObject(jsonString);

        try {
            for (String key : path.split("\\.")) {
                jsonObject = ((JSONObject) jsonObject).get(key);
            }
        } catch (Exception e) {
            return null;
        }

        return (T) jsonObject;
    }

}
