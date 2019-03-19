package top.itreatment.net.util;

import top.itreatment.net.res.Resource;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ResUtil {

    public static Map<String, Object> getMapWithLogin() {
        Class<Resource.LoginRes> loginResClass = Resource.LoginRes.class;
        HashMap<String, Object> map = putAll(loginResClass);
        return map;
    }

    public static HashMap<String, Object> getMapWithCommonHeaders() {
        Class<Resource.CommonHeaders> commonHeadersClass = Resource.CommonHeaders.class;
        HashMap<String, Object> map = putAll(commonHeadersClass);
        return map;
    }

    private static <T> HashMap<String, Object> putAll(Class<T> loginResClass) {
        HashMap<String, Object> map = new HashMap<>();
        Field[] declaredFields = loginResClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            try {
                Object o = declaredField.get(loginResClass);
                String name = declaredField.getName();
                map.put(name, o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
