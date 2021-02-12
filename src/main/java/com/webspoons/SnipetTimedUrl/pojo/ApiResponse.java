package com.webspoons.SnipetTimedUrl.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse {
    private String url;
    private String name;
    private LocalDateTime expiresAt;
    private String snippet;
}
