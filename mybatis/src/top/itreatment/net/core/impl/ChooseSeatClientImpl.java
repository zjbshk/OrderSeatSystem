package top.itreatment.net.core.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import top.itreatment.net.bean.*;
import top.itreatment.net.core.ChooseSeatClient;
import top.itreatment.net.core.Net;
import top.itreatment.net.res.Resource;
import top.itreatment.net.util.ResUtil;
import top.itreatment.net.util.Util;

import java.util.*;

public class ChooseSeatClientImpl implements ChooseSeatClient {

    private UserBean userBean;
    private Map<String, String> cookie;


    @Override
    public CommonBean<UserBean> login(String username, String password) {

        CommonBean<UserBean> userBeanCommonBean = new CommonBean<>();

        //拼接获取URLPath
        String urlPath = Resource.URLRes.BASE_URL + Resource.URLRes.LOGIN_URL;

        Map<String, Object> map = ResUtil.getMapWithLogin();
        map.put("login_name", username);
        map.put("password", password);
        String outData = Util.toJson(map);

        //设置请求数据，并获取网络数据
        RequestBean requestBean = new RequestBean(urlPath, outData, null, RequestBean.Method.POST);
        CommonBean<NetResponse> net = Net.net(requestBean);
        Integer status = net.getStatus();
        if (Resource.SUCCESS.equals(status)) {
            String data = net.getData().getData();
            userBean = Util.fromJson(UserBean.class, data);
            userBeanCommonBean.setData(userBean);
            setCookie(net.getData().getCookie());
        } else {
            userBeanCommonBean.setMsg("账号或密码错误，请检查");
        }
        userBeanCommonBean.setStatus(status);
        return userBeanCommonBean;
    }

    @Override
    public CommonBean<Object> logout() {
        CommonBean<Object> commonBean = new CommonBean<>();
        if (userBean == null || getCookie() == null) {
            commonBean.setStatus(Resource.FAIL);
            commonBean.setMsg("请先登录");
        } else {
            //拼接获取URLPath
            AbstractMap.SimpleImmutableEntry simpleEntry = new AbstractMap.SimpleImmutableEntry(Resource.LAB_JSON_KEY, Resource.LAB_JSON_VALUE);
            String urlPath = Resource.URLRes.BASE_URL + Resource.URLRes.LOGOUT_URL + Util.getArgs(simpleEntry);
            //获取登陆时保存的COOKIE数据
            HashMap<String, Object> headers = getHeaderCoolie();

            //设置请求数据，并获取网络数据
            RequestBean requestBean = new RequestBean(urlPath, headers);
            CommonBean<NetResponse> net = Net.net(requestBean);

            if (Resource.SUCCESS.equals(net.getStatus())) {
                setCookie(null);
                commonBean.setMsg("注销成功");
            } else {
                commonBean.setMsg("注销失败");
            }
            commonBean.setStatus(net.getStatus());
        }
        return commonBean;
    }


    @Override
    public CommonBean<List<CategoryBean>> getAreaList() {
        CommonBean<List<CategoryBean>> commonBean = new CommonBean<>();
        if (userBean == null || getCookie() == null) {
            commonBean.setStatus(Resource.FAIL);
            commonBean.setMsg("请先登录");
        } else {
            //拼接获取URLPath
            AbstractMap.SimpleImmutableEntry simpleEntry = new AbstractMap.SimpleImmutableEntry(Resource.LAB_JSON_KEY, Resource.LAB_JSON_VALUE);
            String urlPath = Resource.URLRes.BASE_URL + Resource.URLRes.CATEGORYLIST + Util.getArgs(simpleEntry);

            //获取登陆时保存的COOKIE数据
            HashMap<String, Object> headers = getHeaderCoolie();

            //设置请求数据，并获取网络数据
            RequestBean requestBean = new RequestBean(urlPath, headers);
            CommonBean<NetResponse> net = Net.net(requestBean);
            if (Resource.SUCCESS.equals(net.getStatus())) {
                JsonElement defaultItems = Util.getItem(net.getData().getData(), "defaultItems");
                if (!defaultItems.isJsonNull()) {
                    JsonArray asJsonArray = defaultItems.getAsJsonArray();
                    for (JsonElement jsonElement : asJsonArray) {
                        JsonObject asJsonObject = jsonElement.getAsJsonObject();
                        JsonElement url = Util.getItem(asJsonObject, "url");
                        String urlAsString = url.getAsString();
                        asJsonObject.addProperty("url", urlAsString);
                        asJsonObject.addProperty("id", urlAsString == null ? null : urlAsString.substring(urlAsString.lastIndexOf("=") + 1));
                    }
                    Gson gson = new Gson();
                    List<CategoryBean> categoryBeans = gson.fromJson(defaultItems, new TypeToken<List<CategoryBean>>() {
                    }.getType());
                    commonBean.setData(categoryBeans);
                    commonBean.setStatus(Resource.SUCCESS);
                    refreshCookie(net.getData().getCookie());
                }
            }
            if (!Resource.SUCCESS.equals(commonBean.getStatus())) {
                commonBean.setStatus(Resource.ERROR);
                commonBean.setMsg(net.getMsg() == null ? "获取区域列表错误" : net.getMsg());
            }
        }
        return commonBean;
    }


