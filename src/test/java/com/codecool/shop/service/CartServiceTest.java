package com.codecool.shop.service;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {
    private CartService cartService;

    @Mock
    private ProductDao productDao;
    @Mock
    private Cart cart;

    @Mock
    private Supplier supplier;

    @Mock
    private ProductCategory category;

    @BeforeEach
    public void setUp() {
        cartService = new CartService(productDao, cart);
    }

    @Test
    public void testGetSum() {
        // given
        when(cart.getSum()).thenReturn("100.00");

        // when
        String sum = cartService.getSum();

        // then
        assertEquals("100.00", sum);
    }

    @Test
    public void testGetAll() {
        // given
        CartItem item1 = new CartItem(new Product(1, "Product 1", new BigDecimal(3), "USD", "eee", category,supplier));
        CartItem item2 = new CartItem(new Product(2, "Product 2", new BigDecimal(3), "USD", "eee", category,supplier));
        List<CartItem> cartItems = Arrays.asList(item1, item2);

        // when
        when(cart.getAll()).thenReturn(cartItems);
        List<CartItem> result = cartService.getAll();

        // then
        assertEquals(cartItems, result);
    }

    @Test
    public void testAdd() {
        // given
        Product product = new Product(1, "Product 1", new BigDecimal(3), "USD", "eee", category,supplier);

        // when
        cartService.add(product);

        // then
        verify(cart).add(product);
    }

    @Test
    public void testRemove() {
        // given
        Product product = new Product(1, "Product 1", new BigDecimal(3), "USD", "eee", category,supplier);

        // when
        cartService.remove(product);

        // then
        verify(cart).remove(product);
    }

    @Test
    public void testClearCart() {
        // given
        cartService.clearCart();

        // then
        verify(cart).clearCart();
    }

}