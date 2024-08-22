package tobyspring.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class Payment {
    private Long orderId;
    private String currency;
    private BigDecimal foreignCurrencyAmount;
    private BigDecimal exRate;
    private BigDecimal convertedAmount;
    private LocalDateTime validUntil;

    // 생성자의 매개변수가 많은 경우 -> 같은 타입의 변수가 연속적으로 나와 실수할 수 있음
    // 빌더패턴으로 해결해야함
    public Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exRate, BigDecimal convertedAmount, LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreignCurrencyAmount = foreignCurrencyAmount;
        this.exRate = exRate;
        this.convertedAmount = convertedAmount;
        this.validUntil = validUntil;
    }

    public static Payment createPrepared(Long orderId, String currency, BigDecimal foreignCurrencyAmount, ExRateProvider exRateProvider,Clock clock)  {
        // exProvider를 받아온다
        // exProvider의 getExRate메서드를 호출해 exRate값을 받아온다
        // clock을 받아온다
        // clock을 이용해 validUntil을 구한다
        ExRateProvider provider = exRateProvider;
        BigDecimal exRate = provider.getExRate(currency);
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now(clock).plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    public boolean isValid(Clock clock) {
        return LocalDateTime.now(clock).isBefore(this.validUntil);
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getForeignCurrencyAmount() {
        return foreignCurrencyAmount;
    }

    public BigDecimal getExRate() {
        return exRate;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "orderId=" + orderId +
                ", currency='" + currency + '\'' +
                ", foreignCurrencyAmount=" + foreignCurrencyAmount +
                ", exRate=" + exRate +
                ", convertedAmount=" + convertedAmount +
                ", validUntil=" + validUntil +
                '}';
    }
}
// 빌더패턴 적용 버전
//package tobyspring.toby_spring6;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//public class Payment {
//    private Long orderId;
//    private String currency;
//    private BigDecimal foreignCurrencyAmount;
//    private BigDecimal exRate;
//    private BigDecimal convertedAmount;
//    private LocalDateTime validUntil;
//
//    private Payment(Builder builder) {
//        this.orderId = builder.orderId;
//        this.currency = builder.currency;
//        this.foreignCurrencyAmount = builder.foreignCurrencyAmount;
//        this.exRate = builder.exRate;
//        this.convertedAmount = builder.convertedAmount;
//        this.validUntil = builder.validUntil;
//    }
//
//    public static class Builder {
//        private Long orderId;
//        private String currency;
//        private BigDecimal foreignCurrencyAmount;
//        private BigDecimal exRate;
//        private BigDecimal convertedAmount;
//        private LocalDateTime validUntil;
//
//        public Builder orderId(Long orderId) {
//            this.orderId = orderId;
//            return this;
//        }
//
//        public Builder currency(String currency) {
//            this.currency = currency;
//            return this;
//        }
//
//        public Builder foreignCurrencyAmount(BigDecimal foreignCurrencyAmount) {
//            this.foreignCurrencyAmount = foreignCurrencyAmount;
//            return this;
//        }
//
//        public Builder exRate(BigDecimal exRate) {
//            this.exRate = exRate;
//            return this;
//        }
//
//        public Builder convertedAmount(BigDecimal convertedAmount) {
//            this.convertedAmount = convertedAmount;
//            return this;
//        }
//
//        public Builder validUntil(LocalDateTime validUntil) {
//            this.validUntil = validUntil;
//            return this;
//        }
//
//        public Payment build() {
//            return new Payment(this);
//        }
//    }
//
//    public Long getOrderId() {
//        return orderId;
//    }
//
//    public String getCurrency() {
//        return currency;
//    }
//
//    public BigDecimal getForeignCurrencyAmount() {
//        return foreignCurrencyAmount;
//    }
//
//    public BigDecimal getExRate() {
//        return exRate;
//    }
//
//    public BigDecimal getConvertedAmount() {
//        return convertedAmount;
//    }
//
//    public LocalDateTime getValidUntil() {
//        return validUntil;
//    }
//
//    @Override
//    public String toString() {
//        return "Payment{" +
//                "orderId=" + orderId +
//                ", currency='" + currency + '\'' +
//                ", foreignCurrencyAmount=" + foreignCurrencyAmount +
//                ", exRate=" + exRate +
//                ", convertedAmount=" + convertedAmount +
//                ", validUntil=" + validUntil +
//                '}';
//    }
//}
