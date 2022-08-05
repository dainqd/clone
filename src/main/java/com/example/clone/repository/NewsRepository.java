package com.example.clone.repository;


import com.example.clone.entity.News;
import com.example.clone.util.Enums;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findAllByStatus(Enums.NewsStatus status);

    Optional<News> findAllByIdAndStatus(Integer id, Enums.NewsStatus status);



//  Hàm tìm kiếm

    @Query("SELECT re FROM News re WHERE "
            + "re.title LIKE %:title% AND "
            + "re.status = :abc")
    public Page<News> filter(@Param("title") String title, Pageable pageable, Enums.NewsStatus abc);


    @Query("SELECT re FROM News re WHERE "
            + "re.title LIKE %:title% AND "
            + "re.status = :active ")
    public Page<News> filter(@Param("title") String title, Pageable pageable);


    @Query("SELECT re FROM News re WHERE " + "re.title LIKE %:title% OR "
            + "re.description LIKE %:title% OR "
            + "re.content LIKE %:title% OR "
            + "re.author LIKE %:title% AND "
            + "re.status = :abc")
    public Page<News> filters(@Param("title") String title, Pageable pageable, Enums.NewsStatus abc);


    @Query("SELECT re FROM News re WHERE " + "re.title LIKE %:title% OR "
            + "re.description LIKE %:title% OR "
            + "re.content LIKE %:title% OR "
            + "re.author LIKE %:title% AND "
            + "re.status = :active")
    public Page<News> filters(@Param("title") String title, Pageable pageable);

    public Page<News> findAll(Pageable pageable);

    @Query("SELECT re FROM News re WHERE "
            + "re.status = :active")
    public Page<News> findAllAndTrue(Pageable pageable);
}
