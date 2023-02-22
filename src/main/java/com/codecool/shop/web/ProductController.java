package com.codecool.shop.web;

import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;


    @Autowired
    public ProductController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String helloWorld(Model model,@ModelAttribute("acountExistError") String acountExistError,@ModelAttribute("wrongLoginDataError") String wrongLoginDataError) {
        model.addAttribute("acountExistError",acountExistError);
        model.addAttribute("wrongLoginDataError",wrongLoginDataError);
        System.out.println(wrongLoginDataError);
        model.addAttribute("products", productService.getAll());
        return basicPage(model);
    }

    private String basicPage(Model model) {
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("suppliers", productService.getAllSuppliers());
        model.addAttribute("totalPrice", cartService.getSum());
        model.addAttribute("cartItems", cartService.getAll());
        model.addAttribute("counterProducts", cartService.getAll().size());
        return "index";
    }

    @GetMapping("/category")
    public String sortByCategory(@RequestParam("categoryId") int categoryId, Model model) {
        model.addAttribute("products", productService.getByCategoryId(categoryId));
        return basicPage(model);
    }

    @GetMapping("/supplier")
    public String sortBySuppliers(@RequestParam("suppliersId") int suppliersId, Model model) {
        model.addAttribute("products",productService.getBySuppliers(suppliersId));
        return basicPage(model);
    }

    @GetMapping("/add/{id}")
    public String addProductToCart(@PathVariable("id") int id) {
        Optional<Product> oProduct = Optional.ofNullable(productService.find(id));
        if(oProduct.isPresent()){
            Product product = oProduct.get();
            cartService.add(product);
        }
        return "redirect:/";
    }

    @GetMapping("/remove/{id}")
    public String removeProductToCart(@PathVariable("id") int id) {
        Optional<Product> oProduct = Optional.ofNullable(productService.find(id));
        if(oProduct.isPresent()){
            Product product = oProduct.get();
            cartService.remove(product);
        }
        return "redirect:/";
    }

    @GetMapping("/clear")
    public String clearCart(){
        cartService.clearCart();
        return "redirect:/";

    }


}
