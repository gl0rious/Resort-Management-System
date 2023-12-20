package edu.miu.cs.cs544.dto.request;

import edu.miu.cs.cs544.domain.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    @NotBlank
    private String line1;
    @NotBlank
    private String line2;
    @NotBlank
    private String city;
    @NotBlank
    private String postalCode;
    @NotBlank
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
