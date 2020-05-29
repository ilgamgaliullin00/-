package ru.icmit.oodb.lab12.controller;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.icmit.oodb.lab12.domain.Customer;

import ru.icmit.oodb.lab12.domain.Orders;
import ru.icmit.oodb.lab12.repository.CustomerRepository;
import ru.icmit.oodb.lab12.repository.OrderRepository;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping("/customer")
    public String customers(HttpServletRequest request, @ModelAttribute("model") ModelMap model) {
        String path = request.getContextPath();
        model.addAttribute("app_path", path);

        List<Customer> customers = repository.findAll();
        model.addAttribute("customers", customers);
        return "/customers";
    }

    @RequestMapping("/customerbyname")
    public String customerByName(HttpServletRequest request,
                                 @RequestParam(value = "name", required = false) String name,
                                 @ModelAttribute("model") ModelMap model) {
        String path = request.getContextPath();
        model.addAttribute("app_path", path);

        if (name != null) {
            Customer customer = repository.findByName(name);
            System.out.println(customer);
            model.addAttribute("customer", customer);
        } else {
            model.addAttribute("customer", null);
        }

        return "/customerbyname";
    }

    @RequestMapping("/addcustomer")
    public String addCustomer(HttpServletRequest request,
                              @RequestParam(value = "id", required = false) Long id,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "surname", required = false) String surname,
                              @RequestParam(value = "companyNumber", required = false) Integer companyNumber,
                              @ModelAttribute("model") ModelMap model) {
        String path  = request.getContextPath();
        model.addAttribute("app_path", path);

        Customer customer = null;
        if (id != null) {
            customer = repository.findById(id);
        }
        if (customer == null) {
            customer = new Customer();
        }
        if (name != null || surname != null || companyNumber != null) {
            customer.setName(name);
            customer.setSurname(surname);
            customer.setCompanyNumber(companyNumber);
            customer = repository.save(customer);
        }
        if (customer.getId() != null) {
            List<Orders> orders = repository.getCustomerOrders(customer);
            model.addAttribute("orders", orders);
        }

        model.addAttribute("customer", customer);
        return "/customer";
    }

    @RequestMapping("/deleteorder")
    @Transactional
    public String deleteCustomerOrders(HttpServletRequest request,
                                       @RequestParam("orderid") Long orderid,
                                       @ModelAttribute("model") ModelMap model) {

        String path = request.getContextPath();

        model.addAttribute("app_path", path);

        Customer customer = repository.findByOrderId( orderid );


        if (customer != null && customer.getId() != null) {
            List<Orders> orders = repository.getCustomerOrders(customer);

            for(int i=0 ; i<orders.size();i++){
                if(orders.get(i).getId().equals(orderid)){
                    orders.remove(i);
                    break;
                }
            }

            repository.removeCustomerOrder(customer.getId(),orderid);
            model.addAttribute("orders", orders);
        }


        model.addAttribute("customer", customer);
        return "/customer";
    }
}
