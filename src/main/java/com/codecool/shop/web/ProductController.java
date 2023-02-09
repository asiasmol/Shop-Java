package com.codecool.shop.web;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ProductController {
    private final ProductDao productDao;
    private final ProductCategoryDao productCategoryDao;
    private final SupplierDao supplierDao;
    private final Cart cart;


    @Autowired
    public ProductController(Cart cart, ProductDaoMem productDao, ProductCategoryDaoMem productCategoryDao, SupplierDaoMem supplierDao, CartDaoMem cartDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
        this.cart = cart;
    }

    @GetMapping("/")
    public String helloword(Model model) {
        model.addAttribute("categories", productCategoryDao.getAll());
        model.addAttribute("suppliers", supplierDao.getAll());
        model.addAttribute("products",productDao.getAll());
//        System.out.println(productDao.getAll());
        return "index";
    }

    @PostMapping("/category")
    public String sortByCategory(@RequestParam("categoryId") int categoryId, Model model) {
        model.addAttribute("categories", productCategoryDao.getAll());
        model.addAttribute("suppliers", supplierDao.getAll());
        model.addAttribute("products",productDao.getBy(productCategoryDao.find(categoryId)));
        return "index";
    }

    @PostMapping("/supplier")
    public String sortBySuppliers(@RequestParam("suppliersId") int suppliersId, Model model) {
        model.addAttribute("categories", productCategoryDao.getAll());
        model.addAttribute("suppliers", supplierDao.getAll());
        model.addAttribute("products",productDao.getBy(supplierDao.find(suppliersId)));
        return "index";
    }

    @GetMapping("/add/{id}")
    public String addProductToCart(@PathVariable("id") int id, Model model) {
        model.addAttribute("categories", productCategoryDao.getAll());
        model.addAttribute("suppliers", supplierDao.getAll());
        model.addAttribute("products",productDao.getAll());

        Optional<Product> oProduct = Optional.ofNullable(productDao.find(id));
        if(oProduct.isPresent()){
            Product product = oProduct.get();
            cart.add(product);
            System.out.println(cart);
        }
        model.addAttribute("cartItems", cart.getAll());
        return "product/index";
    }

    @GetMapping("/remove/{id}")
    public String removeProductToCart(@PathVariable("id") int id, Model model) {
        model.addAttribute("categories", productCategoryDao.getAll());
        model.addAttribute("suppliers", supplierDao.getAll());
        model.addAttribute("products",productDao.getAll());

        Optional<Product> oProduct = Optional.ofNullable(productDao.find(id));
        if(oProduct.isPresent()){
            Product product = oProduct.get();
            cart.remove(product);
        }
        model.addAttribute("cartItems", cart.getAll());
        return "product/index";
    }

}
