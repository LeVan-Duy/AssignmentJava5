package com.leduy.backend.controller.product;

import com.leduy.backend.dto.request.ShopOrderRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.ShopOrderResponse;
import com.leduy.backend.service.ShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/shop-order")
public class ShopOrderController {
    @Autowired
    private ShopOrderService shopOrderService;

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> paginationShopOrder(PaginationRequest paginationRequest) {
        ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(
                shopOrderService.paginationShopOrder(paginationRequest), HttpStatus.OK);
        return response;
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<ShopOrderResponse> getShopOrderById(@PathVariable String id) {
        ShopOrderResponse shopOrderResponse = shopOrderService.findById(id);
        return new ResponseEntity<>(shopOrderResponse, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ShopOrderResponse> saveShopOrder(@RequestBody ShopOrderRequest shopOrderRequest) {
        ShopOrderResponse shopOrderResponse = shopOrderService.save(shopOrderRequest);
        return new ResponseEntity<>(shopOrderResponse, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ShopOrderResponse> updateShopOrder(@PathVariable("id") String id,
                                                             @RequestBody ShopOrderRequest shopOrderRequest) {
        shopOrderRequest.setId(id);
        ShopOrderResponse shopOrderResponse = shopOrderService.update(shopOrderRequest);
        return new ResponseEntity<>(shopOrderResponse, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteShopOrder(@PathVariable String id) {
        boolean deleted = shopOrderService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
