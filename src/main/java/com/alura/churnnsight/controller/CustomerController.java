package com.alura.churnnsight.controller;


import com.alura.churnnsight.dto.consult.DataAccountDetail;
import com.alura.churnnsight.dto.consult.DataCustomerDetail;
import com.alura.churnnsight.dto.consult.DataCustomerStatusDetail;
import com.alura.churnnsight.dto.consult.DataProductDetail;
import com.alura.churnnsight.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<DataCustomerDetail> getCustomer(@PathVariable String customerId){

       var customer = customerService.getCustomer(customerId);
       return ResponseEntity.ok(customer);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DataCustomerDetail>> getCustomers(@PageableDefault(size = 20) Pageable pageable) {
        var page = customerService.getCustomers(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{customerId}/products")
    public ResponseEntity<Page<DataProductDetail>> getCustomerProducts(@PathVariable String customerId, @PageableDefault(size = 20) Pageable pageable) {
        var page = customerService.getCustomerProducts(customerId, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{customerId}/status")
    public ResponseEntity<DataCustomerStatusDetail> getCustomerStatus(@PathVariable String customerId) {
        var customerStatus = customerService.getCustomerStatus(customerId);
        return ResponseEntity.ok(customerStatus);
    }
    @GetMapping("/{customerId}/accounts")
    public ResponseEntity<Page<DataAccountDetail>> getCustomerAccounts(@PathVariable String customerId, @PageableDefault(size = 20) Pageable pageable) {
        var page = customerService.getCustomerAccounts(customerId, pageable);
        return ResponseEntity.ok(page);
    }

}
