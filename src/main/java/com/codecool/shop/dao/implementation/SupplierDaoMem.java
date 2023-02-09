package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class SupplierDaoMem implements SupplierDao {
    private final JdbcTemplate jdbc;

    public SupplierDaoMem(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        return jdbc.queryForObject("SELECT * FROM supplier WHERE id = ?",
                SupplierDaoMem::mapRow, id);
    }

    private static Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Supplier(rs.getInt("id"),rs.getString("name"),rs.getString("description"));
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return jdbc.query("SELECT * FROM supplier ORDER BY id",
                SupplierDaoMem::mapRow);
    }
}
