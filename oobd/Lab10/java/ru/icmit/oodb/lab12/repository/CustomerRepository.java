package ru.icmit.oodb.lab12.repository;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.icmit.oodb.lab12.domain.Customer;
import ru.icmit.oodb.lab12.domain.Orders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class CustomerRepository {
    @PersistenceContext(name = "entityManagerFactory")
    protected EntityManager entityManager;

    public List<Customer> findAll() {
        Query query = entityManager.createQuery(
                "select c from Customer c ", Customer.class
        );
        List<Customer> customers = query.getResultList();
        return customers;
    }

    public Customer findByName(String name) {
        Query queryName = entityManager.createQuery(
                "select c from Customer c where c.name like :name ", Customer.class
        );
        queryName.setParameter("name", name);

        Query querySurname = entityManager.createQuery(
                "select c from Customer c where c.surname like :name ", Customer.class
        );
        querySurname.setParameter("name", name);

        List<Customer> customers = queryName.getResultList();
        customers.addAll(querySurname.getResultList());

        Customer customer = null;
//        customer.setId(null);
//        customer.setName("Нет");
//        customer.setSurname("Пользователя");
//        customer.setCompanyNumber(-1);
        if (customers.size() > 0) {
            customer = customers.get(0);
        }

        return customer;
    }


    public Customer findByOrderId( Long id ) {

        Query query = entityManager.createQuery(
                "select c from Customer c JOIN c.orders a where  a.id = :id ", Customer.class);
        query.setParameter("id", id);
        try {
            return (Customer) query.getSingleResult();
        } catch (Exception e) {}

        return null;
    }

    public List<Orders> getCustomerOrders(Long customerId) {
        Query query = entityManager.createQuery(
                "select t from Customer c JOIN c.orders t where c.id = :id ", Orders.class
        );
        query.setParameter("id", customerId);
        return query.getResultList();
    }

    public List<Orders> getCustomerOrders(Customer customer) {
        Query query = entityManager.createQuery(
                "select t from Customer c JOIN c.orders t where c = :customer ", Orders.class
        );
        query.setParameter("customer", customer);
        return query.getResultList();
    }

    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }


    @Transactional
    public boolean removeCustomer(Long id) {
        Customer customer = entityManager.find(Customer.class, id);

        if (customer == null) return false;

        entityManager.remove(customer);

        return true;
    }

    @Transactional
    public boolean removeOrder(Long id) {
        Orders orders = entityManager.find(Orders.class, id);

        if (orders == null) return false;

        entityManager.remove(orders);

        return true;
    }

    @Transactional
    public boolean removeCustomerOrder(Long customerid, Long orderid) {

        Customer customer = entityManager.find(Customer.class, customerid);


        if (customer == null) return false;

        List<Orders> orders = customer.getOrders();


        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId().equals(orderid)) {
                orders.remove(i);
            }
        }



        customer.setOrders(orders);

        entityManager.merge(customer);

        return true;
    }

    // Удалить все заказы клиента по его ID
    @Transactional
    public boolean removeCustomerAllOrders(Long id) {
        Customer customer = entityManager.find(Customer.class, id);

        if (customer == null) return false;

        List<Orders> orders = customer.getOrders();

        orders.clear();

        customer.setOrders(orders);

        entityManager.merge(customer);

        return true;
    }

    @Transactional
    public Customer save(Customer customer) {
        if (customer == null) return null;
        if (customer.getId() != null) {
            customer = entityManager.merge(customer);
        } else {
            entityManager.persist(customer);
        }
        return customer;
    }
}