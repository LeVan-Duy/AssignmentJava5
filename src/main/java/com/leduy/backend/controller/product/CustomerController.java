package com.leduy.backend.controller.product;

import com.leduy.backend.dto.request.CustomerRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.CustomerResponse;
import com.leduy.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> paginationColor(PaginationRequest paginationRequest) {
        ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(
                customerService.paginationCustomer(paginationRequest), HttpStatus.OK);
        return response;
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<CustomerResponse> getColorById(@PathVariable String id) {
        CustomerResponse customerResponse = customerService.findById(id);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<CustomerResponse> saveColor(@RequestBody CustomerRequest CustomerRequest) {
        CustomerResponse customerResponse = customerService.save(CustomerRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CustomerResponse> updateColor(@PathVariable("id") String id,
                                                        @RequestBody CustomerRequest customerRequest) {
        customerRequest.setId(id);
        CustomerResponse colorResponse = customerService.update(customerRequest);
        return new ResponseEntity<>(colorResponse, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable String id) {
        boolean deleted = customerService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
