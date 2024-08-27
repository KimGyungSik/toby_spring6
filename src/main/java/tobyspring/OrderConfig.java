package tobyspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tobyspring.data.JdbcOrderRepository;
import tobyspring.order.OrderRepository;
import tobyspring.order.OrderService;
import tobyspring.order.OrderServiceImpl;
//import tobyspring.order.OrderServiceTxProxy;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class) // DataConfig에 있는 설정 빈들도 같이 가져올 수 있음
@EnableTransactionManagement
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
    public OrderService orderService(PlatformTransactionManager transactionManager, OrderRepository orderRepository) {
//        return new OrderServiceTxProxy(new OrderServiceImpl(orderRepository),transactionManager);
        return new OrderServiceImpl(orderRepository);
    }
}
