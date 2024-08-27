package tobyspring.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tobyspring.OrderConfig;
import tobyspring.data.JdbcOrderRepository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {
    @Autowired
    OrderService orderService;

    @Autowired
    JdbcOrderRepository jdbcOrderRepository;

    @Autowired
    DataSource dataSource;

    @Test
    void createOrder() {
//        try{
            var order = orderService.createOrder("0100", BigDecimal.ONE);
        System.out.println(order);
            var order2 = orderService.createOrder("0100", BigDecimal.ONE);
        System.out.println(order2);
//        }catch (DataIntegrityViolationException e) {
//            e.printStackTrace();
//        }
//        Assertions.assertThat(order.getId()).isGreaterThan(0); // 0보다 큰값인가 ?
    }

    @Test
    void duplicatedTest() {
        try {
            var order = orderService.createOrder("500", BigDecimal.ONE);
            jdbcOrderRepository.save(order);
            System.out.println(order.getId());

            var order2 = orderService.createOrder("", BigDecimal.ONE);
            jdbcOrderRepository.save(order2);
            System.out.println(order.getId());
        }catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            System.out.println("테스트 성공");
        }
    }

    @Test
    void createOrders() {
        List<OrderReq> orderReqs = List.of(
                new OrderReq("0200", BigDecimal.ONE),
                new OrderReq("0201", BigDecimal.TEN)
        );

        var orders = orderService.createOrders(orderReqs);

        Assertions.assertThat(orders).hasSize(2);
        orders.forEach(order ->
            Assertions.assertThat(order.getId()).isGreaterThan(0));
    }

    @Test
    void createDuplicatedOrders() {
        List<OrderReq> orderReqs = List.of(
                new OrderReq("0300", BigDecimal.ONE),
                new OrderReq("0300", BigDecimal.TEN)
        );

//        DuplicateKeyException가 DataIntegrityViolationException를 상속하고 있음
        assertThatThrownBy(() -> orderService.createOrders(orderReqs)).isInstanceOf(DataIntegrityViolationException.class);

        JdbcClient client = JdbcClient.create(dataSource);
        var count = client.sql("select count(*) from orders where no='0300'").query(Long.class).single();
        Assertions.assertThat(count).isEqualTo(0);
    }
}
