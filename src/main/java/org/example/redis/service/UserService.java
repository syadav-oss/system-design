package org.example.redis.service;

import org.example.redis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    private final RedisTemplate<String, User> redisTemplate;

    private static final String KEY_PREFIX = "USER::";

    public UserService(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public User getUserById(String id) {
        String key = KEY_PREFIX + id;

        User cachedUser = redisTemplate.opsForValue().get(key);
        if (cachedUser != null) {
            System.out.println("Returned from cache");
            return cachedUser;
        }

        // Simulate DB fetch
        User user = new User(id, "User_" + id);
        redisTemplate.opsForValue().set(key, user, 10, TimeUnit.MINUTES);
        System.out.println("Fetched from DB & cached");
        return user;
    }
}

