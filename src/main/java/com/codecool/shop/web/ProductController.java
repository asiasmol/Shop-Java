package com.codecool.shop.web;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
public class ProductController {
    ProductDao productDataStore = ProductDaoMem.getInstance();
    private final Cart cart;
    ProductCategoryDao productCategoryDataStore;
    SupplierDao supplierDataStore;

    @Autowired
    public ProductController(Cart cart) {
        this.cart = cart;
        productDataStore = ProductDaoMem.getInstance();
        productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        supplierDataStore = SupplierDaoMem.getInstance();
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory phone = new ProductCategory("phone", "Software", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(phone);
        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", phone, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        // Alternative setting of the template context
        // Alternative setting of the template context
    }

    @GetMapping("/")
    public String helloword(Model model){
        model.addAttribute("categories", productCategoryDataStore.getAll());
        model.addAttribute("suppliers", supplierDataStore.getAll());
        model.addAttribute("products",productDataStore.getBy(productCategoryDataStore.find(1)));
        return "product/index";
    }
    @PostMapping("/category")
    public String sortByCategory (@RequestParam("categoryId") int categoryId,Model model) {
        model.addAttribute("categories", productCategoryDataStore.getAll());
        model.addAttribute("suppliers", supplierDataStore.getAll());
        model.addAttribute("products",productDataStore.getBy(productCategoryDataStore.find(categoryId)));
        return "product/index";
    }
    @PostMapping("/supplier")
    public String sortBySuppliers(@RequestParam("suppliersId") int suppliersId,Model model) {
        model.addAttribute("categories", productCategoryDataStore.getAll());
        model.addAttribute("suppliers", supplierDataStore.getAll());
        model.addAttribute("products",productDataStore.getBy(supplierDataStore.find(suppliersId)));
        return "product/index";
    }
    @GetMapping("/add/{productName}")
    public String addProductToCart(@PathVariable("productName") String productName, Model model){

        Optional<Product> oProduct = Optional.ofNullable(productDataStore.findByName(productName));
        if(oProduct.isPresent()){
            Product product = oProduct.get();
            cart.add(product);
            System.out.println(cart.toString());
        }
        model.addAttribute("items", productDataStore.getAll());
        return "product/index";
    }


}
