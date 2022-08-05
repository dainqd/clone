package com.example.clone.repository;

import com.example.clone.entity.Category;
import com.example.clone.entity.Role;
import com.example.clone.util.Enums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByStatus(Enums.CategoryStatus status);

    Optional<Category> findAllByIdAndStatus(Integer id, Enums.CategoryStatus status);

    Optional<Category> findByName(Enums.CategoryName name);
}
