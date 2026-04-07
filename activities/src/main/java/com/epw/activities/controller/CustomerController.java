package com.epw.activities.controller;

import com.epw.activities.dto.CreateCustomerRequest;
import com.epw.activities.dto.CustomerResponse;
import com.epw.activities.dto.UpdateCustomerRequest;
import com.epw.activities.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(@Valid @RequestBody CreateCustomerRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<CustomerResponse> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PatchMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id, @Valid @RequestBody UpdateCustomerRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
