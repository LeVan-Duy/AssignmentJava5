package com.leduy.backend.utils;

import com.leduy.backend.entity.*;
import com.leduy.backend.infrastructure.exception.ResourceNotFoundException;
import com.leduy.backend.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class GetEntityById {
    private ProductDetailRepository productDetailRepository;
    private BrandRepository brandRepository;
    private ColorRepository colorRepository;
    private SizeRepository sizeRepository;
    private ProductRepository productRepository;
    private EmployeeRepository employeeRepository;
    private CustomerRepository customerRepository;
    private ShopOrderRepository shopOrderRepository;
    private ModelMapper mapper;

    @Autowired
    public GetEntityById(ProductDetailRepository productDetailRepository,
                         BrandRepository brandRepository,
                         ColorRepository colorRepository,
                         SizeRepository sizeRepository,
                         ProductRepository productRepository,
                         EmployeeRepository employeeRepository,
                         CustomerRepository customerRepository,
                         ShopOrderRepository shopOrderRepository,
                         ModelMapper mapper) {
        this.productDetailRepository = productDetailRepository;
        this.brandRepository = brandRepository;
        this.colorRepository = colorRepository;
        this.sizeRepository = sizeRepository;
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.shopOrderRepository = shopOrderRepository;
        this.mapper = mapper;
    }


    public Product getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new ResourceNotFoundException("Product not found with ID: " + id);
    }

    public Brand getBrandById(String id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            return brand.get();
        }
        throw new ResourceNotFoundException("Brand not found with ID: " + id);
    }

    public Size getSizeById(String id) {
        Optional<Size> size = sizeRepository.findById(id);
        if (size.isPresent()) {
            return size.get();
        }
        throw new ResourceNotFoundException("Size not found with ID: " + id);
    }

    public Color getColorById(String id) {
        Optional<Color> color = colorRepository.findById(id);
        if (color.isPresent()) {
            return color.get();
        }
        throw new ResourceNotFoundException("Color not found with ID: " + id);
    }

    public Employee getEmployeeById(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        throw new ResourceNotFoundException("Employee not found with ID: " + id);
    }

    public Customer getCustomerById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new ResourceNotFoundException("Customer not found with ID: " + id);
    }

    public ProductDetail getProductDetailById(String id) {
        Optional<ProductDetail> x = productDetailRepository.findById(id);
        if (x.isPresent()) {
            return x.get();
        }
        throw new ResourceNotFoundException("Customer not found with ID: " + id);
    }

    public ShopOrder getShopOrderById(String id) {
        Optional<ShopOrder> x = shopOrderRepository.findById(id);
        if (x.isPresent()) {
            return x.get();
        }
        throw new ResourceNotFoundException("Customer not found with ID: " + id);
    }
}
