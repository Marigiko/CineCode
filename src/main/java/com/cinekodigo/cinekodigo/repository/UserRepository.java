package com.cinekodigo.cinekodigo.repository;

import com.cinekodigo.cinekodigo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findByNameContaining(String name);
}