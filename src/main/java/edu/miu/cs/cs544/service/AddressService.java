package edu.miu.cs.cs544.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.miu.cs.cs544.domain.Address;
import edu.miu.cs.cs544.domain.State;
import edu.miu.cs.cs544.dto.request.AddressRequest;
import edu.miu.cs.cs544.repository.StateRepository;

@Service
public class AddressService {
    @Autowired
    private StateRepository stateRepository;

    public State getStateByCode(String code) {
        return stateRepository.findByCode(code);
    }

    public Address createAddress(AddressRequest request) {
        Address address = AddressRequest.to(request);
        State state = stateRepository.findByCode(request.getStateCode());
        if (state == null)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Address Not Found");
        address.setState(state);
        return address;
    }
}
