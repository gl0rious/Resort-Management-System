package edu.miu.cs.cs544.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.miu.cs.cs544.dto.request.CustomerRequest;
import edu.miu.cs.cs544.dto.response.CustomerResponse;
import edu.miu.cs.cs544.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<CustomerResponse> response = customerService.getAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> create(
            @Validated @RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable int id) {
        CustomerResponse response = customerService.getById(id);
        return ResponseEntity.ok(response);
    }
}
