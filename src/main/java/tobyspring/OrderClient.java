package tobyspring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.order.Order;
import tobyspring.order.OrderService;
import tobyspring.order.OrderServiceImpl;

import java.math.BigDecimal;

public class OrderClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderService orderService = beanFactory.getBean(OrderService.class);

        Order order = orderService.createOrder("0100", BigDecimal.TEN);
        System.out.println(order);




//        try {
//            new TransactionTemplate(transactionManager).execute((TransactionCallback<Order>) status -> {
//                Order order = new Order("100", BigDecimal.TEN);
//                repository.save(order);
//
//                System.out.println(order);
//
//                return null;
//            });
//        }
//        catch (DataIntegrityViolationException e) {
//            System.out.println("주문번호 중복 복구 작업");
//        }




//        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);
// 2)
        // transaction begin
//        Order order = new Order("100", BigDecimal.TEN);
//        repository.save(order);
//
//        System.out.println(order);
//
//        try {
//            Order order2 = new Order("100", BigDecimal.ONE);
//            repository.save(order2);
//        }
//        catch (RuntimeException e) {
//            e.printStackTrace();
//            throw e;
//        }
        // commit




// 1)
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//
//        Order order = new Order("100", BigDecimal.TEN);
//        em.persist(order);
//
//        System.out.println(order);
//
//        em.getTransaction().commit();
//        em.close();
    }
}
