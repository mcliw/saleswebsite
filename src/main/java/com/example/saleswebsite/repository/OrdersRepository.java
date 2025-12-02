package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserId(Long userId);

    Optional<Orders> findByOrderCode(String orderCode);

    List<Orders> findByOrderStatus(String orderStatus);
}
