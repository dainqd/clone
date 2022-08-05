package com.example.clone.restapi;

import com.example.clone.entity.Category;
import com.example.clone.entity.News;
import com.example.clone.service.CategoryService;
import com.example.clone.util.Enums;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
@Slf4j
public class CategoryApi {

//    @Autowired
    final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getListt(){
        return ResponseEntity.ok(categoryService.findAllByStatus(Enums.CategoryStatus.active));
//        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer id){
        Optional<Category> optionalCategory = categoryService.getListByIdAndStatus(id, Enums.CategoryStatus.active);
//        Optional<Category> optionalCategory = categoryService.findById(id);
        if (!optionalCategory.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalCategory.get());
    }
}
