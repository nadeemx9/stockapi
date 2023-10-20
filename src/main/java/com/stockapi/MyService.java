package com.stockapi;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

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

    public CandleData candle(String instrumentKey) {
        CandleData candleData =  restClient.get()
                .uri("/historical-candle/intraday/"+instrumentKey+"/1minute")
                .retrieve()
                .body(CandleData.class);

        Object[][] obj = candleData.getData().getCandles();
        for(int i = 0 ; i < obj.length; i++)
        {
            String inputDateTime = obj[i][0].toString();
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(inputDateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

            // Define the desired output format
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");

            // Format the OffsetDateTime to the desired format
            String formattedDateTime = offsetDateTime.format(outputFormatter);
            System.out.println(formattedDateTime);
            obj[i][0] = formattedDateTime;
        }
//        List<Object> candles = Arrays.asList(candleData.getData().getCandles().toArray());
//        System.out.println(candles.get(0));
        candleData.getData().setCandles(obj);
        return candleData;
    }
}
