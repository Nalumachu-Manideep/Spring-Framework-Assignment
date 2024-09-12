package com.springdemo.dao;

import com.springdemo.entity.Product;

import java.util.List;

public interface ProductDAO {
    public List<Product> getProducts();

    public void saveProduct(Product theProduct);

    public Product getProduct(int theId);

    public void deleteProduct(int theId);

    List<Product> getProductsByIds(List<Integer> productIds);
}
