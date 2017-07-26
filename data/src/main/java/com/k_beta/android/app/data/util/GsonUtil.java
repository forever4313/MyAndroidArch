package com.k_beta.android.app.data.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by Kevin Dong on 2017/7/20.
 */
public class GsonUtil {

    private static GsonBuilder gsonBuilder = new GsonBuilder();
    private static Gson gson ;
    public static <T> void registerTypeAdapter(Class<T> clazz,Object obj){
        gsonBuilder.registerTypeAdapter(clazz, obj);
    }

    public static <T> String toGson(T instance){
        String res = getGson().toJson(instance);
        return res;
    }
    public static <T> String toGson(T[] instanceArray){
        String res = getGson().toJson(instanceArray);
        return res;
    }

    public static <T> T fromGson(String gsonString,Class<T> clazz){

        try {
            T ins = getGson().fromJson(gsonString, clazz);
            return ins;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static <T> T fromGson(JsonElement jsonElement, Class<T> clazz){
        try {

            T ins = getGson().fromJson(jsonElement, clazz);
            return ins;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Gson getGson(){
        if(gson == null){
            gson = gsonBuilder.create();
        }
        return gson;

    }

    public static <T> T fromGson(String gsonString){
        try {
            Type type = new TypeToken<T>() {}.getType();

            T ins = getGson().fromJson(gsonString, type);
            return ins;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
