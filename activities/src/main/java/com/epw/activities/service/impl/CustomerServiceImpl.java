package com.epw.activities.service.impl;

import com.epw.activities.dto.CreateCustomerRequest;
import com.epw.activities.dto.CustomerResponse;
import com.epw.activities.dto.UpdateCustomerRequest;
import com.epw.activities.entity.Customer;
import com.epw.activities.exception.ResourceNotFoundException;
import com.epw.activities.repository.CustomerRepository;
import com.epw.activities.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerResponse create(CreateCustomerRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use: " + request.getEmail());
        }
        Customer c = new Customer();
        c.setFullName(request.getFullName());
        c.setEmail(request.getEmail());
        c.setPhone(request.getPhone());
        return toResponse(repository.save(c));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> list() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse getById(Long id) {
        Customer c = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer " + id + " not found"));
        return toResponse(c);
    }

    @Override
    public CustomerResponse update(Long id, UpdateCustomerRequest request) {
        Customer c = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer " + id + " not found"));

        if (request.getFullName() != null) {
            c.setFullName(request.getFullName());
        }
        if (request.getEmail() != null) {
            if (!request.getEmail().equals(c.getEmail()) && repository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("Email already in use: " + request.getEmail());
            }
            c.setEmail(request.getEmail());
        }
        if (request.getPhone() != null) {
            c.setPhone(request.getPhone());
        }

        return toResponse(repository.save(c));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Customer " + id + " not found");
        }
        repository.deleteById(id);
    }

    private CustomerResponse toResponse(Customer c) {
        CustomerResponse r = new CustomerResponse();
        r.setId(c.getId());
        r.setFullName(c.getFullName());
        r.setEmail(c.getEmail());
        r.setPhone(c.getPhone());
        r.setCreatedAt(c.getCreatedAt());
        return r;
    }
}
