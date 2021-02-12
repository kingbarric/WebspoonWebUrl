package com.webspoons.SnipetTimedUrl.pojo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SnippetRequest {
    private String name;
    private int expiryDate;
    private String snippet;
}
