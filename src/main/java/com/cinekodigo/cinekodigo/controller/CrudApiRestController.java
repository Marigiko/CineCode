package com.cinekodigo.cinekodigo.controller;

import com.cinekodigo.cinekodigo.entity.Crud;
import com.cinekodigo.cinekodigo.repository.CrudRepository;
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
public class CrudApiRestController {
    @Autowired
    CrudRepository CrudRepository;
    @GetMapping("/Cruds")
    public ResponseEntity<List<Crud>> getAllCruds(@RequestParam(required = false) String title) {
        try {
            List<Crud> Cruds = new ArrayList<Crud>();
            if (title == null)
                CrudRepository.findAll().forEach(Cruds::add);
            else
                CrudRepository.findByTitleContaining(title).forEach(Cruds::add);
            if (Cruds.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(Cruds, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/Cruds/{id}")
    public ResponseEntity<Crud> getCrudById(@PathVariable("id") long id) {
        Optional<Crud> CrudData = CrudRepository.findById(id);
        if (CrudData.isPresent()) {
            return new ResponseEntity<>(CrudData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/Cruds")
    public ResponseEntity<Crud> createCrud(@RequestBody Crud Crud) {
        try {
            Crud _Crud = CrudRepository
                    .save(new Crud(Crud.getTitle(), Crud.getDescription(), false));
            return new ResponseEntity<>(_Crud, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/Cruds/{id}")
    public ResponseEntity<Crud> updateCrud(@PathVariable("id") long id, @RequestBody Crud Crud) {
        Optional<Crud> CrudData = CrudRepository.findById(id);
        if (CrudData.isPresent()) {
            Crud _Crud = CrudData.get();
            _Crud.setTitle(Crud.getTitle());
            _Crud.setDescription(Crud.getDescription());
            _Crud.setPublished(Crud.isPublished());
            return new ResponseEntity<>(CrudRepository.save(_Crud), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/Cruds/{id}")
    public ResponseEntity<HttpStatus> deleteCrud(@PathVariable("id") long id) {
        try {
            CrudRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/Cruds")
    public ResponseEntity<HttpStatus> deleteAllCruds() {
        try {
            CrudRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/Cruds/published")
    public ResponseEntity<List<Crud>> findByPublished() {
        try {
            List<Crud> Cruds = CrudRepository.findByPublished(true);
            if (Cruds.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(Cruds, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}