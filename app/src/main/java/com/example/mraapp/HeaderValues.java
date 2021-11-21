package com.example.mraapp;

import java.util.HashMap;
import java.util.Map;

public class HeaderValues {
    private static Map<String, String> headers = new HashMap<String,String>();

    public static Map<String, String> getHeaders() {
        headers.put("Content-Type","application/json");
        headers.put("candidateid","kondwa@gmail.com");
        headers.put("apikey","3fdb48c5-336b-47f9-87e4-ae73b8036a1c");
        headers.put("Authorization",SessionManager.getAuthorization());
        return headers;
    }

}
