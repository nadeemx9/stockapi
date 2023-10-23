package com.stockapi;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MyService {

    private final RestClient restClient;

    public MyService(RestClient restClient) {
        this.restClient = restClient;
    }


    public CandleData candle(String instrumentKey, String interval) {
        CandleData candleData =  restClient.get()
                .uri("/historical-candle/intraday/"+instrumentKey+"/"+interval)
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
            obj[i][0] = formattedDateTime;
        }

        candleData.getData().setCandles(obj);
        return candleData;
    }

    public CandleData historicalCandle(String instrumentKey, String interval, String toDate, String fromDate) {
        LocalDate toLocalData=null;
        if(toDate == null)
            toDate = LocalDate.now().toString();

        if(fromDate == null) {
            toLocalData = LocalDate.parse(toDate);
            fromDate = toLocalData.minusMonths(1).toString();
        }

        CandleData candleData =  restClient.get()
                .uri("/historical-candle/"+instrumentKey+"/"+interval+"/"+toDate+"/"+fromDate)
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
            obj[i][0] = formattedDateTime;
        }

        candleData.getData().setCandles(obj);
        return candleData;
    }
}
