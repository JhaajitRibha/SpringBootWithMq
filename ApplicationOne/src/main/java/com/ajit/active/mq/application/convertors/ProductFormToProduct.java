package com.ajit.active.mq.application.convertors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ajit.active.mq.application.Forms.ProductForm;
import com.ajit.active.mq.application.productEntities.Product;

@Component
public class ProductFormToProduct implements Converter<ProductForm,Product>{

	@SuppressWarnings({ "removal", "deprecation" })
	@Override
	public Product convert(ProductForm productForm) {
		 Product product = new Product();
	        if (productForm.getId() != null  && !StringUtils.isEmpty(productForm.getId())) {
	            product.setId(new Long(productForm.getId()));
	        }
	        product.setDescription(productForm.getDescription());
	        product.setPrice(productForm.getPrice());
	        product.setImageUrl(productForm.getImageUrl());
	        return product;
	}
	

}
