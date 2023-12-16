package edu.miu.cs.cs544.dto.request;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String userName;
    private String userPass;
    private String firstName;
    private String lastName;
    private String email;
    private AddressRequest physicalAddress;
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