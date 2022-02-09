package com.cinekodigo.cinekodigo.repository;

import com.cinekodigo.cinekodigo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}