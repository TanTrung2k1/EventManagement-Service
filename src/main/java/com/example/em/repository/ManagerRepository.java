package com.example.em.repository;

import com.example.em.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    List<Manager> findByStatus(boolean status);
    Optional<Manager> findByEmailAndPassword(String email, String password);
}
