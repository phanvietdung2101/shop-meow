package com.yyy.shopcart.repository;

import com.yyy.shopcart.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
}
