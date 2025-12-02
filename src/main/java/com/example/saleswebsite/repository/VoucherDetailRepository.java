package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.VoucherDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherDetailRepository extends JpaRepository<VoucherDetail, Long> {
    Optional<VoucherDetail> findByCode(String code);

    List<VoucherDetail> findByIsActive(Boolean isActive);

    List<VoucherDetail> findByDiscountType(String discountType);
}
