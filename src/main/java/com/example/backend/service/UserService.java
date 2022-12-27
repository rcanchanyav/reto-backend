package com.example.backend.service;

import com.example.backend.entity.api.Client;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserService {

    Client callToUsers() throws JsonProcessingException;
}
