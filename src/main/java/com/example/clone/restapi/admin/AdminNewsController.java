package com.example.clone.restapi.admin;

import com.example.clone.dto.request.CategoryRequest;
import com.example.clone.dto.response.MessageResponse;
import com.example.clone.entity.Category;
import com.example.clone.entity.News;
import com.example.clone.repository.CategoryRepository;
import com.example.clone.service.CategoryService;
import com.example.clone.service.NewsService;
import com.example.clone.util.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/v1/admin/news")
public class AdminNewsController {
    @Autowired
    NewsService newsService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping()
    public ResponseEntity<List<News>> getLists(){
        return ResponseEntity.ok(newsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetails(@PathVariable Integer id){
        Optional<News> optionalNews = newsService.findById(id);
        if (!optionalNews.isPresent()){
            ResponseEntity.badRequest().build();
        }
        optionalNews.get().setViews(optionalNews.get().getViews() + 1);
        newsService.save(optionalNews.get());
        return ResponseEntity.ok(optionalNews.get());
    }

    @PostMapping("")
    public ResponseEntity<?> create( @RequestBody CategoryRequest categoryRequest){
        News news = new News(categoryRequest.getCreatedAt(),
                categoryRequest.getTitle(),
                categoryRequest.getDescription(),
                categoryRequest.getImg(),
                categoryRequest.getContent(),
                categoryRequest.getViews(),
                categoryRequest.getStatus(),
                categoryRequest.getAuthor());
        Set<String> strCategory = categoryRequest.getCategory();
        Set<Category> category = new HashSet<>();
        if (strCategory == null){
            Category newsCategory = categoryRepository.findByName(Enums.CategoryName.HOMEPAGE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            category.add(newsCategory);
        }else {
            strCategory.forEach(cate -> {
                switch (cate){
                    case "homepage":
                        Category homepageCategory = categoryRepository.findByName(Enums.CategoryName.HOMEPAGE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        category.add(homepageCategory);
                        break;
                    case "political":
                        Category politicalCategory = categoryRepository.findByName(Enums.CategoryName.POLITICAL)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        category.add(politicalCategory);
                        break;
                    case "social":
                        Category socialCategory = categoryRepository.findByName(Enums.CategoryName.SOCIAL)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        category.add(socialCategory);
                        break;
                    case "economy":
                        Category economyCategory = categoryRepository.findByName(Enums.CategoryName.ECONOMY)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        category.add(economyCategory);
                        break;
                    case "health":
                        Category healthCategory = categoryRepository.findByName(Enums.CategoryName.HEALTH)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        category.add(healthCategory);
                        break;
                    case "education":
                        Category educationCategory = categoryRepository.findByName(Enums.CategoryName.EDUCATION)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        category.add(educationCategory);
                        break;
                    case "law":
                        Category lawCategory = categoryRepository.findByName(Enums.CategoryName.LAW)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        category.add(lawCategory);
                        break;
                    case "sport":
                        Category sportCategory = categoryRepository.findByName(Enums.CategoryName.SPORT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        category.add(sportCategory);
                        break;
                    default:
                        Category worldCategory = categoryRepository.findByName(Enums.CategoryName.WORLD)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        category.add(worldCategory);
                }
            });
        }
        news.setCategories(category);
        newsService.save(news);
        return ResponseEntity.ok(new MessageResponse("News has been added successfully!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<News> update(@PathVariable Integer id, @RequestBody News news){
        Optional<News> optionalNews = newsService.findById(id);
        if ((!optionalNews.isPresent())){
            ResponseEntity.badRequest().build();
        }
        News existNews = optionalNews.get();

        existNews.setTitle(news.getTitle());
        existNews.setDescription(news.getDescription());
        existNews.setImg(news.getImg());
        existNews.setContent(news.getContent());
        existNews.setViews(news.getViews());
        existNews.setStatus(news.getStatus());
        existNews.setAuthor(news.getAuthor());
        existNews.setCategories(news.getCategories());
        return ResponseEntity.ok(newsService.save(existNews));
    }

    @PutMapping("/{id}/{keyword}")
    public ResponseEntity<News> updated(@PathVariable Integer id,@PathVariable String keyword , @RequestBody News news){
        Optional<News> optionalNews = newsService.findById(id);
        if ((!optionalNews.isPresent())){
            ResponseEntity.badRequest().build();
        }
        News existNews = optionalNews.get();

        if (keyword.equals("title")){
            existNews.setTitle(news.getTitle());
        } else if (keyword.equals("description")){
            existNews.setDescription(news.getDescription());
        } else if (keyword.equals("img")){
            existNews.setImg(news.getImg());
        } else if (keyword.equals("content")){
            existNews.setContent(news.getContent());
        } else if (keyword.equals("views")){
            existNews.setViews(news.getViews());
        } else if (keyword.equals("status")){
            existNews.setStatus(news.getStatus());
        } else if (keyword.equals("author")){
            existNews.setAuthor(news.getAuthor());
        } else if (keyword.equals("categories")){
            existNews.setCategories(news.getCategories());
        } else {
            ResponseEntity.badRequest();
            new RuntimeException("Error: keyword not true");
        }
        return ResponseEntity.ok(newsService.save(existNews));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        if ((!newsService.findById(id).isPresent())){
            ResponseEntity.badRequest().build();
        }
        newsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    //    Tìm kiếm theo title
    @GetMapping("/search/={keyword}/{abc}")
    public Page<News> getFind(@PathVariable String keyword,@PathVariable Enums.NewsStatus abc) {
        return newsService.search(keyword, Pageable.ofSize(10), abc);
    }

    //    Tìm kiếm theo từ khóa
    @GetMapping("/search/all/={keyword}/{abc}")
    public Page<News> getFindes(@PathVariable String keyword,@PathVariable Enums.NewsStatus abc) {
        return newsService.searches(keyword, Pageable.ofSize(10), abc);
    }
    //    Tin hot
    @GetMapping("/search/=hot")
    public Page<News> getFindByView(){
        Pageable pageable= PageRequest.of(0, 10, Sort.by("views").descending());
        return newsService.getListSort(pageable);
    }
    // Tin mới nhất

    @GetMapping("/search/=new")
    public Page<News> getFindByCreateAt(){
        Pageable pageable= PageRequest.of(0, 10, Sort.by("createdAt").descending());
        return newsService.getListSort(pageable);
    }
}
