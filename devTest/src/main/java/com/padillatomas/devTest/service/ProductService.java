package com.padillatomas.devTest.service;

import com.padillatomas.devTest.model.response.ProductResponse;

import java.util.ArrayList;

public interface ProductService {

    ArrayList<ProductResponse> getSimilarProducts(String productId);
}
