package com.stockapi;

import lombok.Data;
import java.util.List;

@Data
public class CandleData {
    private Object status;
    private CandleDataDetail data;

    @Data
    public static class CandleDataDetail {
        private Object[][] candles;
    }

//    @Data
//    public static class Detail{
//        private String date;
//        private Double open;
//        private Double high;
//        private Double low;
//        private Double close;
//        private Long volume;
//        private Long trades;
//    }
}