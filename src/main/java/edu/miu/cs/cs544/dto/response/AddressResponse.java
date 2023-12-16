package edu.miu.cs.cs544.dto.response;

import edu.miu.cs.cs544.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private String line1;
    private String line2;
    private String city;
    private String postalCode;
    private String stateCode;
    private String stateName;
    private String countryCode;
    private String countryName;

    public static AddressResponse from(Address address) {
        AddressResponse response = new AddressResponse();
        response.setLine1(address.getLine1());
        response.setLine2(address.getLine2());
        response.setCity(address.getCity());
        response.setPostalCode(address.getPostalCode());
        response.setStateCode(address.getState().getCode());
        response.setStateName(address.getState().getName());
        response.setCountryCode(address.getState().getCountry().getCode());
        response.setCountryName(address.getState().getCountry().getName());
        return response;
    }
}