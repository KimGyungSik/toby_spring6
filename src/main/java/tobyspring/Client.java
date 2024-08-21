package tobyspring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import tobyspring.payment.Payment;
import tobyspring.payment.PaymentService;

import java.io.IOException;
import java.math.BigDecimal;

@Component
public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
//        PaymentConfig objectFactory = new PaymentConfig();
//        PaymentService paymentService = objectFactory.paymentService();
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment1 = paymentService.prepare(100L,"USD", BigDecimal.valueOf(50.7));
        System.out.println("Payment1:"+ payment1);
//        System.out.println("---------------------------\n");
//
//        TimeUnit.SECONDS.sleep(1);
//
//        Payment payment2 = paymentService.prepare(100L,"USD", BigDecimal.valueOf(50.7));
//        System.out.println("Payment2:"+ payment2);
//        System.out.println("---------------------------\n");
//
//        TimeUnit.SECONDS.sleep(2);
//
//        Payment payment3 = paymentService.prepare(100L,"USD", BigDecimal.valueOf(50.7));
//        System.out.println("Payment3:"+ payment3);
    }
}
