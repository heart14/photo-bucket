package com.heart.photobucket.utils;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONObject;
import com.heart.photobucket.enums.ErrCodeEnums;
import com.heart.photobucket.exceptions.SysException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * About: HttpUtils工具类
 * Other:
 * Created: lwf14 on 2022/9/8 22:37.
 * Editored:
 */
public class HttpUtils {

    public static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    public static final String FIELD_CONNECTION = "Connection";
    public static final String FIELD_CHARSET = "Charset";
    public static final String FIELD_CONTENT_TYPE = "Content-Type";
    public static final String FIELD_CONTENT_LENGTH = "Content-Length";
    public static final String FIELD_ACCEPT = "Accept";
    public static final String CONNECTION_KEEP_ALIVE = "keep-alive";

    public static final int DEFAULT_CONNECT_TIMEOUT = 30000;
    public static final int DEFAULT_READ_TIMEOUT = 30000;
    public static final boolean DEFAULT_KEEP_ALIVE = true;

    public static String doGet(String targetUrl) throws SysException {
        return doGet(targetUrl, DEFAULT_CONNECT_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }

    /**
     * http get 请求
     *
     * @param targetUrl
     * @param connectTimeOut
     * @param readTimeOut
     * @return
     * @throws SysException
     */
    public static String doGet(String targetUrl, int connectTimeOut, int readTimeOut) throws SysException {
        StringBuffer response = new StringBuffer();
        BufferedReader reader = null;
        try {
            HttpURLConnection connection = getHttpURLConnection(HttpMethod.GET.name(), targetUrl, connectTimeOut, readTimeOut, Boolean.TRUE, null);
            connection.connect();
            // TODO 不进行这步处理的话，是不是返回错误页面html代码？
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                int responseCode = connection.getResponseCode();
                String responseMessage = connection.getResponseMessage();
                log.error("http connection fail :responseCode = {}, responseMessage = {}", responseCode, responseMessage);
                throw new SysException(ErrCodeEnums.HTTP_EXCEPTION.getCode(), ErrCodeEnums.HTTP_EXCEPTION.getMsg());
            }
            // 获取响应报文
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            log.info("http do get response :{}", response);
        } catch (Exception e) {
            throw new SysException(ErrCodeEnums.HTTP_EXCEPTION.getCode(), ErrCodeEnums.HTTP_EXCEPTION.getMsg());
        } finally {
            // 关闭IO流
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return response.toString();
    }

    public static String doPostUrlencoded(String targetUrl, String keyValueStr) throws SysException {
        return doPostUrlencoded(targetUrl, keyValueStr, DEFAULT_CONNECT_TIMEOUT, DEFAULT_READ_TIMEOUT, DEFAULT_KEEP_ALIVE);
    }

    /**
     * http post 请求，application/x-www-form-urlencoded
     *
     * @param targetUrl
     * @param keyValueStr
     * @param connectTimeOut
     * @param readTimeOut
     * @param keepAlive
     * @return
     * @throws SysException
     */
    public static String doPostUrlencoded(String targetUrl, String keyValueStr, int connectTimeOut, int readTimeOut, boolean keepAlive) throws SysException {
        StringBuffer response = new StringBuffer();
        // TODO 这个assert是jdk提供的吗
        assert keyValueStr != null;
        byte[] strBytes = keyValueStr.getBytes(StandardCharsets.UTF_8);
        Map<String, Object> map = new HashMap<>();
        map.put(FIELD_CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        map.put(FIELD_CONTENT_LENGTH, strBytes.length);
        OutputStream outputStream = null;
        BufferedReader reader = null;
        HttpURLConnection connection;
        try {
            connection = getHttpURLConnection(HttpMethod.POST.name(), targetUrl, connectTimeOut, readTimeOut, Boolean.TRUE, map);
            connection.connect();
            outputStream = connection.getOutputStream();
            outputStream.write(strBytes);
            outputStream.flush();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                int responseCode = connection.getResponseCode();
                String responseMessage = connection.getResponseMessage();
                log.error("http do post urlencoded fail :responseCode = {}, responseMessage = {}", responseCode, responseMessage);
                throw new SysException(ErrCodeEnums.HTTP_EXCEPTION.getCode(), ErrCodeEnums.HTTP_EXCEPTION.getMsg());
            }
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            log.info("http do post urlencoded response :{}", response);
        } catch (IOException e) {
            throw new SysException(ErrCodeEnums.HTTP_EXCEPTION.getCode(), ErrCodeEnums.HTTP_EXCEPTION.getMsg());
        } finally {
            // 关闭IO流
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return response.toString();
    }

    public static String doPostJson(String targetUrl, String jsonStr) throws SysException {
        return doPostJson(targetUrl, jsonStr, DEFAULT_CONNECT_TIMEOUT, DEFAULT_READ_TIMEOUT, DEFAULT_KEEP_ALIVE);
    }

    /**
     * http post 请求，application/json
     *
     * @param targetUrl
     * @param jsonStr
     * @param connectTimeOut
     * @param readTimeOut
     * @param keepAlive
     * @return
     * @throws SysException
     */
    public static String doPostJson(String targetUrl, String jsonStr, int connectTimeOut, int readTimeOut, boolean keepAlive) throws SysException {
        StringBuffer response = new StringBuffer();
        assert jsonStr != null;
        byte[] strBytes = jsonStr.getBytes(StandardCharsets.UTF_8);
        Map<String, Object> map = new HashMap<>();
        map.put(FIELD_CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        map.put(FIELD_CONTENT_LENGTH, strBytes.length);
        OutputStream outputStream = null;
        BufferedReader reader = null;
        try {
            HttpURLConnection connection = getHttpURLConnection(HttpMethod.POST.name(), targetUrl, connectTimeOut, readTimeOut, Boolean.TRUE, map);
            connection.connect();
            outputStream = connection.getOutputStream();
            outputStream.write(strBytes);
            outputStream.flush();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                int responseCode = connection.getResponseCode();
                String responseMessage = connection.getResponseMessage();
                log.error("http do post json fail :responseCode = {}, responseMessage = {}", responseCode, responseMessage);
                throw new SysException(ErrCodeEnums.HTTP_EXCEPTION.getCode(), ErrCodeEnums.HTTP_EXCEPTION.getMsg());
            }
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            log.info("http do post json response :{}", response);
        } catch (IOException e) {
            throw new SysException(ErrCodeEnums.HTTP_EXCEPTION.getCode(), ErrCodeEnums.HTTP_EXCEPTION.getMsg());
        } finally {
            // 关闭IO流
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return response.toString();
    }

    public static String doPostFormData(String targetUrl, String keyValueStr, List<File> fileList) throws SysException {
        return doPostFormData(targetUrl, keyValueStr, fileList, DEFAULT_CONNECT_TIMEOUT, DEFAULT_READ_TIMEOUT, DEFAULT_KEEP_ALIVE);
    }

    /**
     * http post 请求，multipart/form-data
     *
     * @param targetUrl
     * @param keyValueStr
     * @param fileList
     * @param connectTimeOut
     * @param readTimeOut
     * @param keepAlive
     * @return
     * @throws SysException
     */
    public static String doPostFormData(String targetUrl, String keyValueStr, List<File> fileList, int connectTimeOut, int readTimeOut, boolean keepAlive) throws SysException {
        return null;
    }

    /**
     * 获取HttpURLConnection对象
     *
     * @param method
     * @param targetUrl
     * @param connectTimeOut
     * @param readTimeOut
     * @param keepAlive
     * @param headers
     * @return
     * @throws SysException
     */
    private static HttpURLConnection getHttpURLConnection(String method, String targetUrl, int connectTimeOut, int readTimeOut, boolean keepAlive, Map<String, Object> headers) throws SysException {
        // 获取URL
        URL url;
        try {
            url = new URL(targetUrl);
        } catch (MalformedURLException e) {
            throw new SysException(ErrCodeEnums.HTTP_EXCEPTION.getCode(), ErrCodeEnums.HTTP_EXCEPTION.getMsg());
        }
        // 使用URL获取URLConnection
        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new SysException(ErrCodeEnums.HTTP_EXCEPTION.getCode(), ErrCodeEnums.HTTP_EXCEPTION.getMsg());
        }
        // 用户标识
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
        // 设置超时时间
        httpURLConnection.setConnectTimeout(connectTimeOut);
        httpURLConnection.setReadTimeout(readTimeOut);
        // 允许输入流/输出流
        httpURLConnection.setDoInput(Boolean.TRUE);
        httpURLConnection.setDoOutput(Boolean.TRUE);
        // 设置接受数据类型
        httpURLConnection.setRequestProperty(FIELD_ACCEPT, "*/*");
        // 不使用缓存
        httpURLConnection.setUseCaches(Boolean.FALSE);
        // 请求方式
        try {
            httpURLConnection.setRequestMethod(method);
        } catch (ProtocolException e) {
            throw new SysException(ErrCodeEnums.HTTP_EXCEPTION.getCode(), ErrCodeEnums.HTTP_EXCEPTION.getMsg());
        }
        // 字符集
        httpURLConnection.setRequestProperty(FIELD_CHARSET, StandardCharsets.UTF_8.name());
        // keep-alive
        if (keepAlive) {
            httpURLConnection.setRequestProperty(FIELD_CONNECTION, CONNECTION_KEEP_ALIVE);
        }
        // 请求头参数
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        return httpURLConnection;
    }


    /**
     * Map集合参数转key-value字符串
     *
     * @param map
     * @return
     * @throws SysException
     */
    public static String mapToKeyValueStr(Map<String, String> map) throws SysException {
        Assert.notNull(map, ErrCodeEnums.PARAMS_EXCEPTION.getMsg());
        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            try {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(key, StandardCharsets.UTF_8.name()));
                sb.append("=");
                sb.append(map.get(key));
            } catch (UnsupportedEncodingException e) {
                throw new SysException(ErrCodeEnums.CHARSET_EXCEPTION.getCode(), ErrCodeEnums.CHARSET_EXCEPTION.getMsg());
            }
        }
        return sb.toString();
    }

    /**
     * Map集合参数转json字符串
     *
     * @param map
     * @return
     */
    public static String mapToJsonStr(Map<String, String> map) {
        Assert.notNull(map, ErrCodeEnums.PARAMS_EXCEPTION.getMsg());
        JSONObject jsonObject = new JSONObject(map.size());
        jsonObject.putAll(map);
        return jsonObject.toJSONString();
    }
}
