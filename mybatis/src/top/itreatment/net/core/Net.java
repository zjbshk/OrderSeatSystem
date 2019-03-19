package top.itreatment.net.core;

import top.itreatment.net.bean.CommonBean;
import top.itreatment.net.bean.NetResponse;
import top.itreatment.net.bean.RequestBean;
import top.itreatment.net.res.Resource;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Net,网络请求处理包
 */
public class Net {

    public static final String ENCODING = "utf-8";


    public static CommonBean<NetResponse> net(RequestBean requestBean) {
        CommonBean<NetResponse> commonBean = new CommonBean<>();
        NetResponse netResponse = new NetResponse();
        commonBean.setData(netResponse);
        try {
            String urlpath = requestBean.getUrl();

            if (urlpath == null) {
                throw new RuntimeException("URL不可以为空");
            }
            URL url = new URL(urlpath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //设置一些默认选项
            connection.setReadTimeout(30000);
            connection.setConnectTimeout(30000);

            //设置请求头
            Map<String, Object> headers = requestBean.getHeaders();
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, Object> kv : headers.entrySet()) {
                    String key = kv.getKey();
                    Object value = kv.getValue();
                    if (key == null) continue;
                    else if (value == null) {
                        value = "";
                    }
                    connection.setRequestProperty(key, value.toString());
                }
            }
            //设置请求方法
            RequestBean.Method method = requestBean.getMethod();
            if (method == null) {
                throw new RuntimeException("method不可以为空");
            }
            connection.setRequestMethod(method.name());
            if (method == RequestBean.Method.POST && requestBean.getOutData() != null) {
                connection.setDoOutput(true);
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(requestBean.getOutData().getBytes());
                outputStream.flush();
                outputStream.close();
            }
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                if (requestBean.getEncoding() == null) {
                    requestBean.setEncoding(ENCODING);
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(is, requestBean.getEncoding()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null && is.available() != 0) {
                    sb.append(line);
                }
                //关闭连接和流
                br.close();
                is.close();
                connection.disconnect();


                netResponse.setCode(responseCode);
                netResponse.setUrl(urlpath);
                Map<String, List<String>> headerFields = connection.getHeaderFields();
                String setCookie = "Set-Cookie";
                List<String> coolies = headerFields.get(setCookie);
                if (coolies != null && !coolies.isEmpty()) {
                    Map<String, String> cooliesMapTemp = new HashMap<>();
                    for (String coolie : coolies) {
                        if (coolie == null) continue;
                        int end = coolie.indexOf("=");
                        if (end > 0) {
                            String name = coolie.substring(0, end);
                            cooliesMapTemp.put(name, coolie);
                        }
                    }
                    netResponse.setCookie(cooliesMapTemp);
                }
                netResponse.setData(sb.toString());
                commonBean.setStatus(Resource.SUCCESS);
            } else {
                throw new RuntimeException("获取请求响应码为:" + responseCode + "\nURL:" + urlpath);
            }
        } catch (MalformedURLException e) {
            commonBean.setStatus(Resource.FAIL);
            commonBean.setMsg("URL有误，请检查");
            e.printStackTrace();
        } catch (IOException e) {
            commonBean.setStatus(Resource.FAIL);
            commonBean.setMsg("服务器出错");
            e.printStackTrace();
        } catch (RuntimeException e) {
            commonBean.setStatus(Resource.FAIL);
            commonBean.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return commonBean;
    }
}
