package com.example.backend.entity;

import com.example.backend.entity.api.Data;

import java.util.List;


public class Response {

    private String operationDate;

    private List<DataRs> data;

    public Response() {
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    public List<DataRs> getData() {
        return data;
    }

    public void setData(List<DataRs> data) {
        this.data = data;
    }

}
