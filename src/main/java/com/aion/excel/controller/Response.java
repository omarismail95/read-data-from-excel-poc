package com.aion.excel.controller;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private String responseStatus;
    private List<varsMap> result = new ArrayList<>();

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public List<varsMap> getResult() {
        return result;
    }

    public void setResult(List<varsMap> result) {
        this.result = result;
    }
}
