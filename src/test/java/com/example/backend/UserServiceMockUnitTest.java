package com.example.backend;

import com.example.backend.config.Application;
import com.example.backend.entity.api.Client;
import com.example.backend.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(MockitoJUnitRunner.class)
class UserServiceMockUnitTest {
    private final ObjectMapper mapper = new ObjectMapper();

    private String urlClient;
    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl();
    @Spy
    private RestTemplate restTemplate;
    @Mock
    private Application application;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        urlClient = "https://reqres.in/api/users";
    }

    @Test
    void validateClientThenReturnsDataIsSuccessful() throws IOException {
        Path path = Paths.get("src/test/resources/client.json");
        String clientOfFile = new String(Files.readAllBytes(path));
        Client cli = mapper.readValue(clientOfFile, Client.class);
        Mockito.when(application.getApiHost()).thenReturn(urlClient);
        Mockito.when(restTemplate.getForEntity(urlClient, Client.class)).thenReturn(new ResponseEntity<>(cli, HttpStatus.OK));
        Client client = userService.callToUsers();
        Assertions.assertEquals(cli.getPage(), client.getPage());
        Assertions.assertEquals(cli.getTotalPages(), client.getTotalPages());
    }
}
