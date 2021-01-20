package com.sansae.shorturl.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

public class ApplicationLog {
    @JsonProperty("app_name")
    @Getter
    @Setter
    private String appName = "SANSAE-API";

    @JsonProperty("app_index")
    @Getter @Setter
    private String applndex = "1";

    @JsonProperty("http_method")
    @Getter @Setter
    private String httpMethod = "";

    @JsonProperty("action_method")
    @Getter @Setter
    private String actionMethod = "";

    @JsonProperty("if")
    @Getter @Setter
    private String ifName = "";

    @JsonProperty("ver")
    private String ifVer = "";

    @JsonProperty("ui_name")
    @Getter
    private String uiName = "SANSAE-UI";

    @JsonProperty("latency")
    @Setter
    private long latency = 0;

    @JsonProperty("log_type")
    @Getter
    private String logType = "API";

    @JsonProperty("value")
    @Getter
    private String resultCode = "";

    @JsonProperty("message")
    @Getter
    private String resultMessage = "";

    @Setter
    private long startTime;

    public void setResult(ErrorVo errorVo) {
        this.resultCode = errorVo.getResult();
        this.resultMessage = errorVo.getReason();
    }

    public void setResult(String code, String msg) {
        this.resultCode = code;
        this.resultMessage = msg;
    }

    public void addMessage(String msg) {
        this.resultMessage = this.resultMessage +" | "+ msg;
    }

    public long getLatency(){
        return System.currentTimeMillis() - startTime;
    }

    public String getIfVer() {
        if (StringUtils.isNotBlank(actionMethod)){
            String[] token = actionMethod.split("/");
            if (token != null && token.length > 2) {
                return token[2];
            }
        }
        return "";
    }
}