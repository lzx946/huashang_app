package com.lzx.hsapp.util.mapUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetLocationMsg {

    private static String ak = "KLFFxOn0TuNH15d99PH2e0NGvNvZMSKi";

    public final static void main(String[] args) {

		 /*//String jsonStr = GetLocationMs(22.329157, 114.203258);
		JSONObject jsonObj = JSONArray.parseObject(jsonStr);
		JSONArray jsonArray = (JSONArray) jsonObj.get("results");
		System.out.println(jsonArray);
		List list = new ArrayList();// 用list保存全部数据
		for (int i = 0; i < jsonArray.size(); i++) {
			Userdetails user = (Userdetails) JSONObject.toJavaObject(jsonArray.getJSONObject(i), Userdetails.class);
			// System.out.println(user.toString());
				list.add(user.getFormatted_address());
		}
		System.out.println(list);*/
    }

    public static String GetLocationMs(double d, double f) {

        String message = "";

        String url = String.format(

                "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=%s,%s&output=json&pois=0&latest_admin=1&ak=%s",

                d, f,ak);

        URL myURL = null;

        URLConnection httpsConn = null;

        try {

            myURL = new URL(url);

        } catch (MalformedURLException e) {

            e.printStackTrace();

        }

        try {

            httpsConn = (URLConnection) myURL.openConnection();

            if (httpsConn != null) {

                InputStreamReader insr = new InputStreamReader(

                        httpsConn.getInputStream(), "UTF-8");

                BufferedReader br = new BufferedReader(insr);

                String data = null;

                while ((data = br.readLine()) != null) {

                    message = message + data;

                }

                insr.close();

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        return message;

    }

}
