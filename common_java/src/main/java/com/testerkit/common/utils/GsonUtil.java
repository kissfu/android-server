package com.testerkit.common.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GsonUtil {

    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    /**
     * 将object对象转成json字符串
     *
     * @param object
     * @return
     */
    public static String gsonString(Object object) {
        String gsonString = "";
        if (gson != null && object != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 将object对象转成json字符串
     *
     * @param object
     * @return
     */
    public static String gsonString(Object object, String dataFormat) {
        if (StringUtil.isEmpty(dataFormat)) {
            // 北京东八区 ，防止在数据库呀的地方重复增加
            dataFormat ="yyyy-MM-dd'T'HH:mm:ss.SSS+0800";//"yyyy-MM-dd HH:mm:ss.SSS'Z'";
        }
        Gson gsonTemp = new GsonBuilder()
                .setDateFormat(dataFormat)
                .create();
        String gsonString = "";
        gsonString = gsonTemp.toJson(object);
        return gsonString;
    }

    /**
     * 将gsonString转成泛型bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T toBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    public static <T> T toBean(Gson g, String gsonString, Class<T> cls) {
        T t = null;
        if (g != null) {
            t = g.fromJson(gsonString, cls);
        }
        return t;
    }

    public static <T> T toBean(String gsonString, Type type) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, type);
        }
        return t;
    }

    /**
     * 转成list
     * 泛型在编译期类型被擦除导致报错
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> toList(String gsonString, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成list
     * 解决泛型在编译期类型被擦除导致报错
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> toListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }


    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> toMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }


    public static String gsonString(Object object, final List<String> fieldsExclusion) {
        String gsonString = "";
        Gson g = new GsonBuilder()
                .addSerializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        if (fieldsExclusion.contains(f.getName())) {
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();
        if (g != null && object != null) {
            gsonString = g.toJson(object);
        }
        return gsonString;
    }

}
