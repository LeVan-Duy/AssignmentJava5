package com.leduy.backend.controller.product;

import com.leduy.backend.dto.request.OrderDetailRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.OrderDetailResponse;
import com.leduy.backend.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/order-detail")
@CrossOrigin(origins = {"*"})
public class OrderDetailController {
    @Autowired
    private OrderDetailService OrderDetailService;

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> paginationOrderDetail(PaginationRequest paginationRequest) {
        ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(
                OrderDetailService.paginationOrderDetail(paginationRequest), HttpStatus.OK);
        return response;
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<OrderDetailResponse> getOrderDetailById(@PathVariable String id) {
        OrderDetailResponse orderDetailResponse = OrderDetailService.findById(id);
        return new ResponseEntity<>(orderDetailResponse, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<OrderDetailResponse> saveOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest) {
        OrderDetailResponse orderDetailResponse = OrderDetailService.save(orderDetailRequest);
        return new ResponseEntity<>(orderDetailResponse, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<OrderDetailResponse> updateOrderDetail(@PathVariable("id") String id,
                                                                 @RequestBody OrderDetailRequest orderDetailRequest) {
        orderDetailRequest.setId(id);
        OrderDetailResponse OrderDetailResponse = OrderDetailService.update(orderDetailRequest);
        return new ResponseEntity<>(OrderDetailResponse, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable String id) {
        boolean deleted = OrderDetailService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
