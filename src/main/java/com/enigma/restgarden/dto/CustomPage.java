package com.enigma.restgarden.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class CustomPage<T> {
    private List<T> data;
    private Integer page;
    private Integer size;
    private Long totalRows;
    private Integer totalPage;

    public CustomPage(Page<T> pageData) {
        this.data = pageData.getContent();
        this.page = pageData.getPageable().getPageNumber();
        this.totalRows = pageData.getTotalElements();
        this.totalPage = pageData.getTotalPages();
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
