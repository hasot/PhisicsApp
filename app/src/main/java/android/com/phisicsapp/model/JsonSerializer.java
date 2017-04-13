package android.com.phisicsapp.model;

import com.google.gson.Gson;

public class JsonSerializer {
    public static String toJson(Object o) {
        return new Gson().toJson(o);
    }

    public static Object fromJson(String json, Class aClass) {
        return new Gson().fromJson(json, aClass);
    }
}
