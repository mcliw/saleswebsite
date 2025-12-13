package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.product.ProductCreateRequest;
import com.example.saleswebsite.dto.product.ProductResponse;
import com.example.saleswebsite.entity.Product;
import com.example.saleswebsite.entity.ProductVariant;
import com.example.saleswebsite.mapper.ProductMapper;
import com.example.saleswebsite.repository.ProductRepository;
import com.example.saleswebsite.repository.ProductVariantRepository;
import com.example.saleswebsite.service.ProductService;
import com.example.saleswebsite.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductVariantRepository productVariantRepository) {
        this.productRepository = productRepository;
        this.productVariantRepository = productVariantRepository;
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(p -> ProductMapper.toResponse(p,
                        productVariantRepository.findAll().stream()
                                .filter(v -> v.getProductId() != null && v.getProductId().equals(p.getId()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
                .map(p -> {
                    List<ProductVariant> variants = productVariantRepository.findAll().stream()
                            .filter(v -> v.getProductId() != null && v.getProductId().equals(p.getId()))
                            .collect(Collectors.toList());
                    return ProductMapper.toResponse(p, variants);
                }).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductResponse create(ProductCreateRequest request) {
        log.info("Creating product: {}", request.getName());
        Product p = ProductMapper.toEntity(request);
        Product saved = productRepository.save(p);

        List<Path> uploadedFiles = new ArrayList<>();
        try {
            // handle variants batch insert
            if (request.getVariants() != null && !request.getVariants().isEmpty()) {
                List<ProductVariant> variants = request.getVariants().stream().map(rv -> {
                    ProductVariant v = new ProductVariant();
                    v.setProductId(saved.getId());
                    v.setName(rv.getName());
                    v.setStock(rv.getStock());
                    v.setPrice(rv.getPrice());
                    v.setOriginalPrice(rv.getOriginalPrice());
                    return v;
                }).collect(Collectors.toList());
                productVariantRepository.saveAll(variants);
            }

            // simulate file handling: if any file operations needed, use FileUtils
            // e.g., Path pth = FileUtils.saveFile(Paths.get("uploads/products"),
            // "file.jpg", bytes);
            return findById(saved.getId());
        } catch (Exception ex) {
            log.error("Error creating product, rolling back: {}", ex.getMessage(), ex);
            // Transaction will rollback; delete any uploaded files
            FileUtils.rollbackUploadedFiles(uploadedFiles);
            throw ex;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductResponse update(Long id, ProductCreateRequest request) {
        log.info("Updating product {}", id);
        return productRepository.findById(id).map(existing -> {
            existing.setName(request.getName());
            existing.setDescription(request.getDescription());
            existing.setIsActive(request.getIsActive());
            Product saved = productRepository.save(existing);

            // manage variants: for simplicity delete existing variants for product and
            // insert new ones
            List<ProductVariant> old = productVariantRepository.findAll().stream()
                    .filter(v -> v.getProductId() != null && v.getProductId().equals(saved.getId()))
                    .collect(Collectors.toList());
            if (!old.isEmpty()) {
                productVariantRepository.deleteAll(old);
            }
            if (request.getVariants() != null && !request.getVariants().isEmpty()) {
                List<ProductVariant> variants = request.getVariants().stream().map(rv -> {
                    ProductVariant v = new ProductVariant();
                    v.setProductId(saved.getId());
                    v.setName(rv.getName());
                    v.setStock(rv.getStock());
                    v.setPrice(rv.getPrice());
                    v.setOriginalPrice(rv.getOriginalPrice());
                    return v;
                }).collect(Collectors.toList());
                productVariantRepository.saveAll(variants);
            }
            return findById(saved.getId());
        }).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting product {}", id);
        // cascade delete variants
        List<ProductVariant> variants = productVariantRepository.findAll().stream()
                .filter(v -> v.getProductId() != null && v.getProductId().equals(id)).collect(Collectors.toList());
        if (!variants.isEmpty()) {
            productVariantRepository.deleteAll(variants);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> findByName(String name) {
        if (name == null)
            return List.of();
        return productRepository.findAll().stream()
                .filter(p -> p.getName() != null && p.getName().toLowerCase().contains(name.toLowerCase()))
                .map(p -> {
                    List<ProductVariant> variants = productVariantRepository.findAll().stream()
                            .filter(v -> v.getProductId() != null && v.getProductId().equals(p.getId()))
                            .collect(Collectors.toList());
                    return ProductMapper.toResponse(p, variants);
                }).collect(Collectors.toList());
    }
}
