package com.k_beta.android.app.data;



import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by dongkai on 2015/11/3.
 */

public class MyResponseDeserializer implements JsonDeserializer<MyResponse> {
    private final static String KEY_OK = "code";
    private final static String KEY_MSG = "msg";
    private final static String KEY_DATAS = "datas";
    private final static String KEY_PAGE = "page";

    @Override
    public MyResponse deserialize(JsonElement json, Type type,
                                       JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = (JsonObject) json;
        MyResponse res = new MyResponse();
        JsonElement codeElelment= jsonObject.get(KEY_OK);
        if(codeElelment !=null && !(codeElelment instanceof JsonNull)){
            res.setCode(codeElelment.getAsInt());
        }
        JsonElement objElement = jsonObject.get(KEY_DATAS);
        if (objElement != null && !(objElement instanceof JsonNull)) {
            res.setDatas(objElement.toString());
        }
        JsonElement msgElement = jsonObject.get(KEY_MSG);
        if (msgElement != null && !(msgElement instanceof JsonNull)) {
            res.setMsg(msgElement.toString());
        }
        JsonElement pageElement = jsonObject.get(KEY_PAGE);
        if (pageElement != null && !(pageElement instanceof JsonNull)) {
            res.setMsg(pageElement.toString());
        }
        return res;
    }
}