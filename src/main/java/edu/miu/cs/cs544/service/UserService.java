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

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressService addressService;

    public AdminResponse addAdminUser(AdminRequest request) {
        User user = userRepository.findByUserName(request.getUserName());
        if (user != null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        user = AdminRequest.to(request);
        user = userRepository.save(user);
        AdminResponse userResponse = AdminResponse.to(user);
        return userResponse;
    }

    public CustomerResponse addCustomerUser(CustomerRequest request) {
        User existedUser = userRepository.findByUserName(request.getUserName());
        if (existedUser != null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        Customer customer = CustomerRequest.to(request);
        Address billingAddress = addressService.createAddress(request.getBillingAddress());
        customer.setBillingAddress(billingAddress);
        Address physicAddress = addressService.createAddress(request.getPhysicalAddress());
        customer.setPhysicalAddress(physicAddress);
        customer.getBillingAddress().setState(addressService.getStateByCode(null));
        customer = customerRepository.save(customer);
        User user = CustomerRequest.toUser(request);
        user.setCustomer(customer);
        user = userRepository.save(user);
        CustomerResponse customerResponse = CustomerResponse.from(customer);
        return customerResponse;
    }

}
