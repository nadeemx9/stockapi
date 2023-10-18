package com.stockapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("get-chart")
    public ResponseEntity<ChartData> autoComplete(@RequestParam("symbol") String symbol,
                                                  @RequestParam(value = "interval", required = false, defaultValue = "1mo") String interval,
                                                  @RequestParam(value = "range", required = false, defaultValue = "5y") String range)
    {
        return new ResponseEntity<>(myService.getChart(symbol, interval, range), HttpStatus.OK);
    }
}
