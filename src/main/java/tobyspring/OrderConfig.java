package tobyspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import tobyspring.data.JdbcOrderRepository;
import tobyspring.data.JpaOrderRepository;
import tobyspring.order.OrderRepository;
import tobyspring.order.OrderService;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class) // DataConfig에 있는 설정 빈들도 같이 가져올 수 있음
public class OrderConfig {
//    @Bean
//    public OrderRepository orderRepository() {
//        return new JpaOrderRepository();
//    }
@Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }

    @Bean
    public OrderService orderService(PlatformTransactionManager transactionManager,OrderRepository orderRepository) {
        return new OrderService(orderRepository,transactionManager);
    }
}
