package com.ushan.springBootCRUD.controller;

import com.ushan.springBootCRUD.model.Customer;
import com.ushan.springBootCRUD.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    private final CustomerService customerService;

    public HomeController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String home(Model model) {
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
                       RedirectAttributes redirectAttributes,
                       Model model){

        if (bindingResult.hasErrors()){
            return "create";
        }
        customerService.save(customer);
        redirectAttributes.addFlashAttribute(
                "message",
                "Customer created successfully"
        );
        return "redirect:/";
    }

    @GetMapping("customer/{id}/edit")
    public String edit(@PathVariable long id, Model model){
        Customer customer = customerService.findById(id).orElse(null);
        model.addAttribute("customer", customer);
        return "create";
    }

}
