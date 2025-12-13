package com.example.saleswebsite.mapper;

import com.example.saleswebsite.dto.product.ProductCreateRequest;
import com.example.saleswebsite.dto.product.ProductResponse;
import com.example.saleswebsite.dto.product.ProductVariantResponse;
import com.example.saleswebsite.entity.Product;
import com.example.saleswebsite.entity.ProductVariant;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static Product toEntity(ProductCreateRequest req) {
        if (req == null)
            return null;
        Product p = new Product();
        p.setUserId(req.getUserId());
        p.setName(req.getName());
        p.setDescription(req.getDescription());
        p.setIsActive(req.getIsActive());
        return p;
    }

    public static ProductResponse toResponse(Product p, List<ProductVariant> variants) {
        if (p == null)
            return null;
        ProductResponse r = new ProductResponse();
        r.setId(p.getId());
        r.setUserId(p.getUserId());
        r.setName(p.getName());
        r.setDescription(p.getDescription());
        r.setIsActive(p.getIsActive());
        if (variants != null) {
            List<ProductVariantResponse> vs = variants.stream().map(v -> {
                ProductVariantResponse vr = new ProductVariantResponse();
                vr.setId(v.getId());
                vr.setName(v.getName());
                vr.setPrice(v.getPrice());
                vr.setStock(v.getStock());
                return vr;
            }).collect(Collectors.toList());
            r.setVariants(vs);
        }
        return r;
    }
}
