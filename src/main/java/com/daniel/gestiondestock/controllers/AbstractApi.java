package com.daniel.gestiondestock.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AbstractApi<T> {

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    T create(@RequestBody T resource);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    T getById(@PathVariable("id") Integer id);

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<T> getAll();

    @DeleteMapping(value = "/delete/{id}")
    void delete(@PathVariable("id") Long id);

}
