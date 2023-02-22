package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDaoJdbc implements ProductDao {
    private static ProductCategoryDaoJdbc productCategoryDaoJdbc;
    private static SupplierDaoJdbc supplierDaoMem;
    private final JdbcTemplate jdbc;

    public ProductDaoJdbc(ProductCategoryDaoJdbc productCategoryDaoJdbc, SupplierDaoJdbc supplierDaoMem, JdbcTemplate jdbc) {
        this.productCategoryDaoJdbc = productCategoryDaoJdbc;
        this.supplierDaoMem = supplierDaoMem;
        this.jdbc = jdbc;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return jdbc.queryForObject("SELECT * FROM products WHERE id = ?",
                ProductDaoJdbc::mapRow, id);
    }

    @Override
    public Product findByName(String name) {
        return null;
    }

    @Override
    public void remove(int id) {

    }



    @Override
    public List<Product> getAll() {
        return jdbc.query("SELECT * FROM products ORDER BY id",
                ProductDaoJdbc::mapRow);
    }

    private static Product mapRow(ResultSet rs,int rowNum) throws SQLException {
        return new Product(rs.getInt("id"),rs.getString("name"),rs.getBigDecimal("price"), rs.getString("currency_string"),rs.getString("description"), productCategoryDaoJdbc.find(rs.getInt("category_id")),supplierDaoMem.find(rs.getInt("supplier_id")) );
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return jdbc.query("SELECT * FROM products WHERE supplier_id = ?",
                ProductDaoJdbc::mapRow, supplier.getId());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return jdbc.query("SELECT * FROM products WHERE category_id = ?",
                ProductDaoJdbc::mapRow, productCategory.getId());
    }
}
