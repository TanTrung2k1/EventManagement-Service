package com.example.em.repository;

import com.example.em.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByStatusTrue();
    Optional<User> findByIdAndStatusTrue(Long id);
    Optional<User> findByNameAndStatusTrue(String name);
    Optional<User> findByEmailAndPassword(String email, String password);
}
