package by.brel.rest.api.v1.controller;

import by.brel.entity.Customer;
import by.brel.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CustomerRESTController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(@RequestParam(defaultValue = "1") int page){
        return customerService.getAllCustomers(page);
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") long id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping(value = "/customers")
    public Customer saveCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);

        return customer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);

        return customer;
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);

        return "Customer with id " + id + " was deleted";
    }
}
