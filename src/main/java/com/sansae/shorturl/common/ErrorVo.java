package com.sansae.shorturl.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorVo {

    public ErrorVo(String result, String reason) {
        this.setResult(result);
        this.setReason(reason);
    }

    @JsonProperty("result")
    private String result = "";
    @JsonProperty("reason")
    private String reason = "";
}