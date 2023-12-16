package edu.miu.cs.cs544.dto.request;

import edu.miu.cs.cs544.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    private String line1;
    private String line2;
    private String city;
    private String postalCode;
    private String stateCode;

    public static Address to(AddressRequest request) {
        Address address = new Address();
        address.setLine1(request.getLine1());
        address.setLine2(request.getLine2());
        address.setCity(request.getCity());
        address.setPostalCode(request.getPostalCode());
        return address;
    }
}
