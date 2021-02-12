package com.webspoons.SnipetTimedUrl.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class SnippetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private LocalDateTime expiryDate;
    private String snippet;
    private boolean valid;

    public void setExpiryDate(int seconds) {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.expiryDate = localDateTime.plusSeconds(seconds);
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public boolean isValid() {
        return this.expiryDate.isAfter(LocalDateTime.now());
    }
}
