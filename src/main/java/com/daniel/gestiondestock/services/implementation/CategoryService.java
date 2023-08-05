package com.daniel.gestiondestock.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.daniel.gestiondestock.dto.CategoryDto;
import com.daniel.gestiondestock.exception.EntityNotFoundException;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.exception.InvalidEntityException;
import com.daniel.gestiondestock.mapper.DtoMapper;
import com.daniel.gestiondestock.model.Category;
import com.daniel.gestiondestock.repository.CategoryRepository;
import com.daniel.gestiondestock.services.contracts.ICategoryService;
import com.daniel.gestiondestock.validators.DtoValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService implements ICategoryService {

  @Autowired
  private CategoryRepository repository;

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Invalid category id provided");
      return;
    }
    this.repository.deleteById(id);
  }

  @Override
  public List<CategoryDto> findAll() {
    List<CategoryDto> categories = repository.findAll()
        .stream()
        .map((cat) -> DtoMapper.fromEntity(cat, CategoryDto.class))
        .collect(Collectors.toList());
    return categories;
  }

  @Override
  public CategoryDto findById(Integer id) {
    if (id == null) {
      log.error("Not valid category id provided");
      return null;
    }
    Optional<Category> categoryOptional = this.repository.findById(id);
    Category category = categoryOptional.orElseThrow(
        () -> new EntityNotFoundException("Aucune category avec l'ID  = " + id + " n'as été trouver dans la BDD",
            ErrorCodes.CATEGORY_NOT_FOUND));
    return DtoMapper.fromEntity(category, CategoryDto.class);
  }

  @Override
  public CategoryDto save(CategoryDto dto) {
    List<String> errors = DtoValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Category is not valid");
      throw new InvalidEntityException("La category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
    }
    var category = DtoMapper.toEntity(dto, Category.class);
    return DtoMapper.fromEntity(
        this.repository.save(category), CategoryDto.class);
  }

  @Override
  public CategoryDto findByCode(String code) {
    if (!StringUtils.hasLength(code)) {
      log.error("Category Code is null");
      return null;
    }
    return this.repository.findCategoryBycode(code)
        .map((category) -> DtoMapper.fromEntity(category, CategoryDto.class))
        .orElseThrow(() -> new EntityNotFoundException(
            "category not found for code " + code,
            ErrorCodes.CATEGORY_NOT_FOUND));
  }

}
