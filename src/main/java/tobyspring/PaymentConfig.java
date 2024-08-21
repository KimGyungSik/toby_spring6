package tobyspring;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.exrate.WebApiExRateProvider;
import tobyspring.payment.ExRateProvider;
import tobyspring.payment.PaymentService;

import java.time.Clock;

@Configuration // = 구성정보다.
//@ComponentScan
public class PaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(),clock());
    }

//    @Bean
//    public ExRateProvider cachedExRateProvider() {
//        return new CachedExRateProvider(exRateProvider());
//    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }
}
