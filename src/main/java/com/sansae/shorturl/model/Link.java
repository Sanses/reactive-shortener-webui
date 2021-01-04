package com.sansae.shorturl.model;

import lombok.Value;

@Value
public class Link {

    String originalLink;
    String key;
}