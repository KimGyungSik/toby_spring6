package tobyspring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import tobyspring.order.Order;
import tobyspring.order.OrderRepository;

import java.math.BigDecimal;

public class JpaOrderRepository implements OrderRepository {

    //    private final EntityManagerFactory emf;

    //    public OrderRepository(EntityManagerFactory emf) {
//        this.emf = emf;
//    }
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Order order) {
        entityManager.persist(order);
    }






    // 템플릿으로 만들기 좋은 코드
    // JPA가 내부에서 동작하는 방식의 코드
//    public void save(Order order) {
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//
//        try {
//            em.persist(order); // 변하는 부분
//            em.flush();
//
//            transaction.commit();
//        }
//        catch (RuntimeException e) {
//            if (transaction.isActive()) transaction.rollback();
//            throw e;
//        }
//        finally {
//            if(em.isOpen()) em.close();
//        }
//    }
}
