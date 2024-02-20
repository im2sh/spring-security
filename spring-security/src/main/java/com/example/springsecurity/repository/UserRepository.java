package com.example.springsecurity.repository;

import com.example.springsecurity.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);
}
