package com.padillatomas.devTest.controller;

import com.padillatomas.devTest.model.response.ProductResponse;
import com.padillatomas.devTest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}/similar")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<ProductResponse> getSimilar(@PathVariable String productId){
        return productService.getSimilarProducts(productId);
    }

}
