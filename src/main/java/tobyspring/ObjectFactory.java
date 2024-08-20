package tobyspring;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.exrate.CachedExRateProvider;
import tobyspring.payment.ExRateProvider;
import tobyspring.exrate.WebApiExRateProvider;
import tobyspring.payment.PaymentService;

@Configuration
//@ComponentScan
public class ObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }
}
