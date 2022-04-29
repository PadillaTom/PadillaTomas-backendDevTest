package com.padillatomas.devTest.service.impl;

import com.padillatomas.devTest.model.response.ProductResponse;
import com.padillatomas.devTest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private  RestTemplate restTemplate;

//    @Autowired
//    public ProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
//        this.restTemplate = restTemplateBuilder.errorHandler(
//                new CustomRestTemplateExceptionHandler()
//        ).build();
//    }
    @Autowired
    public ProductServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private static final String GET_PRODUCT_SIMILARIDS = "http://localhost:3001/product/{productId}/similarids";
    private static final String GET_PRODUCTID = "http://localhost:3001/product/{productId}";

    @Override
    public ArrayList<ProductResponse> getSimilarProducts(String productId) {

//        Get SimilarIDS by ProductId:
        List<String> similarIds = new ArrayList<>(
                Arrays.asList(
                        restTemplate.getForObject(
                                GET_PRODUCT_SIMILARIDS.replace("{productId}", productId),
                                String[].class
                        )
                )
        );

//        Response:
        return similarIds.stream()
                .map((singleId)-> {
                    ProductResponse myProduct = restTemplate.getForObject(
                            GET_PRODUCTID.replace("{productId}", singleId),
                            ProductResponse.class
                    );
                    return myProduct;
                }
                ).collect(Collectors.toCollection(ArrayList::new));
    }

}
