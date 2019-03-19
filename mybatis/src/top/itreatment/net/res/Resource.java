package top.itreatment.net.res;

public class Resource {

    public static final Integer SUCCESS = 200;
    public static final Integer FAIL = 100;
    public static final Integer ERROR = -200;


    public static final String SPACE_CATEGORY_BCATEGORY_ID = "591";
    public static final String LAB_JSON_VALUE = "1";
    public static final String LAB_JSON_KEY = "LAB_JSON";





    public static class URLRes {
        public static String BASE_URL = "https://jxnu.huitu.zhishulib.com";
        public static String LOGIN_URL = "/api/1/login";
        public static String LOGOUT_URL = "/user/index/logout";
        public static String CATEGORYLIST = "/Space/Category/list";
        public static String SEARCHSEATS = "/Seat/Index/searchSeats";
        public static String BOOKSEATS = "/Seat/Index/bookSeats";
        public static String MYBOOKINGLIST = "/Seat/Index/myBookingList";
        public static String CANCELBOOKING = "/Seat/Index/cancelBooking";
        public static String UNLOCKALLSEATS = "/Seat/Index/unlockAllSeats";
    }

    //登录时用到的常量
    public static class LoginRes {
        public static String str = "u4MSMzQ8ATw91npm";
        public static String _JavaScriptKey = "lab4";
        public static String _ClientVersion = "js_xxx";
        public static String code = "cb31399a41b330f8991e118598c34288";
        public static String _InstallationId = "200eb7f5-b3f1-8ebe-bf74-088edc473dbf";
        public static String org_id = "142";
        public static String _ApplicationId = "lab4";
        public static String login_name_type = "school_number";
    }


    //通用连接头
    public static class CommonHeaders {
        public static String Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
        public static String Accept_Encoding = "gzip, deflate, br";
        public static String Accept_Language = "zh-CN,zh;q=0.9";
        public static String Cache_Control = "no-cache";
        public static String Connection = "keep-alive";
        public static String Host = "jxnu.huitu.zhishulib.com";
        public static String Pragma = "no-cache";
        public static String User_Agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36";
        public static String Upgrade_Insecure_Requests = "1";
    }

}
