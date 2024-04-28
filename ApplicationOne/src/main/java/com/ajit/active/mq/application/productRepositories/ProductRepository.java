package com.ajit.active.mq.application.productRepositories;

import org.springframework.data.repository.CrudRepository;

import com.ajit.active.mq.application.productEntities.Product;


public interface ProductRepository extends CrudRepository<Product,Long>{

}
