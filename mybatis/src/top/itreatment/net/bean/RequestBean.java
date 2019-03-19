package top.itreatment.net.bean;

import top.itreatment.net.core.Net;

import java.util.Map;

public class RequestBean {


    private String url;
    private String outData;
    private Map<String, Object> headers;
    private Method method = Method.GET;
    private String encoding = Net.ENCODING;

    public RequestBean() {
    }

    public RequestBean(String url) {
        this.url = url;
    }

    public RequestBean(String url, String encoding) {
        this.url = url;
        this.encoding = encoding;
    }

    public RequestBean(String url, Map<String, Object> headers) {
        this.url = url;
        this.headers = headers;
    }

    public RequestBean(String url, String outData, Method method) {
        this.url = url;
        this.outData = outData;
        this.method = method;
    }

    public RequestBean(String url, String outData, Map<String, Object> headers, Method method) {
        this.url = url;
        this.outData = outData;
        this.headers = headers;
        this.method = method;
    }

    public RequestBean(String url, String outData, Map<String, Object> headers, Method method, String encoding) {
        this.url = url;
        this.outData = outData;
        this.headers = headers;
        this.method = method;
        this.encoding = encoding;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOutData() {
        return outData;
    }

    public void setOutData(String outData) {
        this.outData = outData;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public enum Method {
        POST,
        GET,
        DELETE,
        PUT,
        HEAD
    }

}
