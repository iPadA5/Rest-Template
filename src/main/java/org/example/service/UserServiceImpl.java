package org.example.service;

import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.example.model.UserModel;
import org.example.model.request.RequestDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final RestTemplate restTemplate;
    private final String apiUrl;

    @Override
    public List<UserModel> getUsers(){
        ResponseEntity<List<UserModel>> responseEntity = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );
        return responseEntity.getBody();
    }

    @Override
    public String addUser(RequestDto requestDto) {
        HttpEntity<RequestDto> request = new HttpEntity<>(requestDto);
        return restTemplate.postForEntity(apiUrl, request, String.class).getBody();
    }
}
