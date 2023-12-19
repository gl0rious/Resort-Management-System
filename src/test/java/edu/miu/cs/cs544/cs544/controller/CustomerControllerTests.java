package edu.miu.cs.cs544.cs544.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.miu.cs.cs544.controller.AdminController;
import edu.miu.cs.cs544.controller.CustomerController;
import edu.miu.cs.cs544.domain.Country;
import edu.miu.cs.cs544.domain.State;
import edu.miu.cs.cs544.domain.UserType;
import edu.miu.cs.cs544.dto.request.AddressRequest;
import edu.miu.cs.cs544.dto.request.AdminRequest;
import edu.miu.cs.cs544.dto.request.CustomerRequest;
import edu.miu.cs.cs544.dto.response.AdminResponse;
import edu.miu.cs.cs544.dto.response.CustomerResponse;
import edu.miu.cs.cs544.repository.CountryRepository;
import edu.miu.cs.cs544.repository.StateRepository;
import edu.miu.cs.cs544.service.AdminService;
import edu.miu.cs.cs544.service.CustomerService;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {

    @MockBean
    private CustomerService customerService;
    @Autowired
    MockMvc mock;
    @Autowired
    private ObjectMapper objectMapper;
    // @MockBean
    // StateRepository stateRepository;

    // @BeforeEach
    // public void setup() {
    // Country US = new Country("US", "United States",
    // 1000000);

    // State CA = new State("CA", "California", US);
    // State TX = new State("TX", "Texas", US);

    // Mockito.when(stateRepository.findByCode("CA")).thenReturn(Optional.of(CA));
    // Mockito.when(stateRepository.findByCode("TX")).thenReturn(Optional.of(TX));

    // }

    @Test
    public void should_ReturnCustomer_When_CustomerExists() throws Exception {
        CustomerResponse customer = new CustomerResponse();
        customer.setId(1);
        customer.setFirstName("Bob");
        customer.setLastName("Mark");
        customer.setUserName("bob");
        customer.setEmail("bmark@gmail.com");

        Mockito.when(customerService.getById(customer.getId())).thenReturn(customer);
        mock.perform(MockMvcRequestBuilders.get("/customers/{id}",
                customer.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(customer.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value(customer.getUserName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(customer.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(customer.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(customer.getEmail()));
    }

    @Test
    public void should_CreateCustomer_When_CustomerPosted() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setFirstName("Bob");
        request.setLastName("Mark");
        request.setUserName("bob");
        request.setUserPass("pass");
        request.setEmail("bmark@gmail.com");
        AddressRequest addressRequest1 = new AddressRequest();
        addressRequest1.setLine1("qwe");
        addressRequest1.setLine2("qwe");
        addressRequest1.setPostalCode("123");
        addressRequest1.setCity("fairfield");
        addressRequest1.setStateCode("TX");
        request.setBillingAddress(addressRequest1);
        AddressRequest addressRequest2 = new AddressRequest();
        addressRequest2.setLine1("qwe");
        addressRequest2.setLine2("qwe");
        addressRequest2.setPostalCode("456");
        addressRequest2.setCity("fairfield");
        addressRequest2.setStateCode("NY");
        request.setPhysicalAddress(addressRequest2);
        CustomerResponse response = new CustomerResponse();
        response.setId(1);
        response.setFirstName("Bob");
        response.setLastName("Mark");
        response.setUserName("bob");
        response.setEmail("bmark@gmail.com");
        String json = objectMapper.writeValueAsString(request);
        Mockito.when(customerService.create(request)).thenReturn(response);
        mock.perform(MockMvcRequestBuilders.post("/customers")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
