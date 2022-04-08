package by.brel.controller;

import by.brel.entity.Customer;
import by.brel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/main")
    public String getAllCustomers(Model model){
        List<Customer> customerList = customerService.getAllCustomers();

        model.addAttribute("customers", customerList);

        return "/customers/mainCustomers";
    }

    @GetMapping("/main/{id}")
    public Customer getCustomerById(@PathVariable long id) {
        Customer customer = customerService.findCustomerById(id);

        return customer;
    }

    @GetMapping("/new")
    public String showNewOrUpdateCustomersView(@ModelAttribute("customer") Customer customer) {
        return "/customers/newOrUpdateCustomers";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);

        return "redirect:/customers/main";
    }

    @GetMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") long id, Model model) {
        Customer customer = customerService.findCustomerById(id);

        model.addAttribute("customer", customer);

        return "/customers/newOrUpdateCustomers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);

        return "redirect:/customers/main";
    }
}
