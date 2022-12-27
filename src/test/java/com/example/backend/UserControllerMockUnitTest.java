package com.example.backend;

import com.example.backend.entity.Response;
import com.example.backend.entity.api.Client;
import com.example.backend.service.UserService;
import com.example.backend.web.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(MockitoJUnitRunner.class)
class UserControllerMockUnitTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    private UserController userController = new UserController();

    @Mock
    private UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateUserControllerThenReturnDataIsSuccessful() throws IOException {
        Path path = Paths.get("src/test/resources/client.json");
        String clientOfFile = new String(Files.readAllBytes(path));
        Client client = mapper.readValue(clientOfFile, Client.class);
        Mockito.when(userService.callToUsers()).thenReturn(client);

        Path path2 = Paths.get("src/test/resources/user.json");
        String rsOfFile = new String(Files.readAllBytes(path2));
        Response rsExpected = mapper.readValue(rsOfFile, Response.class);

        Response rs = userController.getResponse();
        Assertions.assertEquals(rs.getData().size(), rsExpected.getData().size());
    }

}
