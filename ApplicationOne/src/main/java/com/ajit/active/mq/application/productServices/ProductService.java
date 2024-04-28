package com.ajit.active.mq.application.productServices;

import java.util.List;

import com.ajit.active.mq.application.Forms.ProductForm;
import com.ajit.active.mq.application.productEntities.Product;

public interface ProductService {
	 List<Product> listAll();

	    Product getById(Long id);

	    Product saveOrUpdate(Product product);

	    void delete(Long id);

	    Product saveOrUpdateProductForm(ProductForm productForm);

	    void sendMessage(String id);

}