    @Override
    public CommonBean<List<MyBookingBean>> myBookingList() {
        CommonBean<List<MyBookingBean>> commonBean = new CommonBean<>();
        if (userBean == null || getCookie() == null) {
            commonBean.setStatus(Resource.FAIL);
            commonBean.setMsg("请先登录");
        } else {
            //拼接获取URLPath
            AbstractMap.SimpleImmutableEntry simpleEntry = new AbstractMap.SimpleImmutableEntry(Resource.LAB_JSON_KEY, Resource.LAB_JSON_VALUE);
            AbstractMap.SimpleImmutableEntry simpleEntry1 = new AbstractMap.SimpleImmutableEntry("fromType", "web");
            String urlPath = Resource.URLRes.BASE_URL + Resource.URLRes.MYBOOKINGLIST + Util.getArgs(simpleEntry, simpleEntry1);

            //获取登陆时保存的COOKIE数据
            HashMap<String, Object> headers = getHeaderCoolie();

            //设置请求数据，并获取网络数据
            RequestBean requestBean = new RequestBean(urlPath, headers);
            CommonBean<NetResponse> net = Net.net(requestBean);
            if (Resource.SUCCESS.equals(net.getStatus())) {
                JsonElement defaultItems = Util.getItem(net.getData().getData(), "defaultItems");
                if (!defaultItems.isJsonNull()) {
                    JsonArray asJsonArray = defaultItems.getAsJsonArray();
                    List<MyBookingBean> myBookingBeans = new ArrayList<>();
                    List<String> fieldNames = Util.getFieldNamesFromObject(MyBookingBean.class);
                    for (JsonElement jsonElement : asJsonArray) {
                        Map<String, JsonElement> mapTemp = Util.getItem((JsonObject) jsonElement, Util.toArray(fieldNames, new String[fieldNames.size()]));
                        JsonObject jsonObject = Util.toJsonObject(mapTemp);
                        MyBookingBean myBookingBean = Util.fromJson(MyBookingBean.class, jsonObject);
                        myBookingBeans.add(myBookingBean);
                    }
                    commonBean.setData(myBookingBeans);
                    commonBean.setStatus(Resource.SUCCESS);
                    refreshCookie(net.getData().getCookie());
                }
            }
            if (!Resource.SUCCESS.equals(commonBean.getStatus())) {
                commonBean.setStatus(Resource.ERROR);
                commonBean.setMsg(net.getMsg() == null ? "获取预定列表错误" : net.getMsg());
            }
        }
        return commonBean;
    }

