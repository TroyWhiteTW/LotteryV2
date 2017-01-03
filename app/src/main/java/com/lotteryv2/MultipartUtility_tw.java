package com.lotteryv2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TroyWhite on 2016/11/13.
 */

public class MultipartUtility_tw {
    private HttpURLConnection httpConn;
    private OutputStream outputStream;

    MultipartUtility_tw(String requestURL) throws IOException {
        URL url = new URL(requestURL);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setReadTimeout(30000);// 設置讀取超時
        httpConn.setConnectTimeout(60000);// 設置連接網路超時
        httpConn.setUseCaches(false);
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        httpConn.setRequestMethod("POST");// 設置請求方法為POST
    }

    public void postKeyValue(String key, String value) throws IOException {
        String data = "&" + key + "=" + value;
        outputStream = httpConn.getOutputStream();
        outputStream.write(data.getBytes());
        outputStream.flush();
    }

    public void sendCookie(String cookie) {
        httpConn.setRequestProperty("Cookie", cookie);
    }

    public String getCookie() {
        return httpConn.getHeaderField("Set-Cookie");
    }

    public int getResponseCode() throws IOException {
        return httpConn.getResponseCode();
    }

    public List<String> getHtml() throws IOException {
        List<String> response = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
        String line = null;
        while ((line = reader.readLine()) != null) response.add(line);
        reader.close();
        return response;
    }

    public JSONObject getJSONObjectData() throws IOException, JSONException {
        String line = getHtml().get(0);
        JSONObject jo = new JSONObject(line);
        return jo;
    }

    public JSONArray getJSONArrayData() throws IOException, JSONException {
        String line = getHtml().get(0);
        JSONArray jo = new JSONArray(line);
        return jo;
    }

    public void disconnect() throws IOException {
        outputStream.close();
        httpConn.disconnect();
    }

}
