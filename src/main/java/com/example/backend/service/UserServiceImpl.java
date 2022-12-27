package com.example.backend.service;

import com.example.backend.config.Application;
import com.example.backend.entity.api.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Application application;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Client callToUsers() throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(application.getApiHost(),
                String.class, Map.of("page", 1));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Client rs = mapper.readValue(response.getBody(), Client.class);
        return rs;
    }
}
