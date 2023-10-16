package com.leduy.backend.controller.product;

import com.leduy.backend.dto.request.ColorRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.ColorResponse;
import com.leduy.backend.service.ColorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/color")
@CrossOrigin(origins = {"*"})
public class ColorController {
    @Autowired
    private ColorService colorService;

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> paginationColor( PaginationRequest paginationRequest) {
        ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(
                colorService.paginationColor(paginationRequest), HttpStatus.OK);
        return response;
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<ColorResponse> getColorById(@PathVariable String id) {
        ColorResponse colorResponse = colorService.findById(id);
        return new ResponseEntity<>(colorResponse, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ColorResponse> saveColor(@Valid @RequestBody ColorRequest colorRequest) {
        ColorResponse colorResponse = colorService.save(colorRequest);
        return new ResponseEntity<>(colorResponse, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ColorResponse> updateColor(@PathVariable("id") String id,
                                                     @RequestBody ColorRequest colorRequest) {
        colorRequest.setId(id);
        ColorResponse colorResponse = colorService.update(colorRequest);
        return new ResponseEntity<>(colorResponse, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable String id) {
        boolean deleted = colorService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
