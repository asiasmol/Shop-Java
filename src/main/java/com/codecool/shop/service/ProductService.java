package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private final SupplierDao supplierDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }

    public ProductCategory getProductCategory(int categoryId) {
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId) {
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

    public List<Product> getAll() {
        return productDao.getAll();
    }

    public List<Product> getByCategoryId(int categoryId) {
        return productDao.getBy(productCategoryDao.find(categoryId));
    }

    public List<Product> getBySuppliers(int suppliersId) {
        return productDao.getBy(supplierDao.find(suppliersId));
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDao.getAll();
    }

    public Product find(int id) {
        return productDao.find(id);
    }

    public List<ProductCategory> getAllCategories() {
        return productCategoryDao.getAll();
    }
}
