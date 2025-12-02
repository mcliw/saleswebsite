package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    News findBySlug(String slug);

    List<News> findByAuthorId(Long authorId);

    News findByTitle(String title);
}
