package com.leduy.backend.controller.product;

import com.leduy.backend.dto.request.ProductRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.ProductResponse;
import com.leduy.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> paginationColor(PaginationRequest paginationRequest) {
        ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(
                productService.paginationProduct(paginationRequest), HttpStatus.OK);
        return response;
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<ProductResponse> getColorById(@PathVariable String id) {
        ProductResponse productResponse = productService.findById(id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ProductResponse> saveColor(@RequestBody ProductRequest ProductRequest) {
        ProductResponse productResponse = productService.save(ProductRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ProductResponse> updateColor(@PathVariable("id") String id,
                                                       @RequestBody ProductRequest ProductRequest) {
        ProductRequest.setId(id);
        ProductResponse productResponse = productService.update(ProductRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable String id) {
        boolean deleted = productService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
