package tobyspring.order;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
//    private final JpaTransactionManager transactionManager;
//    private final PlatformTransactionManager transactionManager;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);

//        return new TransactionTemplate(transactionManager).execute(status -> {
//            this.orderRepository.save(order);
//            return order;
//        });  // JPA는 트랜잭션매니저를 얻어와야 하야 트랜잭션 시작이 가능함
        this.orderRepository.save(order);
        return order; // JDBC이므로 자동커밋이 가능함
    }

    @Override
    public List<Order> createOrders(List<OrderReq> reqs) {
                return reqs.stream().map(req -> createOrder(req.no(), req.total())).toList();
    }
}
