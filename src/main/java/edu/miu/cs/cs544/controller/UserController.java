package edu.miu.cs.cs544.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.cs.cs544.dto.request.AdminRequest;
import edu.miu.cs.cs544.dto.request.CustomerRequest;
import edu.miu.cs.cs544.dto.response.AdminResponse;
import edu.miu.cs.cs544.dto.response.CustomerResponse;
import edu.miu.cs.cs544.service.UserService;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/admins")
    public ResponseEntity<?> addAdminUser(
            @Validated @RequestBody AdminRequest request) {
        AdminResponse userResponse = userService.addAdminUser(request);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/customers")
    public ResponseEntity<?> addCustomerUser(
            @Validated @RequestBody CustomerRequest request) {
        CustomerResponse userResponse = userService.addCustomerUser(request);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}
