package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.ContentBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContentBlockRepository extends JpaRepository<ContentBlock, Long> {
    List<ContentBlock> findByNewsId(Long newsId);
}
