package org.example.problems.urlShortener;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        System.out.println("Usage: shorten(\"https://example.com\") or restore(\"http://short.ly/a\")\n");
        Scanner scanner = new Scanner(System.in);
        UrlShortener urlShortener = new UrlShortener();
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String operation = StringUtils.substringBefore(input, "(");
            String url = StringUtils.substringBetween(input,"(\"","\")");
            if(!isValidUrl(url)) {
                System.out.println("not a valid url");
                continue;
            }
            if("shorten".equalsIgnoreCase(operation)) {
                if(StringUtils.isEmpty(url)){
                    System.out.println("please provide a valid url");
                }else {
                    String shortenedUrl = urlShortener.shorten(url);
                    System.out.println("shortenedUrl is: " + shortenedUrl);
                }
            }else if("restore".equalsIgnoreCase(operation)) {
                String originalUrl = urlShortener.restore(url);
                if(StringUtils.isEmpty(originalUrl)) {
                    System.out.println("Invalid Request!");
                }else{
                    System.out.println("Original url is = " + originalUrl);
                }
            }else{
                System.out.println("Invalid Operation!");
            }
        }
    }
    private static boolean isValidUrl( String url ) {
        return StringUtils.isNotEmpty(url);
    }
}
