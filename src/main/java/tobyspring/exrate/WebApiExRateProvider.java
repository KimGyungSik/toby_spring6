package tobyspring.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import tobyspring.api.*;
import tobyspring.payment.ExRateProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;

@Component
public class WebApiExRateProvider implements ExRateProvider {
    // WebApiExRateProvider의 구성
    // 1. URI를 준비하고 예외처리를 위한 작업을 하는 코드 -> API로부터 환율정보를 가져오는 코드의 기본 틀 (변경되지 않는 성질)
    // 2. API를 실행하고, 서버로부터 받은 응답을 가져오는 코드 -> API를 호출하는 기술과 방법이 변경될 수 있음 (변경될려고 하는 성질)
    // 3. JSON 문자열을 파싱하고 필요한 환율정보를 추출하는 코드 -> API 응답의 JSON 구조에 따라 정보를 추출하는 방식이 변경 (변경될려고 하는 성질)

//    private final ApiTemplate apiTemplate = new ApiTemplate(); // 분리된 템플릿 클래스
    private final ApiTemplate apiTemplate; // 분리된 템플릿 클래스

    public WebApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    // 클라이언트 부분 : 콜백을 만들어서 파라미터로 넘겨 템플릿의 메서드를 호출
    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/USD" + currency;

//        return apiTemplate.getForExRate(url, new HttpClientApiExecutor(),new ErApiExRateExtractor()); // SimpleApiExecutor가 콜백, ExRateExtractor 람다식 -> 콜백
        return apiTemplate.getForExRate(url); // 디폴트 콜백
    }

    // 고정된 틀(템플릿) -> 재사용 할려고
    // 잘 변하지 않는 코드들을 가지고 있음
    // 변하는 속성들을 가진 코드는 콜백 형태로 만들어서 템플릿의 메서드 파라미터로 전달
//    private static BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
//        URI uri;
//        try {
//            uri = new URI(url);
//        } catch (URISyntaxException e) { // CheckedException -> unCheckedException
//            throw new RuntimeException(e);
//        }
//
//
//        String response;
//        try {
//            response = apiExecutor.execute(uri);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            return exRateExtractor.exrtract(response);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
