package edu.miu.cs.cs544.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.miu.cs.cs544.domain.Address;
import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.dto.request.AdminRequest;
import edu.miu.cs.cs544.dto.request.CustomerRequest;
import edu.miu.cs.cs544.dto.response.AdminResponse;
import edu.miu.cs.cs544.dto.response.CustomerResponse;
import edu.miu.cs.cs544.repository.CustomerRepository;
import edu.miu.cs.cs544.repository.UserRepository;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressService addressService;

    public AdminResponse addAdminUser(AdminRequest request) {
        String userName = request.getUserName();
        if (userRepository.existsByUserName(userName))
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "User (username='%s') already exists".formatted(userName));
        User user = AdminRequest.to(request);
        user = userRepository.save(user);
        AdminResponse userResponse = AdminResponse.to(user);
        return userResponse;
    }

    public CustomerResponse addCustomerUser(CustomerRequest request) {
        String userName = request.getUserName();
        if (userRepository.existsByUserName(userName))
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "User (username='%s') already exists".formatted(userName));
        Customer customer = CustomerRequest.to(request);
        Address billingAddress = addressService.createAddress(request.getBillingAddress());
        customer.setBillingAddress(billingAddress);
        Address physicAddress = addressService.createAddress(request.getPhysicalAddress());
        customer.setPhysicalAddress(physicAddress);
        customer = customerRepository.save(customer);
        User user = CustomerRequest.toUser(request);
        user.setCustomer(customer);
        customer.setUser(user);
        user = userRepository.save(user);
        CustomerResponse customerResponse = CustomerResponse.from(customer);
        return customerResponse;
    }

}
