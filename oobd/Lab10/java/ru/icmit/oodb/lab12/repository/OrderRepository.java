package ru.icmit.oodb.lab12.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.icmit.oodb.lab12.domain.Orders;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Component
public class OrderRepository {
    @PersistenceContext(name = "entityManagerFactory")
    protected EntityManager entityManager;


    @Transactional
    public Orders save(Orders orders) {
        if (orders != null && orders.getId() != null) {
            orders = entityManager.merge(orders);
        } else {
            entityManager.persist(orders);
        }
        return orders;
    }

    @Transactional
    public void remove(Orders orders) {
        entityManager.remove(orders);
    }
}
