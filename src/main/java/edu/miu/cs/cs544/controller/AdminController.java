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

import edu.miu.cs.cs544.dto.request.AdminRequest;
import edu.miu.cs.cs544.dto.response.AdminResponse;
import edu.miu.cs.cs544.service.AdminService;

@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<AdminResponse> response = adminService.getAll();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> create(
            @Validated @RequestBody AdminRequest request) {
        AdminResponse response = adminService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable int id) {
        AdminResponse response = adminService.getById(id);
        return ResponseEntity.ok().body(response);
    }
}
