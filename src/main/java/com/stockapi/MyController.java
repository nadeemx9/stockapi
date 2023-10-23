package com.stockapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("stock")
public class MyController {

    String str;
    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("candle")
    public ResponseEntity<CandleData> candle(@RequestParam(name = "instrumentKey") String instrumentKey,
                                             @RequestParam(name = "interval", required = false, defaultValue = "1minute") String interval)
    {
        return new ResponseEntity<>(myService.candle(instrumentKey, interval), HttpStatus.OK);
    }
    @GetMapping("historicalCandle")
    public ResponseEntity<CandleData> historicalCandle(@RequestParam(name = "instrumentKey") String instrumentKey,
                                                       @RequestParam(name = "interval", required = false, defaultValue = "1minute") String interval,
                                                       @RequestParam(name = "toDate", required = false) String toDate,
                                                       @RequestParam(name = "fromDate", required = false) String fromDate)
    {
        return new ResponseEntity<>(myService.historicalCandle(instrumentKey, interval, toDate, fromDate), HttpStatus.OK);
    }
}
