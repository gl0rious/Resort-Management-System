package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.dto.response.WelcomeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @RequestMapping("/")
    public ResponseEntity<WelcomeResponse> welcome() {
        return ResponseEntity.ok(new WelcomeResponse(true, "Welcome to Resort Management System"));
    }

}
