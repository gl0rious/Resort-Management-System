package edu.miu.cs.cs544.dto.request;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String userPass;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotNull
    private AddressRequest physicalAddress;
    @NotNull
    private AddressRequest billingAddress;

    public static Customer to(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        // customer.setPhysicalAddress(AddressRequest.to(request.getPhysicalAddress()));
        // customer.setBillingAddress(AddressRequest.to(request.getBillingAddress()));
        return customer;
    }

    public static User toUser(CustomerRequest request) {
        User user = new User(request.getUserName(),
                request.getUserPass(), UserType.CLIENT);
        return user;
    }

}