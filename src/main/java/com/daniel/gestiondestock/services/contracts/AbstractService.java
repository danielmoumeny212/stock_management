package com.daniel.gestiondestock.services.contracts;

import java.util.List;

public interface AbstractService <T> {
  T save(T dto);
  T findById(Integer id);
  List<T> findAll();
  void delete(Integer id);
  
}
