package com.leduy.backend.controller.product;

import com.leduy.backend.dto.request.SizeRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.SizeResponse;
import com.leduy.backend.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/size")
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> paginationSize( PaginationRequest paginationRequest) {
        ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(
                sizeService.paginationSize(paginationRequest), HttpStatus.OK);
        return response;
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<SizeResponse> getSizeById(@PathVariable String id) {
        SizeResponse sizeResponse = sizeService.findById(id);
        return new ResponseEntity<>(sizeResponse, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<SizeResponse> saveSize(@RequestBody SizeRequest sizeRequest) {
        SizeResponse sizeResponse = sizeService.save(sizeRequest);
        return new ResponseEntity<>(sizeResponse, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<SizeResponse> updateSize(@PathVariable("id") String id,
                                                     @RequestBody SizeRequest sizeRequest) {
        sizeRequest.setId(id);
        SizeResponse sizeResponse = sizeService.update(sizeRequest);
        return new ResponseEntity<>(sizeResponse, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteSize(@PathVariable String id) {
        boolean deleted = sizeService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
