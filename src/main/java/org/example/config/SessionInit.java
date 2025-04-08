package org.example.config;

import org.example.holder.SessionIdHolder;
import org.example.service.SessionIdHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SessionInit {
    private final String apiUrl;
    private final RestTemplate restTemplate;
    private final SessionIdHolder sessionIdHolder;

    @Autowired
    public SessionInit(String apiUrl, RestTemplate restTemplate, SessionIdHolder sessionIdHolder) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        this.sessionIdHolder = sessionIdHolder;
    }

    public void initialize() {
        HttpEntity<?> httpEntity = formHttpEntityWithHeaders();
        ResponseEntity<String> response = restTemplate.
                exchange(apiUrl, HttpMethod.GET, httpEntity, String.class);
         sessionIdHolder.setSessionId(SessionIdHandler
                 .parseSessionId(response
                         .getHeaders()
                         .getFirst("Set-Cookie")));
    }

    private HttpEntity<?> formHttpEntityWithHeaders() {
        String sessionId = sessionIdHolder.getSessionId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (sessionId != null && !sessionId.isEmpty()) {
            headers.add("Cookie", sessionId); // Добавляем сохраненный идентификатор сессии
        }
        return new HttpEntity<>(headers);
    }
}
