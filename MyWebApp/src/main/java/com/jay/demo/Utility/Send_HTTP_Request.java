package com.jay.demo.Utility;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.System.*;

@Component
public class Send_HTTP_Request {
    public static void main(String[] args){
        try{
            out.println(call_me("hello"));
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public static JSONArray call_me(String word) throws Exception {
        String url = "https://words.bighugelabs.com/api/2/9ed053a144459dc9ec12a6772b9dffcf/"+word+"/json?";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        out.println("\nSending 'GET' request to URL : " + url);
        out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print in String
        out.println(response.toString());

        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        JSONArray jsonArray = new JSONArray();

        if(response.toString().contains("noun")) {
            jsonArray.put(((JSONObject) myResponse.get("noun")).get("syn"));
        }
        else {
            jsonArray.put(((JSONObject) myResponse.get("adjective")).get("syn"));
        }
        return jsonArray;
    }
}
