package edu.miu.cs.cs544.dto.request;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequest {
    private String userName;
    private String userPass;

    public static User to(AdminRequest userRequest) {
        return new User(userRequest.getUserName(),
                userRequest.getUserPass(), UserType.ADMIN);
    }
}
