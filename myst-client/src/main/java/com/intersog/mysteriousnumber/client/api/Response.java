package com.intersog.mysteriousnumber.client.api;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private final int statusCode;
    private final String responseMessage;
    public Response(int statusCode, String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public String getResponseMessage() {
        return responseMessage;
    }
    public static Map<String, String> getParametersFromRequest(String responseBody) {
        Map<String, String> parametersMap = new HashMap<>();
        String[] params = responseBody.split("&");
        for (String param: params) {
            String[] kv = param.split("=", -1);
            System.out.println(responseBody);
            parametersMap.put(kv[0], kv[1]);
        }
        return parametersMap;
    }
    @Override
    public String toString() {
        return "Response{" +
                "statusCode=" + statusCode +
                ", responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
