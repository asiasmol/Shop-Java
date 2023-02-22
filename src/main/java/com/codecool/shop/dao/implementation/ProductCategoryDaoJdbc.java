package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class ProductCategoryDaoJdbc implements ProductCategoryDao {
    private final JdbcTemplate jdbc;

    public ProductCategoryDaoJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        return jdbc.queryForObject("SELECT * FROM categories WHERE id = ?",
                ProductCategoryDaoJdbc::mapRow, id);    }

    private static ProductCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ProductCategory(rs.getInt("id"),rs.getString("name"), rs.getString("department"), rs.getString("description"));
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return jdbc.query("SELECT * FROM categories ORDER BY id",
                ProductCategoryDaoJdbc::mapRow);
    }
}
