package com.leduy.backend.service.impl;

import com.leduy.backend.dto.request.CustomerRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.CustomerResponse;
import com.leduy.backend.entity.Customer;
import com.leduy.backend.infrastructure.constant.EntityProperties;
import com.leduy.backend.infrastructure.exception.ResourceNotFoundException;
import com.leduy.backend.repository.CustomerRepository;
import com.leduy.backend.service.CustomerService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CustomerResponse save(@Valid CustomerRequest customerRequest) {
        Customer model = mapper.map(customerRequest, Customer.class);
        Customer saveCustomer = customerRepository.save(model);
        CustomerResponse customerResponse = mapper.map(saveCustomer, CustomerResponse.class);
        return customerResponse;

    }

    @Override
    public CustomerResponse update(@Valid CustomerRequest customerRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerRequest.getId());
        if (optionalCustomer.isPresent()) {
            Customer model = optionalCustomer.get();
            model.setFullName(customerRequest.getFullName());
            model.setEmail(customerRequest.getEmail());
            model.setGender(customerRequest.getGender());
            model.setDateOfBirth(customerRequest.getDateOfBirth());
            model.setPassWord(customerRequest.getPassWord());
            model.setId(customerRequest.getId());
            customerRepository.save(model);
            return mapper.map(model, CustomerResponse.class);
        }
        throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
    }

    @Override
    public CustomerResponse findById(String id) {
        Optional<Customer> optionalCustomerResponse = customerRepository.findById(id);
        if (optionalCustomerResponse.isPresent()) {
            return mapper.map(optionalCustomerResponse.get(), CustomerResponse.class);
        }
        throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
    }

    @Override
    public boolean delete(String id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            customerRepository.deleteById(id);
            return true;
        } else if (optionalCustomer.isEmpty()) {
            throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
        }
        return false;
    }

    @Override
    public Map<String, Object> paginationCustomer(PaginationRequest paginationRequest) {
        Pageable pageable = null;
        Map<String, Object> result = new HashMap<>();
        if (paginationRequest.getPage() > 0) {
            pageable = PageRequest.of(paginationRequest.getPage() - 1, paginationRequest.getSize());
        }
        if (paginationRequest.getOrder().equals("asc")) {
            pageable = PageRequest.of(paginationRequest.getPage() - 1, paginationRequest.getSize(), Sort.by(paginationRequest.getField()).ascending());
        }
        if (paginationRequest.getOrder().equals("desc")) {
            pageable = PageRequest.of(paginationRequest.getPage() - 1, paginationRequest.getSize(), Sort.by(paginationRequest.getField()).descending());

        }
        Page<Customer> customerPage = customerRepository.paginationCustomer(pageable, paginationRequest.getSearch());
        List<CustomerResponse> customerResponseList = customerPage
                .toList()
                .stream()
                .map(Customers -> mapper.map(Customers, CustomerResponse.class))
                .collect(Collectors.toList());
        result.put("listCustomer", customerResponseList);
        result.put("totalPage", customerPage.getTotalPages());
        result.put("totalElement", customerPage.getTotalElements());
        result.put("currentPage", paginationRequest.getPage());
        return result;
    }
}
