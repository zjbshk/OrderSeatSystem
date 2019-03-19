package top.itreatment.net.util;

import com.google.gson.*;

import java.lang.reflect.Field;
import java.util.*;

public class Util {


    public static JsonElement getItem(String data, String defaultItems) {
        JsonParser parser = new JsonParser();
        JsonElement parse = parser.parse(data);
        JsonObject asJsonObject = parse.getAsJsonObject();
        return getItem(asJsonObject, defaultItems);
    }

    public static Map<String, JsonElement> getItem(String data, String... defaultItems) {
        JsonParser parser = new JsonParser();
        JsonElement parse = parser.parse(data);
        JsonObject asJsonObject = parse.getAsJsonObject();
        return getItem(asJsonObject, defaultItems);
    }

    public static JsonElement getItem(JsonObject data, String item) {
        Set<Map.Entry<String, JsonElement>> entries = data.entrySet();
        for (Map.Entry<String, JsonElement> entry : entries) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            if (key.equals(item)) {
                return value;
            } else if (value.isJsonObject()) {
                JsonElement subItem = getItem((JsonObject) value, item);
                if (subItem != null && !(subItem instanceof JsonNull))
                    return subItem;
            }
        }
        return JsonNull.INSTANCE;
    }

    public static Map<String, JsonElement> getItem(JsonObject data, String... items) {
        Map<String, JsonElement> map = new HashMap<>();
        Set<Map.Entry<String, JsonElement>> entries = data.entrySet();
        for (Map.Entry<String, JsonElement> entry : entries) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            if (isIn(key, items)) {
                map.put(key, value);
            } else if (value.isJsonObject()) {
                Map<String, JsonElement> subItems = getItem((JsonObject) value, items);
                if (!subItems.isEmpty()) {
                    map.putAll(subItems);
                }
            }
        }
        return map;
    }

    public static boolean isIn(String key, String[] items) {
        for (String item : items) {
            if (item == null && key == null || item != null && item.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public static String toKV(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (i != 0) {
                sb.append("&");
            }
            i++;
            String key = entry.getKey();
            Object value = entry.getValue();
            sb.append(key).append("=").append(value);
        }
        return sb.toString();
    }

    public static <T> String toJson(T t) {
        Gson gson = new Gson();
        String s = gson.toJson(t);
        return s;
    }

    public static <T> T fromJson(Class<T> t, String data) {
        Gson gson = new Gson();
        T t1 = gson.fromJson(data, t);
        return t1;
    }

    public static <T> T fromJson(Class<T> t, JsonElement data) {
        Gson gson = new Gson();
        T t1 = gson.fromJson(data, t);
        return t1;
    }

    public static String getArgs(Map.Entry<String, Object>... sos) {
        StringBuilder sb = new StringBuilder();
        if (sos != null && sos.length >= 0) {
            boolean isFirst = true;
            for (Map.Entry<String, Object> so : sos) {
                getArg(so, isFirst, sb);
                isFirst = false;
            }
        }
        return sb.toString();
    }

    public static void getArg(Map.Entry<String, Object> so, boolean isFirst, StringBuilder sb) {
        String key = so.getKey();
        Object value = so.getValue();
        if (isFirst) {
            sb.append("?");
        } else {
            sb.append("&");
        }
        sb.append(key + "=" + value);
    }

    public static String getArgs(HashMap<String, Object> so) {
        StringBuilder sb = new StringBuilder();
        if (so != null && !so.isEmpty()) {
            boolean isFirst = true;
            for (Map.Entry<String, Object> entry : so.entrySet()) {
                getArg(entry, isFirst, sb);
                isFirst = false;
            }
        }
        return sb.toString();
    }

    public static String getCookieStr(Map<String, String> cooliesMap) {
        StringBuilder sbTemp = new StringBuilder();
        int i = 0;
        for (String value : cooliesMap.values()) {
            if (i == cooliesMap.size() - 1)
                sbTemp.append(value);
            else
                sbTemp.append(value).append("; ");
            i++;
        }
        return sbTemp.toString();
    }

    public static JsonObject toJsonObject(Map<String, JsonElement>... defaultItemss) {
        JsonObject jsonObject = new JsonObject();
        for (Map<String, JsonElement> defaultItems : defaultItemss) {
            defaultItems.forEach(jsonObject::add);
        }
        return jsonObject;
    }

    public static <T> List<String> getFieldNamesFromObject(Class<T> t) {
        List<String> objects = new ArrayList<>();
        Field[] declaredFields = t.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            objects.add(declaredField.getName());
        }
        return objects;
    }

    public static <T> T[] toArray(List<T> ts, T[] s) {
        if (ts == null || s == null || ts.size() != s.length) {
            throw new RuntimeException("传入参数有误，请检查");
        }
        for (int i = 0; i < s.length; i++) {
            s[i] = ts.get(i);
        }
        return s;
    }

    public static long getTime(Long dateParam, Integer hour, Integer minute, Integer second) {
        Date date = new Date(dateParam);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.HOUR, hour);
        return calendar.getTime().getTime();
    }



}
