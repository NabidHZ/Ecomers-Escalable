package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products") // Define la ruta base para este controlador
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<Product> getProducts( // Método para obtener productos filtrados
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getFilteredProducts(category, minPrice, maxPrice, sort, page, size);
    }
}