    @Override
    public CommonBean<CategoryInfoBean> getCategoryInfo(String contentId) {
        CommonBean<CategoryInfoBean> commonBean = new CommonBean<>();
        if (userBean == null || getCookie() == null) {
            commonBean.setStatus(Resource.FAIL);
            commonBean.setMsg("请先登录");
        } else {
            //拼接获取URLPath
            AbstractMap.SimpleImmutableEntry simpleEntry = new AbstractMap.SimpleImmutableEntry(Resource.LAB_JSON_KEY, Resource.LAB_JSON_VALUE);
            AbstractMap.SimpleImmutableEntry simpleEntry1 = new AbstractMap.SimpleImmutableEntry("space_category%5Bcategory_id%5D", Resource.SPACE_CATEGORY_BCATEGORY_ID);
            AbstractMap.SimpleImmutableEntry simpleEntry2 = new AbstractMap.SimpleImmutableEntry("space_category%5Bcontent_id%5D", contentId);
            String urlPath = Resource.URLRes.BASE_URL + Resource.URLRes.SEARCHSEATS + Util.getArgs(simpleEntry, simpleEntry1, simpleEntry2);


            //获取登陆时保存的COOKIE数据
            HashMap<String, Object> headers = getHeaderCoolie();

            //设置请求数据，并获取网络数据
            RequestBean requestBean = new RequestBean(urlPath, headers);
            CommonBean<NetResponse> net = Net.net(requestBean);
            if (Resource.SUCCESS.equals(net.getStatus())) {
                List<String> fieldNames = Util.getFieldNamesFromObject(CategoryInfoBean.class);
                Map<String, JsonElement> defaultItems = Util.getItem(net.getData().getData(), Util.toArray(fieldNames, new String[fieldNames.size()]));
                if (!defaultItems.isEmpty()) {
                    JsonObject jsonObject = Util.toJsonObject(defaultItems);
                    CategoryInfoBean categoryInfoBean = Util.fromJson(CategoryInfoBean.class, jsonObject);
                    commonBean.setData(categoryInfoBean);
                    commonBean.setStatus(Resource.SUCCESS);
                    refreshCookie(net.getData().getCookie());
                }
            }

            if (!Resource.SUCCESS.equals(commonBean.getStatus())) {
                commonBean.setStatus(Resource.ERROR);
                commonBean.setMsg(net.getMsg() == null ? "获取区域信息错误" : net.getMsg());
            }
        }
        return commonBean;
    }

    @Override
    public CommonBean<CategorySeatsInfoBean> searchSeats(Integer beginTime, Integer duration, Integer num, String contentId) {
        CommonBean<CategorySeatsInfoBean> commonBean = new CommonBean<>();
        if (userBean == null || getCookie() == null) {
            commonBean.setStatus(Resource.FAIL);
            commonBean.setMsg("请先登录");
        } else {
            //拼接获取URLPath
            AbstractMap.SimpleImmutableEntry simpleEntry = new AbstractMap.SimpleImmutableEntry(Resource.LAB_JSON_KEY, Resource.LAB_JSON_VALUE);
            String urlPath = Resource.URLRes.BASE_URL + Resource.URLRes.SEARCHSEATS + Util.getArgs(simpleEntry);
            //获取登陆时保存的COOKIE数据
            HashMap<String, Object> headers = getHeaderCoolie();

            Map<String, Object> map = new HashMap<>();
            map.put("beginTime", beginTime);
            map.put("duration", duration);
            map.put("num", num);
            map.put("space_category%5Bcategory_id%5D", Resource.SPACE_CATEGORY_BCATEGORY_ID);
            map.put("space_category%5Bcontent_id%5D", contentId);
            String outData = Util.toKV(map);

            //设置请求数据，并获取网络数据
            RequestBean requestBean = new RequestBean(urlPath, outData, headers, RequestBean.Method.POST);
            CommonBean<NetResponse> net = Net.net(requestBean);

            if (Resource.SUCCESS.equals(net.getStatus())) {
                List<String> fieldNames = Util.getFieldNamesFromObject(CategorySeatsInfoBean.class);
                Map<String, JsonElement> defaultItems = Util.getItem(net.getData().getData(), Util.toArray(fieldNames, new String[fieldNames.size()]));

                if (!defaultItems.isEmpty()) {
                    JsonObject jsonObject = Util.toJsonObject(defaultItems);
                    CategorySeatsInfoBean categorySeatsInfoBean = Util.fromJson(CategorySeatsInfoBean.class, jsonObject);
                    commonBean.setData(categorySeatsInfoBean);
                    commonBean.setStatus(Resource.SUCCESS);
                    refreshCookie(net.getData().getCookie());
                } else {
                    JsonElement message = Util.getItem(net.getData().getData(), "MESSAGE");
                    net.setMsg(message.isJsonNull() ? null : message.getAsString());
                }
            }

            if (!Resource.SUCCESS.equals(commonBean.getStatus())) {
                commonBean.setStatus(Resource.ERROR);
                commonBean.setMsg(net.getMsg() == null ? "获取座位信息错误" : net.getMsg());
            }
        }
        return commonBean;
    }

