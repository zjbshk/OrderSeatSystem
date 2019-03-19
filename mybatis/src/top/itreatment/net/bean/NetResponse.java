package top.itreatment.net.bean;

import java.util.Map;

public class NetResponse {

    private int code;
    private String url;
    private Map<String, String> cookie;
    private String data;

    public NetResponse() {
    }

    public NetResponse(int code, String url) {
        this.code = code;
        this.url = url;
    }

    public NetResponse(int code, String url, Map<String, String> cookie) {
        this.code = code;
        this.url = url;
        this.cookie = cookie;
    }

    public NetResponse(int code, String url, Map<String, String> cookie, String data) {
        this.code = code;
        this.url = url;
        this.cookie = cookie;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getCookie() {
        return cookie;
    }

    public void setCookie(Map<String, String> cookie) {
        this.cookie = cookie;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NetResponse{" +
                "code=" + code +
                ", url='" + url + '\'' +
                ", cookie='" + cookie + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
