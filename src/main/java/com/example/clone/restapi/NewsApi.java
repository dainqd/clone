package com.example.clone.restapi;

import com.example.clone.dto.request.CategoryRequest;
import com.example.clone.dto.response.MessageResponse;
import com.example.clone.entity.Category;
import com.example.clone.entity.News;
import com.example.clone.entity.Role;
import com.example.clone.repository.CategoryRepository;
import com.example.clone.service.CategoryService;
import com.example.clone.service.NewsService;
import com.example.clone.util.Enums;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
public class NewsApi {
//    @Autowired
    final NewsService newsService;

//    @Autowired
    final CategoryService categoryService;

//    @Autowired
    final CategoryRepository categoryRepository;

    @GetMapping()
    public ResponseEntity<List<News>> getList(){
        return ResponseEntity.ok(newsService.getListByStatus(Enums.NewsStatus.active));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer id){
        Optional<News> optionalNews = newsService.getListByIdAndStatus(id,Enums.NewsStatus.active);
        if (!optionalNews.isPresent()){
            ResponseEntity.badRequest().build();
        }
        optionalNews.get().setViews(optionalNews.get().getViews() + 1);
        newsService.save(optionalNews.get());
        return ResponseEntity.ok(optionalNews.get());
    }
//    Tìm kiếm theo title
    @GetMapping("/search/={keyword}")
    public Page<News> getFind(@PathVariable String keyword) {
        return newsService.search(keyword, Pageable.ofSize(10), Enums.NewsStatus.active);
    }

//    Tìm kiếm theo từ khóa
    @GetMapping("/search/all/={keyword}")
    public Page<News> getFindes(@PathVariable String keyword) {
        return newsService.searches(keyword, Pageable.ofSize(10), Enums.NewsStatus.active);
    }

//    Tin hot
    @GetMapping("/search/=hot")
    public Page<News> getFindViewUser(){
        Pageable pageable= PageRequest.of(0, 10, Sort.by("views").descending());
        return newsService.getListSortAndTrue(pageable);
    }

// Tin mới nhất
    @GetMapping("/search/=new")
    public Page<News> getFindByCreateAtUser(){
        Pageable pageable= PageRequest.of(0, 10, Sort.by("createdAt").descending());
        return newsService.getListSortAndTrue(pageable);
    }

}
