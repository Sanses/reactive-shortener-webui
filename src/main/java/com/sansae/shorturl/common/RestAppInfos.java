package com.sansae.shorturl.common;

import lombok.Getter;
import java.util.Arrays;

public enum RestAppInfos {

    IF_SANSAE_001("IF-SANSAE-001","/v1/users");

    @Getter
    final private String ifName;
    @Getter
    final private String action;

    private RestAppInfos(String ifName, String action){
        this.ifName = ifName;
        this.action = action;
    }

    public static String findByIFname(String action) {

        try {
            return Arrays.stream(values()).filter(value -> action.equals(value.getAction())).findAny().get().getIfName();
        }catch (Exception e) {
            return "";
        }
    }
}
