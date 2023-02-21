package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    private ProductService productService;
    @Mock
    private ProductDao productDao;

    @Mock
    private ProductCategoryDao productCategoryDao;

    @Mock
    private SupplierDao supplierDao;

    @Mock
    private Supplier supplier;

    @Mock
    private ProductCategory category;

    @BeforeEach
    public void setUp() {
        productService = new ProductService(productDao, productCategoryDao, supplierDao);
    }

    @Test
    public void testGetAll(){
        // given
        var product1 = new Product(1, "Product 1", new BigDecimal(3), "USD", "eee", category,supplier);
        var product2 = new Product(2, "Product 2", new BigDecimal(3), "USD", "eee", category,supplier);
        List<Product> products = Arrays.asList(product1, product2);

        // when
        when(productDao.getAll()).thenReturn(products);
        List<Product> result = productService.getAll();

        // then
       assertEquals(products, result);
    }

    @Test
    public void testGetByCategoryId(){
        // given
        var category1 = new ProductCategory(1,"eee", "ddd", "eee");
        var product1 = new Product(1, "Product 1", new BigDecimal(3), "USD", "eee", category1,supplier);
        var products1 = List.of(product1);

        //when
        when(productDao.getBy(productCategoryDao.find(1))).thenReturn(List.of(product1));
        List<Product> result = productService.getByCategoryId(1);

        // then
        assertEquals(products1, result);
    }

    @Test
    public void testGetBySupplierId(){
        // given
        var supplier = new Supplier(1,"eee", "ddd");
        var product1 = new Product(1, "Product 1", new BigDecimal(3), "USD", "eee", category,supplier);
        var products1 = List.of(product1);

        //when
        when(productDao.getBy(supplierDao.find(1))).thenReturn(List.of(product1));
        List<Product> result = productService.getBySuppliers(1);

        // then
        assertEquals(products1, result);
    }

    @Test
    public void testGetAllSuppliers(){
        // given
        var supplier1 = new Supplier(1,"eee", "ddd");
        var supplier2 = new Supplier(2,"eee", "ddd");
        var suppliers = List.of(supplier1, supplier2);

        // when
        when(supplierDao.getAll()).thenReturn(suppliers);
        List<Supplier> result = productService.getAllSuppliers();

        // then
        assertEquals(suppliers, result);
    }

}
