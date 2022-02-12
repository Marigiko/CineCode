package com.cinekodigo.cinekodigo.repository;

import com.cinekodigo.cinekodigo.entity.Crud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrudRepository extends JpaRepository<Crud, Long> {
    List<Crud> findByPublished(boolean published);
    List<Crud> findByTitleContaining(String title);
}