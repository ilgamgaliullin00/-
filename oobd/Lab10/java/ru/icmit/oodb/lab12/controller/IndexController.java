package ru.icmit.oodb.lab12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.icmit.oodb.lab12.repository.CustomerRepository;
import ru.icmit.oodb.lab12.repository.OrderRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping("/")
    public String index(HttpServletRequest request,
                        @ModelAttribute("model") ModelMap model) {
        String path = request.getContextPath();
        model.addAttribute("app_path", path);
        return "/index";
    }
}
