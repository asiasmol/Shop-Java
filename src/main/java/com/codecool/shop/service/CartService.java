package com.codecool.shop.service;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private ProductDao productDao;

    private List<Product> data = new ArrayList<>();

}
