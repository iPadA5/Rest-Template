package org.example.service;

import org.springframework.stereotype.Component;

@Component
public class SessionIdHandler {
    public static String parseSessionId(String setCookieHeader) {
        String sessionId = "";
        if (setCookieHeader != null) {
            String[] cookies = setCookieHeader.split(";");
            for (String cookie : cookies) {
                if (cookie.trim().startsWith("JSESSIONID=")) {
                    sessionId = cookie.trim();
                    break;
                }
            }
        }
        return sessionId;
    }
}
