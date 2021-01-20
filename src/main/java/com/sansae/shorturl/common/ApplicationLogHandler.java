package com.sansae.shorturl.common;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApplicationLogHandler{

    private ApplicationLog data;
    ObjectMapper mapper = new ObjectMapper();

    public ApplicationLogHandler() {
        data = new ApplicationLog();
    }

    public String getJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(data);
        } catch (Exception e) {
            return "{\"MSG\":\"JSON PASER ERROR\"}";
        }
    }

    public String getJSON(String msg) {
        data.addMessage(msg);
        try {
            return mapper.writeValueAsString(data);
        } catch (Exception e) {
            return "{\"MSG\":\"JSON PASER ERROR\"}";
        }
    }

    public void setResult(ErrorVo errorVo) {
        data.setResult(errorVo);
    }

    public void setResult(String code, String msg) {
        data.setResult(code,msg);
    }

    public void setHttpMethod(String method) {
        data.setHttpMethod(method);
    }

    public void setIfName(String s) {
        data.setStartTime(System.currentTimeMillis());
        data.setIfName(s);
    }

    public void setActionMethod(String s) {
        data.setActionMethod(s);
    }
}