package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    Attribute findByName(String name);
}
