package org.example.service;

import lombok.AllArgsConstructor;
import org.example.holder.SessionIdHolder;
import org.example.model.UserModel;
import org.example.service.interfaces.UserService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final SessionIdHolder sessionIdHolder;

    @Override
    public List<UserModel> getUsers(){
        ResponseEntity<List<UserModel>> responseEntity = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );
        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<String> addUser(UserModel userModel) {
        HttpEntity<UserModel> httpEntity = formHttpEntity(userModel);
        return restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, String.class);
    }

    @Override
    public ResponseEntity<String> updateUser(UserModel userModel) {
        HttpEntity<UserModel> httpEntity = formHttpEntity(userModel);
        return restTemplate.exchange(apiUrl, HttpMethod.PUT, httpEntity, String.class);
    }

    @Override
    public ResponseEntity<String> deleteUser(Long userId) {
        String sessionId = sessionIdHolder.getSessionId();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);
        HttpEntity<UserModel> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange((apiUrl + "/" + userId), HttpMethod.DELETE, httpEntity, String.class);
    }

    private HttpEntity<UserModel> formHttpEntity(UserModel userModel) {
        String sessionId = getSessionId();
        HttpHeaders headers = new HttpHeaders();
        if (sessionId != null && !sessionId.isEmpty()) {
            headers.add("Cookie", sessionId);
        }
        return new HttpEntity<>(userModel, headers);
    }

    private String getSessionId() {
        return sessionIdHolder.getSessionId();
    }
}
