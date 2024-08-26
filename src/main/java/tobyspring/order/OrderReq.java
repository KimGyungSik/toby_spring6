package tobyspring.order;

import java.math.BigDecimal;

public record OrderReq(String no, BigDecimal total) {
    // 자동으로 생성자처럼 두값이 iv로 생성됨
    // getter생성하지 않아도됨 iv이름으로 된 메서드로 값 꺼내올 수 있음
//    reqs.forEach(req -> {
//        createOrder(req.no(), req.total());
//    });
}
