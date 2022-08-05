package com.example.clone.service;

import com.example.clone.entity.Category;
import com.example.clone.repository.CategoryRepository;
import com.example.clone.util.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Integer id){
        return categoryRepository.findById(id);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Integer id){
        categoryRepository.deleteById(id);
    }

    public List<Category> findAllByStatus(Enums.CategoryStatus status){
        return categoryRepository.findAllByStatus(status);
    }

    public Optional<Category> getListByIdAndStatus(Integer id, Enums.CategoryStatus status){
        return categoryRepository.findAllByIdAndStatus(id, status);
    }
}
