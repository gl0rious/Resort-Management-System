package edu.miu.cs.cs544.dto.response;

import edu.miu.cs.cs544.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private AddressResponse physicalAddress;
    private AddressResponse billingAddress;

    public static CustomerResponse from(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setUserName(customer.getUser().getUserName());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());
        response.setPhysicalAddress(AddressResponse.from(customer.getPhysicalAddress()));
        response.setBillingAddress(AddressResponse.from(customer.getBillingAddress()));
        return response;
    }
}