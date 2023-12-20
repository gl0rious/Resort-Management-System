package edu.miu.cs.cs544.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

    boolean existsByUserName(String userName);

    List<User> findAllByType(UserType userType);
}
