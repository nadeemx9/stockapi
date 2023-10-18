package com.stockapi;

import lombok.val;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Map;

@Service
public class MyService {

    private final RestClient restClient;

    public MyService(RestClient restClient) {
        this.restClient = restClient;
    }

    public QuoteSummaryResponse getProfile(String symbol) {

        return restClient.get()
                .uri("/stock/v3/get-profile?symbol=" + symbol)
                .retrieve()
                .body(QuoteSummaryResponse.class);
    }

    public Object autoComplete(String q) {

        return restClient.get()
                .uri("/stock/v3/auto-complete?q=" + q)
                .retrieve()
                .body(Object.class);
    }

    public ChartData getChart(String symbol, String interval, String range) {
        return restClient.get()
                .uri("/stock/v3/get-chart?symbol=" + symbol + "&interval=" + interval + "&range=" + range)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ResponseError(response.getStatusCode(), response.getHeaders());
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new ResponseError(response.getStatusCode(), response.getHeaders());
                })
                .body(ChartData.class);
    }
}
