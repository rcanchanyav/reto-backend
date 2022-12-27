package com.example.backend.web;

import com.example.backend.entity.DataRs;
import com.example.backend.entity.Response;
import com.example.backend.entity.api.Client;
import com.example.backend.entity.api.Data;
import com.example.backend.service.UserService;
import com.example.backend.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/backend")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/users")
    public Response getResponse() throws JsonProcessingException {
        String formattedDate = Util.parseIsoDateTime(new Date().toString());
        Client client = service.callToUsers();
        Response rs = new Response();
        List<DataRs> listDataRs = new ArrayList<>();
        for (Data dataIn : client.getData()) {
            DataRs dataRs = new DataRs();
            dataRs.setEmail(dataIn.getEmail());
            dataRs.setId(dataIn.getId());
            dataRs.setLastName(dataIn.getLastName());
            listDataRs.add(dataRs);
        }
        rs.setData(listDataRs);
        rs.setOperationDate(formattedDate);
        return rs;
    }


}
