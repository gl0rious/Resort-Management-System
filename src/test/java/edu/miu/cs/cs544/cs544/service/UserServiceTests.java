package edu.miu.cs.cs544.cs544.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;
import edu.miu.cs.cs544.dto.request.AdminRequest;
import edu.miu.cs.cs544.dto.response.AdminResponse;
import edu.miu.cs.cs544.repository.UserRepository;
import edu.miu.cs.cs544.service.AdminService;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private AdminService service;
    @MockBean
    private UserRepository repo;

    @Test
    public void should_SaveUser_When_UserCreated() {
        AdminRequest adminRequest = new AdminRequest();
        adminRequest.setUserName("admin");
        adminRequest.setUserPass("pass");
        User user = AdminRequest.to(adminRequest);
        user.setId(1);
        Mockito.when(repo.save(any(User.class))).thenReturn(user);
        service.create(adminRequest);
        Mockito.verify(repo, Mockito.times(1)).save(any(User.class));
    }

    @Test
    public void should_ThrowException_When_UserNameExists() {
        Mockito.when(repo.existsByUserName("admin"))
                .thenReturn(true);
        AdminRequest adminRequest = new AdminRequest();
        adminRequest.setUserName("admin");
        adminRequest.setUserPass("pass");
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            service.create(adminRequest);
        });
        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    }

    @Test
    public void should_ReturnUser_When_UserExists() {
        User saved = new User("admin", "pass", UserType.ADMIN);
        saved.setId(1);
        Mockito.when(repo.findByUserName("admin"))
                .thenReturn(saved);
        AdminResponse found = service.getByName("admin");
        assertThat(found.getUserName()).isEqualTo(saved.getUserName());
        assertThat(found.getType()).isEqualTo(saved.getType());
    }
}
