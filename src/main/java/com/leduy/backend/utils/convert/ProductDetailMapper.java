//package com.leduy.backend.utils.convert;
//
//import com.leduy.backend.dto.request.ProductDetailRequest;
//import com.leduy.backend.dto.response.ProductDetailResponse;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.PropertyMap;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ProductDetailMapper {
//    private final ModelMapper modelMapper;
//    private final ProductDetailConverter productDetailConverter;
//
//    @Autowired
//    public ProductDetailMapper(ModelMapper modelMapper, ProductDetailConverter productDetailConverter) {
//        this.modelMapper = modelMapper;
//        this.productDetailConverter = productDetailConverter;
//    }
//
//    public ProductDetailResponse mapProductDetailResponse(ProductDetailConverter productDetailConverter) {
//        return modelMapper.map(productDetailConverter, ProductDetailResponse.class);
//    }
//
//    public ProductDetailConverter mapProductDetail(ProductDetailRequest productDetailRequest) {
//        modelMapper.addConverter(productDetailConverter.brandConverter());
//        modelMapper.addConverter(productDetailConverter.productConverter());
//        modelMapper.addConverter(productDetailConverter.colorConverter());
//        modelMapper.addConverter(productDetailConverter.sizeConverter());
//        // Đăng ký các Converter cho Size và Product (tương tự)
//
//        modelMapper.addMappings(new PropertyMap<ProductDetailRequest, ProductDetailConverter>() {
//            protected void configure() {
//                map(source.getGender(), destination.getGender());
//                map(source.getDescription(), destination.getDescription());
//                map(source.getPrice(), destination.getPrice());
//
//                using(productDetailConverter.brandConverter()).map(source.getBrandRequest()).setBrand(null);
//                // Sử dụng các Converter cho Size và Product (tương tự)
//            }
//        });
//        return modelMapper.map(productDetailRequest, ProductDetailConverter.class);
//    }
//}
