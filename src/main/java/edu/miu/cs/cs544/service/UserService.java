// package edu.miu.cs.cs544.service;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.stereotype.Service;
// import org.springframework.web.server.ResponseStatusException;

// import edu.miu.cs.cs544.domain.Address;
// import edu.miu.cs.cs544.domain.Customer;
// import edu.miu.cs.cs544.domain.User;
// import edu.miu.cs.cs544.domain.UserType;
// import edu.miu.cs.cs544.dto.request.AdminRequest;
// import edu.miu.cs.cs544.dto.request.CustomerRequest;
// import edu.miu.cs.cs544.dto.response.AdminResponse;
// import edu.miu.cs.cs544.dto.response.CustomerResponse;
// import edu.miu.cs.cs544.repository.CustomerRepository;
// import edu.miu.cs.cs544.repository.UserRepository;
// import jakarta.transaction.Transactional;

// @Transactional
// @Service
// public class UserService {
// @Autowired
// UserRepository userRepository;
// @Autowired
// CustomerRepository customerRepository;

// @Autowired
// AddressService addressService;

// public AdminResponse createAdminUser(AdminRequest request) {
// String userName = request.getUserName();
// if (userRepository.existsByUserName(userName))
// throw new ResponseStatusException(HttpStatus.CONFLICT,
// "User (username='%s') already exists".formatted(userName));
// User user = AdminRequest.to(request);
// user = userRepository.save(user);
// AdminResponse userResponse = AdminResponse.from(user);
// return userResponse;
// }

// public CustomerResponse createCustomerUser(CustomerRequest request) {
// String userName = request.getUserName();
// if (userRepository.existsByUserName(userName))
// throw new ResponseStatusException(HttpStatus.CONFLICT,
// "User (username='%s') already exists".formatted(userName));
// Customer customer = CustomerRequest.to(request);
// Address billingAddress =
// addressService.createAddress(request.getBillingAddress());
// customer.setBillingAddress(billingAddress);
// Address physicAddress =
// addressService.createAddress(request.getPhysicalAddress());
// customer.setPhysicalAddress(physicAddress);
// customer = customerRepository.save(customer);
// User user = CustomerRequest.toUser(request);
// user.setCustomer(customer);
// customer.setUser(user);
// user = userRepository.save(user);
// CustomerResponse customerResponse = CustomerResponse.from(customer);
// return customerResponse;
// }

// public AdminResponse getAdmin(String userName) {
// User user = userRepository.findByUserName(userName);
// if (user == null)
// throw new ResponseStatusException(HttpStatus.NOT_FOUND,
// "Admin (username='%s') not found".formatted(userName));
// if (user.getType() != UserType.ADMIN)
// throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
// "User (username='%s') is not an admin".formatted(userName));
// AdminResponse userResponse = AdminResponse.from(user);
// return userResponse;
// }

// public CustomerResponse getCustomer(String userName) {
// User user = userRepository.findByUserName(userName);
// if (user == null)
// throw new ResponseStatusException(HttpStatus.NOT_FOUND,
// "Customer (username='%s') not found".formatted(userName));
// if (user.getType() != UserType.CLIENT)
// throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
// "User (username='%s') is not a customer".formatted(userName));
// Customer customer = user.getCustomer();
// CustomerResponse customerResponse = CustomerResponse.from(customer);
// return customerResponse;
// }

// public List<AdminResponse> getAllAdminUsers() {
// return
// userRepository.findAllByUserType(UserType.ADMIN).stream().map(AdminResponse::from)
// .toList();
// }

// public List<CustomerResponse> getAllCustomers() {
// return
// customerRepository.findAll().stream().map(CustomerResponse::from).toList();
// }

// public CustomerResponse getById(int id) {
// return customerRepository.findById(id).map(CustomerResponse::from)
// .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
// "Customer (id='%d') not found".formatted(id)));
// }
// }
