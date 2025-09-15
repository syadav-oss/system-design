package org.example.redis.domain;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {
    private String id;
    private String name;

    public User(String id, String s) {
        this.id = id;
        this.name = s;
    }
}

