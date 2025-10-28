package com.example.employeeperformance.repositories;

import com.example.employeeperformance.entities.User;
import com.example.employeeperformance.types.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNickName(String nickName);

    Optional<User> findByEmail(String email);

    List<User> findByUserRole(UserRole userRole);
}
