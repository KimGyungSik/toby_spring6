package tobyspring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.aspectj.weaver.ast.Or;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import tobyspring.data.OrderRepository;
import tobyspring.order.Order;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

        try {
            new TransactionTemplate(transactionManager).execute((TransactionCallback<Order>) status -> {
                Order order = new Order("100", BigDecimal.TEN);
                repository.save(order);

                System.out.println(order);

                Order order2 = new Order("100", BigDecimal.ONE);
                repository.save(order2);

                return null;
            });
        }
        catch (DataIntegrityViolationException e) {
            System.out.println("주문번호 중복 복구 작업");
        }









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
