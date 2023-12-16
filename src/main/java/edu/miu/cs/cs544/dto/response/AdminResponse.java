package edu.miu.cs.cs544.dto.response;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
    private int id;
    private String userName;
    private UserType type;

    public static AdminResponse to(User user) {
        return new AdminResponse(user.getId(), user.getUserName(), user.getType());
    }
}