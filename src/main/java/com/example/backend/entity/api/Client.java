package com.example.backend.entity.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Client {

    private int page;
    @JsonProperty("per_page")
    private int perPages;
    private int total;
    @JsonProperty("total_pages")
    private int totalPages;
    private List<Data> data;
    private Support support;

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public int getPerPages() {
        return perPages;
    }

    public void setPerPages(int perPages) {
        this.perPages = perPages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
