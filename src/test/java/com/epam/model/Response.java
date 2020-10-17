package com.epam.model;

import java.util.List;

public class Response {
    private final String message;
    private final List<Data> data;

    public Response(String message, List<Data> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public List<Data> getData() {
        return data;
    }
}
