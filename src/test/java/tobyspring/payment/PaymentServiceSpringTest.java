package tobyspring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.TestPaymentConfig;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class) // 스프링 구성정보를 이용해서 컨테이너의 오브젝트들을 생성후 필요시 Autowired를 통해 주입
class PaymentServiceSpringTest {

//    @Autowired
//    BeanFactory beanFactory;

    @Autowired
    PaymentService paymentService;

    @Autowired
    ExRateProviderStub exRateProviderStub;

    @Autowired
    Clock clock;


    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() throws IOException {
//        BeanFactory beanFactory = new AnnotationConfigApplicationContext(TestPaymentConfig.class);
//        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        // exRate : 1000
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000)); // isEqualByComparingTo() -> BigDecimal에서 권장하는 메서드
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));

        // exRate : 500
        exRateProviderStub.setExRate(valueOf(500));
        Payment payment2 = paymentService.prepare(1L, "USD", TEN);

        assertThat(payment2.getExRate()).isEqualByComparingTo(valueOf(500)); // isEqualByComparingTo() -> BigDecimal에서 권장하는 메서드
        assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));

        // 원화환산금액의 유효시간 계산
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    @Test
    void validUntil() throws IOException {
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        // valid until이 prepare() 30분 뒤로 설정됐는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }
}