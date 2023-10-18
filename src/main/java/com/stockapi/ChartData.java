package com.stockapi;

import lombok.Data;
import java.util.Map;

@Data
public class ChartData {
    private Chart chart;

    @Data
    public static class Chart {
        private Result[] result;
        private Object error;
    }

    @Data
    public static class Result {
        private Meta meta;
        private long[] timestamp;
        private Events events;
        private Indicators indicators;
    }

    @Data
    public static class Events {
        private Map<Long, Dividends> dividends;

        @Data
        public static class Dividends {
            private double amount;
            private long date;
        }
    }

    @Data
    public static class Indicators {
        private Quote[] quote;
        private AdjClose[] adjclose;
    }

    @Data
    public static class Meta {
        private String currency;
        private String symbol;
        private String exchangeName;
        private String instrumentType;
        private long firstTradeDate;
        private long regularMarketTime;
        private int gmtoffset;
        private String timezone;
        private String exchangeTimezoneName;
        private double regularMarketPrice;
        private double chartPreviousClose;
        private int priceHint;
        private CurrentTradingPeriod currentTradingPeriod;
        private String dataGranularity;
        private String range;
        private String[] validRanges;
    }

    @Data
    public static class CurrentTradingPeriod {
        private TradingPeriod pre;
        private TradingPeriod regular;
        private TradingPeriod post;
    }

    @Data
    public static class TradingPeriod {
        private String timezone;
        private long end;
        private long start;
        private int gmtoffset;
    }


    @Data
    public static class Quote {
        private long[] open;
        private long[] low;
        private long[] close;
        private long[] high;
        private long[] volume;

    }

    @Data
    public static class AdjClose {
        private double[] adjclose;
    }
}
