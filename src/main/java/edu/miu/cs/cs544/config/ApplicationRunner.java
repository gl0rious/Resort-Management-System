package edu.miu.cs.cs544.config;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;
import edu.miu.cs.cs544.dto.request.AddressRequest;
import edu.miu.cs.cs544.dto.request.CustomerRequest;
import edu.miu.cs.cs544.repository.UserRepository;
import edu.miu.cs.cs544.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("sanjeev", encoder.encode("password"), UserType.ADMIN));
        userRepository.save(new User("majid", encoder.encode("password"), UserType.ADMIN));
        userRepository.save(new User("mahmud", encoder.encode("password"), UserType.ADMIN));
        userRepository.save(new User("nasa", encoder.encode("password"), UserType.ADMIN));
        userRepository.save(new User("emanuel", encoder.encode("password"), UserType.ADMIN));

//        AddressRequest addressRequest = new AddressRequest("1000 N 4th St", null, "Fairfield", "52557", "IA");
//        CustomerRequest customerRequest = new CustomerRequest("john", "password", "John", "Doe", "johndoe@est.com", addressRequest, addressRequest);
//        customerService.create(customerRequest);
    }
}
