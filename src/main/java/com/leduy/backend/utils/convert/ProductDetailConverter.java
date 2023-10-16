package com.leduy.backend.utils.convert;

import com.leduy.backend.dto.request.BrandRequest;
import com.leduy.backend.dto.request.ColorRequest;
import com.leduy.backend.dto.request.ProductRequest;
import com.leduy.backend.dto.request.SizeRequest;
import com.leduy.backend.entity.Brand;
import com.leduy.backend.entity.Color;
import com.leduy.backend.entity.Product;
import com.leduy.backend.entity.Size;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailConverter {
    public Converter<BrandRequest, Brand> brandConverter() {
        return context -> {
            BrandRequest source = context.getSource();
            Brand destination = context.getDestination();
            destination.setId(source.getId());
            destination.setName(source.getName());
            return destination;
        };
    }
    public Converter<SizeRequest, Size> sizeConverter() {
        return context -> {
            SizeRequest source = context.getSource();
            Size destination = context.getDestination();
            destination.setId(source.getId());
            destination.setName(source.getName());
            return destination;
        };
    }
    public Converter<ColorRequest, Color> colorConverter() {
        return context -> {
            ColorRequest source = context.getSource();
            Color destination = context.getDestination();
            destination.setId(source.getId());
            destination.setName(source.getName());
            return destination;
        };
    }
    public Converter<ProductRequest, Product> productConverter() {
        return context -> {
            ProductRequest source = context.getSource();
            Product destination = context.getDestination();
            destination.setId(source.getId());
            destination.setName(source.getName());
            return destination;
        };
    }
}
