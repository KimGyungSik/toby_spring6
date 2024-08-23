package tobyspring.exrate;

import org.springframework.web.client.RestTemplate;
import tobyspring.payment.ExRateProvider;

import java.math.BigDecimal;

public class RestTemplateExRateProvider implements ExRateProvider {
    private final RestTemplate restTemplate; // 스프링 빈으로 등록해두고 사용하는 것이 BEST

    public RestTemplateExRateProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/USD" + currency;

        return restTemplate.getForObject(url, ExRateData.class).rates().get("KRW");
    }
}
