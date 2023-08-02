package com.daniel.gestiondestock.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.gestiondestock.controllers.api.AbstractApi;
import com.daniel.gestiondestock.dto.CategoryDto;
import com.daniel.gestiondestock.services.impl.CategoryServiceImpl;

import static com.daniel.gestiondestock.utils.Constants.APP_ROOT;


@RestController
@RequestMapping(APP_ROOT + "/category")
public class CategoryController implements AbstractApi<CategoryDto>{
  
  @Autowired
  private CategoryServiceImpl categoryService; 

  @Override
  public CategoryDto create(CategoryDto resource) {
    return categoryService.save(resource);
  }

  @Override
  public void delete(Integer id) {
      categoryService.delete(id);    
  }

  @Override
  public List<CategoryDto> getAll() {
    return categoryService.findAll();
  }

  @Override
  public CategoryDto getById(Integer id) {
    return categoryService.findById(id);
  }

 
  
}
