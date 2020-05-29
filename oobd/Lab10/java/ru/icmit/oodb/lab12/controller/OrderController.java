package ru.icmit.oodb.lab12.controller;

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
public class OrderController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping("/addorder")
    @Transactional
    public String customerByName(HttpServletRequest request,
                                 @RequestParam(value = "id", required = false) Long id,
                                 @RequestParam(value = "name", required = false) String name,
                                 @ModelAttribute("model")ModelMap model) {
        String path = request.getContextPath();
        model.addAttribute("app_path", path);

        Customer customer = null;
        if (id != null) {
            customer = customerRepository.findById(id);

            if (customer != null) {
                Orders orders = new Orders();
                orders.setOrderName(name);
                orderRepository.save(orders);

                customer.getOrders().add(orders);
                customerRepository.save(customer);
            }

            List<Orders> orders = customerRepository.getCustomerOrders(customer);
            model.addAttribute("orders", orders);
        } else {
            customer = new Customer();
        }
        model.addAttribute("customer", customer);

        return "/customer";
    }

    @RequestMapping("/order/remove")
    @Transactional
    public String customerByName(HttpServletRequest request,
                                 @RequestParam(value = "id", required = false) Long id,
                                 @RequestParam(value = "orderid", required = false) Long orderid,
                                 @ModelAttribute("model") ModelMap model) {
        String path = request.getContextPath();
        model.addAttribute("app_path", path);

        Customer customer = customerRepository.findById(id);

        customerRepository.removeOrder(orderid);

        List<Orders> orders = customer.getOrders();
        model.addAttribute("orders", orders);

        return "/ordertable";
    }
}