    @Override
    public CommonBean<String> lockSeats(Integer beginTime, Integer duration, String... seats) {
        CommonBean<String> commonBean = new CommonBean<>();
        if (userBean == null || getCookie() == null) {
            commonBean.setStatus(Resource.FAIL);
            commonBean.setMsg("请先登录");
        } else {

            //拼接获取URLPath
            AbstractMap.SimpleImmutableEntry simpleEntry = new AbstractMap.SimpleImmutableEntry(Resource.LAB_JSON_KEY, Resource.LAB_JSON_VALUE);
            String urlPath = Resource.URLRes.BASE_URL + Resource.URLRes.BOOKSEATS + Util.getArgs(simpleEntry);
            //获取登陆时保存的COOKIE数据
            HashMap<String, Object> headers = getHeaderCoolie();

            for (String seat : seats) {
                Map<String, Object> map = new HashMap<>();
                map.put("beginTime", beginTime);
                map.put("duration", duration);
                map.put("seats%5B0%5D", seat);
                map.put("seatBookers%5B0%5D", userBean.getObjectId());
                String outData = Util.toKV(map);

                //设置请求数据，并获取网络数据
                RequestBean requestBean = new RequestBean(urlPath, outData, headers, RequestBean.Method.POST);
                CommonBean<NetResponse> net = Net.net(requestBean);


                if (Resource.SUCCESS.equals(net.getStatus())) {
                    JsonElement defaultItems = Util.getItem(net.getData().getData(), "result");
                    if (defaultItems != null && !defaultItems.isJsonNull()) {
                        String asString = defaultItems.getAsString();
                        if (asString.equals("success")) {
                            commonBean.setStatus(Resource.SUCCESS);
                            commonBean.setMsg("选座成功");
                            commonBean.setData(seat);
                            refreshCookie(net.getData().getCookie());
                            break;
                        } else {
                            JsonElement message = Util.getItem(net.getData().getData(), "msg");
                            net.setMsg(message.isJsonNull() ? null : message.getAsString());
                        }
                    }
                }

                if (!Resource.SUCCESS.equals(commonBean.getStatus())) {
                    commonBean.setStatus(Resource.ERROR);
                    commonBean.setMsg(net.getMsg() == null ? "选座失败" : net.getMsg());
                }
            }
        }
        return commonBean;
    }

    @Override
    public CommonBean cancelbooking(Integer bookingId) {
        CommonBean<SeatBean> commonBean = new CommonBean<>();
        if (userBean == null || getCookie() == null) {
            commonBean.setStatus(Resource.FAIL);
            commonBean.setMsg("请先登录");
        } else {
            //拼接获取URLPath
            AbstractMap.SimpleImmutableEntry simpleEntry = new AbstractMap.SimpleImmutableEntry(Resource.LAB_JSON_KEY, Resource.LAB_JSON_VALUE);
            AbstractMap.SimpleImmutableEntry simpleEntry1 = new AbstractMap.SimpleImmutableEntry("bookingId", bookingId);
            String urlPath = Resource.URLRes.BASE_URL + Resource.URLRes.CANCELBOOKING + Util.getArgs(simpleEntry, simpleEntry1);

            //获取登陆时保存的COOKIE数据
            HashMap<String, Object> headers = getHeaderCoolie();

            //设置请求数据，并获取网络数据
            RequestBean requestBean = new RequestBean(urlPath, headers);
            CommonBean<NetResponse> net = Net.net(requestBean);

            if (Resource.SUCCESS.equals(net.getStatus())) {
                JsonElement result = Util.getItem(net.getData().getData(), "result");
                if (result != null && !result.isJsonNull()) {
                    String asString = result.getAsString();
                    if (asString.equals("success")) {
                        commonBean.setStatus(Resource.SUCCESS);
                        commonBean.setMsg("预约取消成功");
                        refreshCookie(net.getData().getCookie());
                    } else {
                        JsonElement message = Util.getItem(net.getData().getData(), "msg");
                        net.setMsg(message.isJsonNull() ? null : message.getAsString());
                    }
                }
            }

            if (!Resource.SUCCESS.equals(commonBean.getStatus())) {
                commonBean.setStatus(Resource.ERROR);
                commonBean.setMsg(net.getMsg() == null ? "预约取消失败" : net.getMsg());
            }
        }
        return commonBean;
    }

