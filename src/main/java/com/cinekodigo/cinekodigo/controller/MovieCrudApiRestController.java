package com.cinekodigo.cinekodigo.controller;

import com.cinekodigo.cinekodigo.entity.Movies;
import com.cinekodigo.cinekodigo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MovieCrudApiRestController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/Movies")
    public ResponseEntity<List<Movies>> getAllMovies(@RequestParam(required = false) String title) {
        try {
            List<Movies> movies = new ArrayList<>();
            if (title == null)
                movieRepository.findAll().forEach(movies::add);
            else
                movieRepository.findByTitleContaining(title).forEach(movies::add);
            if (movies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Movies/{id}")
    public ResponseEntity<Movies> getMovieById(@PathVariable("id") long id) {
        Optional<Movies> movieData = movieRepository.findById(id);
        if (movieData.isPresent()) {
            return new ResponseEntity<>(movieData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Movies")
    public ResponseEntity<Movies> createMovie(@RequestBody Movies movie) {
        try {
            Movies _movie = movieRepository
                    .save(new Movies(movie.getTitle(), movie.getLength()));
            return new ResponseEntity<>(_movie, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Movies/{id}")
    public ResponseEntity<Movies> updateMovie(@PathVariable("id") long id, @RequestBody Movies movie) {
        Optional<Movies> movieData = movieRepository.findById(id);
        if (movieData.isPresent()) {
            Movies _movie = movieData.get();
            _movie.setTitle(movie.getTitle());
            _movie.setLength(movie.getLength());
            return new ResponseEntity<>(movieRepository.save(_movie), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Movies/{id}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable("id") long id) {
        try {
            movieRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/Movies")
    public ResponseEntity<HttpStatus> deleteAllMovies() {
        try {
            movieRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
