package com.leduy.backend.controller.product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.leduy.backend.dto.request.ProductDetailRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.ProductDetailResponse;
import com.leduy.backend.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/product-detail")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> paginationProductDetail(@RequestBody PaginationRequest paginationRequest)throws JsonProcessingException {
        ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(
                productDetailService.paginationProductDetail(paginationRequest), HttpStatus.OK);
        return response;
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<ProductDetailResponse> getProductDetailById(@PathVariable String id) {
        ProductDetailResponse productDetailResponse = productDetailService.findById(id);
        return new ResponseEntity<>(productDetailResponse, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ProductDetailResponse> saveProductDetail(@RequestBody ProductDetailRequest productDetailRequest) {
        ProductDetailResponse productDetailResponse = productDetailService.save(productDetailRequest);
        return new ResponseEntity<>(productDetailResponse, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ProductDetailResponse> updateProductDetail(@PathVariable("id") String id,
                                                                     @RequestBody ProductDetailRequest productDetailRequest) {
        productDetailRequest.setId(id);
        ProductDetailResponse ProductDetailResponse = productDetailService.update(productDetailRequest);
        return new ResponseEntity<>(ProductDetailResponse, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteProductDetail(@PathVariable String id) {
        boolean deleted = productDetailService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
