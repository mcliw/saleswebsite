package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long orderId);

    List<OrderDetail> findByProductVariantId(Long productVariantId);
}
