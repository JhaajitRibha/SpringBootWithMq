package com.ajit.active.mq.application.convertors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ajit.active.mq.application.Forms.ProductForm;
import com.ajit.active.mq.application.productEntities.Product;

@Component
public class ProductToProductForm implements Converter<Product, ProductForm> {
    @Override
    public ProductForm convert(Product product) {
        ProductForm productForm = new ProductForm();
        productForm.setId(product.getId());
        productForm.setDescription(product.getDescription());
        productForm.setPrice(product.getPrice());
        productForm.setImageUrl(product.getImageUrl());
        return productForm;
    }

	
	
}
