package com.nick.spring.rest;

import com.nick.spring.entity.Customer;
import com.nick.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {

        return customerService.getCustomers();

    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId){


        Customer customer = customerService.getCustomer(customerId);

        if(customer == null){
            throw new CustomerNotFoundException("Customer id not found - " + customerId);
        }

        return customer;
    }


    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer){

        // force to save a new item, insert new one
        // if someone would put an id into it
        customer.setId(0);
        //
        customerService.saveCustomer(customer);

        return customer;
    }

    //update an existing customer

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer){
        
        customerService.saveCustomer(customer);

        return customer;
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId){

        Customer customer = customerService.getCustomer(customerId);

        if(customer == null){
            throw new CustomerNotFoundException("Customer id not found - " + customerId);
        }

        customerService.deleteCustomer(customerId);

        return "Deleted customer. Id - " + customerId;
    }


}
