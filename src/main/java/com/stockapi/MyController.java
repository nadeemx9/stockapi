package com.stockapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("stock")
public class MyController {

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("profile")
    public ResponseEntity<QuoteSummaryResponse> getProfile(@RequestParam(name = "symbol") String symbol)
    {
        return new ResponseEntity<>(myService.getProfile(symbol), HttpStatus.OK);
    }

    @GetMapping("auto-complete")
    public ResponseEntity<Object> autoComplete(@RequestParam("q") String q)
    {
        return new ResponseEntity<>(myService.autoComplete(q), HttpStatus.OK);
    }
    @GetMapping("candle/{instrumentKey}")
    public ResponseEntity<CandleData> candle(@PathVariable String instrumentKey)
    {
        return new ResponseEntity<>(myService.candle(instrumentKey), HttpStatus.OK);
    }
}
