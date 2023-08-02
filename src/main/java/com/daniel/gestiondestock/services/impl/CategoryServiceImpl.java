package com.daniel.gestiondestock.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.gestiondestock.dto.CategoryDto;
import com.daniel.gestiondestock.exception.EntityNotFoundException;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.exception.InvalidEntityException;
import com.daniel.gestiondestock.model.Category;
import com.daniel.gestiondestock.repository.CategoryRepository;
import com.daniel.gestiondestock.services.CategoryService;
import com.daniel.gestiondestock.validators.CategoryValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository repository;

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Invalid category id provided");
    }
    this.repository.deleteById(id);
  }

  @Override
  public List<CategoryDto> findAll() {
    List<CategoryDto> categories = repository.findAll()
        .stream().map(CategoryDto::fromEntity)
        .collect(Collectors.toList());
    return categories;
  }

  @Override
  public CategoryDto findById(Integer id) {
    if (id == null) {
      log.error("Not valid category id provided");
      return null;
    }
    Optional<Category> category = this.repository.findById(id);

    return Optional.of(CategoryDto.fromEntity(category.get())).orElseThrow(
        () -> new EntityNotFoundException("Aucun article avec l'ID  = " + id + "n'as été trouver dans la BDD",
            ErrorCodes.CATEGORY_NOT_FOUND));
  }

  @Override
  public CategoryDto save(CategoryDto dto) {
    List<String> errors = CategoryValidator.validate(dto);
    log.info(dto.toString());
    if(!errors.isEmpty()){
       log.error("Category is not valid");
       throw new InvalidEntityException("La category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
    }

    return CategoryDto.fromEntity(
      repository.save(
        CategoryDto.toEntity(dto)
      )
    );
  }

}
