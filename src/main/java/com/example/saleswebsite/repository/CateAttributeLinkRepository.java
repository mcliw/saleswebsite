package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.CateAttributeLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CateAttributeLinkRepository extends JpaRepository<CateAttributeLink, Long> {
    List<CateAttributeLink> findByCategoryId(Long categoryId);

    List<CateAttributeLink> findByAttributeId(Long attributeId);

    List<CateAttributeLink> findByCategoryIdAndAttributeId(Long categoryId, Long attributeId);
}
