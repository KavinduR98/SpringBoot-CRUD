package com.ushan.springBootCRUD.controller;

import com.ushan.springBootCRUD.model.Customer;
import com.ushan.springBootCRUD.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final CustomerService customerService;

    public HomeController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String home(@RequestParam(value = "name", defaultValue = "") String name, Model model) {
        List<Customer> customerList = customerService.getAllCustomers();
        model.addAttribute("customerList", customerList);
        return "home";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("customer", new Customer());
        return "create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("customer") Customer customer,
                       BindingResult bindingResult,
                       Model model){

        if (bindingResult.hasErrors()){
            return "/create";
        }
        customerService.save(customer);
        return "redirect:/";
    }

}
