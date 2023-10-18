package com.stockapi;

import lombok.Data;

@Data
public class QuoteSummaryResponse {
    private QuoteSummary quoteSummary;

    @Data
    public static class QuoteSummary {
        private Result[] result;
        private Object error;

        @Data
        public static class Result {
            private SummaryProfile summaryProfile;

            @Data
            public static class SummaryProfile {
                private String address1;
                private String address2;
                private String city;
                private String zip;
                private String country;
                private String phone;
                private String fax;
                private String website;
                private String industry;
                private String industryKey;
                private String industryDisp;
                private String sector;
                private String sectorKey;
                private String sectorDisp;
                private String longBusinessSummary;
                private int fullTimeEmployees;
                private String[] companyOfficers; // You might want to replace Object with an appropriate class if available
                private int maxAge;
            }
        }

    }

}
