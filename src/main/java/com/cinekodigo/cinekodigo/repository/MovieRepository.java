package com.cinekodigo.cinekodigo.repository;

import com.cinekodigo.cinekodigo.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movies, Long> {
    List<Movies> findByTitleContaining(String title);
}