    @Override
    public CommonBean unlockallseats() {
        CommonBean commonBean = new CommonBean<>();
        if (userBean == null || getCookie() == null) {
            commonBean.setStatus(Resource.FAIL);
            commonBean.setMsg("请先登录");
        } else {
            //拼接获取URLPath
            AbstractMap.SimpleImmutableEntry simpleEntry = new AbstractMap.SimpleImmutableEntry(Resource.LAB_JSON_KEY, Resource.LAB_JSON_VALUE);
            String urlPath = Resource.URLRes.BASE_URL + Resource.URLRes.UNLOCKALLSEATS + Util.getArgs(simpleEntry);

            //获取登陆时保存的COOKIE数据
            HashMap<String, Object> headers = getHeaderCoolie();

            //设置请求数据，并获取网络数据
            RequestBean requestBean = new RequestBean(urlPath, headers);
            CommonBean<NetResponse> net = Net.net(requestBean);

            if (Resource.SUCCESS.equals(net.getStatus())) {
                JsonElement result = Util.getItem(net.getData().getData(), "result");
                if (result != null && !result.isJsonNull()) {
                    String asString = result.getAsString();
                    if (asString.equals("success")) {
                        commonBean.setStatus(Resource.SUCCESS);
                        commonBean.setMsg("解除锁定成功");
                        refreshCookie(net.getData().getCookie());
                    } else {
                        JsonElement message = Util.getItem(net.getData().getData(), "msg");
                        net.setMsg(message.isJsonNull() ? null : message.getAsString());
                    }
                }
            }

            if (!Resource.SUCCESS.equals(commonBean.getStatus())) {
                commonBean.setStatus(Resource.ERROR);
                commonBean.setMsg(net.getMsg() == null ? "解除锁定失败" : net.getMsg());
            }
        }
        return commonBean;
    }

    @Override
    public CommonBean<List<SeatBean>> getBlankPOIs(String contentld) {
        CommonBean<List<SeatBean>> poIsInfo = getSeatsInfo(contentld, 0);
        return poIsInfo;
    }

    @Override
    public CommonBean<List<SeatBean>> getRecommendSeat(String contentld) {
        CommonBean<List<SeatBean>> seat = getSeatsInfo(contentld, 1);
        return seat;
    }

    private CommonBean<List<SeatBean>> getSeatsInfo(String contentld, int tag) {
        CommonBean<List<SeatBean>> commonBean = new CommonBean<>();
        CommonBean<CategoryInfoBean> categoryInfo = getCategoryInfo(contentld);
        if (Resource.SUCCESS.equals(categoryInfo.getStatus())) {
            long date = categoryInfo.getData().getDate();
            int beginTime = categoryInfo.getData().getBeginTime();
            long time = Util.getTime(date * 1000, beginTime, 0, 0);
            CommonBean<CategorySeatsInfoBean> categorySeatsInfoBeanCommonBean = searchSeats((int) (time / 1000), 3 * 60 * 60, 1, contentld);
            if (Resource.SUCCESS.equals(categorySeatsInfoBeanCommonBean.getStatus())) {
                commonBean.setStatus(Resource.SUCCESS);
                if (tag == 0) {
                    commonBean.setData(categorySeatsInfoBeanCommonBean.getData().getPOIs());
                } else if (tag == 1) {
                    commonBean.setData(categorySeatsInfoBeanCommonBean.getData().getSeats());
                } else {
                    commonBean.setMsg("tag不符合规范");
                }
            } else {
                commonBean.setStatus(categorySeatsInfoBeanCommonBean.getStatus());
                commonBean.setMsg(categorySeatsInfoBeanCommonBean.getMsg() == null ? "获取座位信息失败" : categorySeatsInfoBeanCommonBean.getMsg());
            }
        } else {
            commonBean.setStatus(categoryInfo.getStatus());
            commonBean.setMsg(categoryInfo.getMsg() == null ? "获取座位信息失败" : categoryInfo.getMsg());
        }
        return commonBean;
    }

    private HashMap<String, Object> getHeaderCoolie() {
        HashMap<String, Object> headers = new HashMap<>();
        String cookie = Util.getCookieStr(getCookie());
        headers.put("Cookie", cookie);
        return headers;
    }

    private void refreshCookie(Map<String, String> cookie) {
        if (cookie != null && !cookie.isEmpty())
            getCookie().putAll(cookie);
    }


    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public Map<String, String> getCookie() {
        return cookie;
    }

    public void setCookie(Map<String, String> cookie) {
        this.cookie = cookie;
    }
}
