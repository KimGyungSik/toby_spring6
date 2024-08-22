package tobyspring.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

public interface ExRateExtractor {
    BigDecimal exrtract(String response) throws JsonProcessingException;
}
