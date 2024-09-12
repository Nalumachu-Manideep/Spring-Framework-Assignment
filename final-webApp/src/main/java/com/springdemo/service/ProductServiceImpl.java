package com.springdemo.service;

import com.springdemo.dao.ProductDAO;
import com.springdemo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    // Need to inject Product DAO
    @Autowired
    private ProductDAO productDAO;

    // Validator for additional validation in service layer
    @Autowired
    private Validator validator;

    @Override
    @Transactional
    public List<Product> getProducts() {
        return productDAO.getProducts();
    }

    @Override
    @Transactional
    public void saveProduct(Product theProduct) {
        productDAO.saveProduct(theProduct);
    }

    @Override
    @Transactional
    public Product getProduct(int theId) {
        return productDAO.getProduct(theId);
    }

    @Override
    @Transactional
    public void deleteProduct(int theId) {
        productDAO.deleteProduct(theId);
    }

    @Override
    @Transactional
    public List<Product> getProductsByIds(List<Integer> productIds) {
        return productDAO.getProductsByIds(productIds);
    }
}
