package com.ecommerce.service;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getFilteredProducts(String category, Double minPrice, Double maxPrice, String sort, int page, int size) {
        Sort sorting = Sort.unsorted();
        if (sort != null) {
            switch (sort) {
                case "newest":
                    sorting = Sort.by(Sort.Direction.DESC, "id");
                    break;
                case "priceAsc":
                    sorting = Sort.by(Sort.Direction.ASC, "price");
                    break;
                case "priceDesc":
                    sorting = Sort.by(Sort.Direction.DESC, "price");
                    break;
                default:
                    sorting = Sort.unsorted();
            }
        }

        PageRequest pageRequest = PageRequest.of(page, size, sorting);
        return productRepository.findByFilters(category, minPrice, maxPrice, pageRequest);
    }
}