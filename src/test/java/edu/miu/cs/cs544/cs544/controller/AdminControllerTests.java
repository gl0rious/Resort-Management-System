package edu.miu.cs.cs544.cs544.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
import edu.miu.cs.cs544.domain.UserType;
import edu.miu.cs.cs544.dto.request.AdminRequest;
import edu.miu.cs.cs544.dto.response.AdminResponse;
import edu.miu.cs.cs544.service.AdminService;

@WebMvcTest(AdminController.class)
public class AdminControllerTests {

    @MockBean
    private AdminService adminService;

    @Autowired
    MockMvc mock;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_ReturnAdmin_When_AdminExists() throws Exception {
        AdminResponse admin = new AdminResponse();
        admin.setId(1);
        admin.setUserName("admin");
        admin.setType(UserType.ADMIN);
        Mockito.when(adminService.getById(admin.getId())).thenReturn(admin);
        mock.perform(MockMvcRequestBuilders.get("/admins/{id}",
                admin.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(admin.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value(admin.getUserName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value(admin.getType().toString()));
    }

    @Test
    public void should_CreateAdmin_When_AdminPosted() throws Exception {
        AdminRequest admin = new AdminRequest();
        admin.setUserName("admin");
        admin.setUserPass("pass");
        AdminResponse response = new AdminResponse();
        response.setId(1);
        response.setUserName("admin");
        response.setType(UserType.ADMIN);
        String json = objectMapper.writeValueAsString(admin);
        Mockito.when(adminService.create(admin)).thenReturn(response);
        mock.perform(MockMvcRequestBuilders.post("/admins")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        // verify(adminService, times(1)).create(admin);
    }

}
