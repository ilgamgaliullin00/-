package ru.icmit.oodb.lab12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.icmit.oodb.lab12.domain.Customer;
import ru.icmit.oodb.lab12.domain.Orders;
import ru.icmit.oodb.lab12.repository.CustomerRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DeleteObjectController {

    @Autowired
    private CustomerRepository repository;

    @RequestMapping("/customer/delete")
    public String deleteCustomer(HttpServletRequest request,
                                 @RequestParam("customerid") Long customerid,
                                 @ModelAttribute("model") ModelMap model) {

        String path = request.getContextPath();

        model.addAttribute("app_path", path);

        repository.removeCustomer(customerid);
        return "/delobject";
    }

    @RequestMapping("/customer/deleteorder")
    public String deleteOrder(HttpServletRequest request,
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
            repository.removeOrder(orderid);

        }else repository.removeOrder(orderid);


        return "/delobject";
    }

    @RequestMapping("/customer/deleteallorders")
    public String deleteAllAccounts(HttpServletRequest request,
                                    @RequestParam("customerid") Long id,
                                    @ModelAttribute("model") ModelMap model) {

        String path = request.getContextPath();

        model.addAttribute("app_path", path);

        repository.removeCustomerAllOrders(id);
        return "/delobject";
    }


    @RequestMapping("/delobject")
    public String delObject(HttpServletRequest request,
                            @ModelAttribute("model") ModelMap model) {

        String path = request.getContextPath();

        model.addAttribute("app_path", path);


        return "/delobject";
    }
}