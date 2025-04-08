package org.example.service;

import org.springframework.stereotype.Component;

@Component
public class SessionIdParser {
    public String parseSessionId(String setCookieHeader) {
        String sessionId = "";
        if (setCookieHeader != null) {
            String[] cookies = setCookieHeader.split(";");
            for (String cookie : cookies) {
                if (cookie.trim().startsWith("sessionId=")) {
                    sessionId = cookie.trim().substring("sessionId=".length());
                    break;
                }
            }
        }
        return sessionId;
    }
}
