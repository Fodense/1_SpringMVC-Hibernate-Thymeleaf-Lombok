package by.brel.rest.api.v1.controller;

import by.brel.entity.Customer;
import by.brel.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Customers.")
public class CustomerRESTController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    @ApiOperation(value = "${customers.controller.method.getAllCustomers}", position = 0)
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    @ApiOperation(value = "${customers.controller.method.getCustomerById}", position = 1)
    public Customer getCustomerById(
            @ApiParam(value = "${customers.controller.method.param.getCustomerById}", required = true, example = "1")
            @PathVariable("id") long id
    ) {
        return customerService.findCustomerById(id);
    }

    @PostMapping(value = "/customers")
    @ApiOperation(value = "${customers.controller.method.saveCustomer}", position = 2)
    public Customer saveCustomer(
            @ApiParam(value = "${customers.controller.method.param.saveCustomer}", required = true)
            @RequestBody Customer customer
    ) {
        customerService.saveCustomer(customer);

        return customer;
    }

    @PutMapping("/customers")
    @ApiOperation(value = "${customers.controller.method.updateCustomer}", position = 3)
    public Customer updateCustomer(
            @ApiParam(value = "${customers.controller.method.param.updateCustomer}", required = true)
            @RequestBody Customer customer
    ) {
        customerService.saveCustomer(customer);

        return customer;
    }

    @DeleteMapping("/customers/{id}")
    @ApiOperation(value = "${customers.controller.method.deleteCustomer}", position = 4)
    public String deleteCustomer(
            @ApiParam(value = "${customers.controller.method.param.deleteCustomer}", required = true, example = "1")
            @PathVariable("id") long id
    ) {
        customerService.deleteCustomer(id);

        return "Customer with id " + id + " was deleted";
    }
}
