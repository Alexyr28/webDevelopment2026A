package com.epw.activities.service;

import com.epw.activities.dto.CreateCustomerRequest;
import com.epw.activities.dto.CustomerResponse;
import com.epw.activities.dto.UpdateCustomerRequest;

import java.util.List;

public interface CustomerService {
    CustomerResponse create(CreateCustomerRequest request);
    List<CustomerResponse> list();
    CustomerResponse getById(Long id);
    CustomerResponse update(Long id, UpdateCustomerRequest request);
    void delete(Long id);
}
