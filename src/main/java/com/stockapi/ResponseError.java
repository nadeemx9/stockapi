package com.stockapi;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

public class ResponseError extends RuntimeException {
    public ResponseError(HttpStatusCode statusCode, HttpHeaders headers) {
        super("Something went wrong.");
    }
}
