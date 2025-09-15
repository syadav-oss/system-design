package org.example.problems.urlShortener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UrlShortener {
    private static final String BASE_URL = "http://short.ly/";
    AtomicLong counter = new AtomicLong(0);
    private final char[] base62 = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u',
            'v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',
            'S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};

    private final Map<String, String> urlMap = new ConcurrentHashMap<>();  // short to original
    private final Map<String, String> reverseUrlMap = new ConcurrentHashMap<>(); // original to short

    public String shorten(String url) {

        reverseUrlMap.computeIfAbsent(url, k -> {
            String shortenedUrl = BASE_URL + getBase62(counter.getAndIncrement());
            urlMap.put(shortenedUrl, url);
            return shortenedUrl;
        });

        return reverseUrlMap.get(url);
    }

    public String restore(String shortenedUrl) {
        return urlMap.get(shortenedUrl);
    }

    public String getBase62( long num ) {
        StringBuilder res = new StringBuilder();
        while(num > 0) {
            int rem = (int)num%62;
            res.append(base62[rem]);
            num = num/62;
        }
        if(res.isEmpty()){
            res.append("a");
        }
        res.reverse();
        return res.toString();
    }
}
