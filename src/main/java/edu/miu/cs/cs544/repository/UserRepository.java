package edu.miu.cs.cs544.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.miu.cs.cs544.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}
