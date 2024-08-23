package tobyspring;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import tobyspring.api.ApiTemplate;
import tobyspring.api.ErApiExRateExtractor;
import tobyspring.api.SimpleApiExecutor;
import tobyspring.exrate.RestTemplateExRateProvider;
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

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new JdkClientHttpRequestFactory());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new RestTemplateExRateProvider(restTemplate());
    }

    //    @Bean
//    public ExRateProvider cachedExRateProvider() {
//        return new CachedExRateProvider(exRateProvider());
//    }

//    @Bean
//    public ApiTemplate apiTemplate() {
//        return new ApiTemplate(new SimpleApiExecutor(),new ErApiExRateExtractor());
//    }
}
