package edu.miu.cs.cs544.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.miu.cs.cs544.domain.Address;
import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;
import edu.miu.cs.cs544.dto.request.AdminRequest;
import edu.miu.cs.cs544.dto.request.CustomerRequest;
import edu.miu.cs.cs544.dto.response.AdminResponse;
import edu.miu.cs.cs544.dto.response.CustomerResponse;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.UserRepository;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class AdminService {
    @Autowired
    UserRepository userRepository;

    public AdminResponse create(AdminRequest request) {
        String userName = request.getUserName();
        if (userRepository.existsByUserName(userName))
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "User (username='%s') already exists".formatted(userName));
        User user = AdminRequest.to(request);
        user = userRepository.save(user);
        AdminResponse userResponse = AdminResponse.from(user);
        return userResponse;
    }

    public AdminResponse getByName(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Admin (username='%s') not found".formatted(userName));
        if (user.getType() != UserType.ADMIN)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User (username='%s') is not an admin".formatted(userName));
        AdminResponse userResponse = AdminResponse.from(user);
        return userResponse;
    }

    public List<AdminResponse> getAll() {
        return userRepository.findAllByType(UserType.ADMIN).stream().map(AdminResponse::from)
                .toList();
    }

    public AdminResponse getById(int id) {
        return userRepository.findById(id)
                .filter(e -> e.getType() == UserType.ADMIN)
                .map(AdminResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Admin (id='%d') not found".formatted(id)));
    }
}
