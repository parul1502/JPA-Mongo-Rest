package com.myapp.spring.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.myapp.spring.model.Product;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends MongoRepository<Product, Long> {

	List<Product> findByProductName(@Param("productName")String productName); 
	
	@Query("{price : {$lt : ?0, $gt : ?1}}")
	List<Product> findByPrice(double maxPrice, double minPrice);

	@Query("{productName : {$ne : ?0}}")
	List<Product> findByProductNameNotEqual(String productName);

	@Query("{'productName' : null}")
	List<Product> findByProductNameIsNull();

	@Query("{'productName' : {$ne : null}}")
	List<Product> findByProductNameIsNotNull();
	
}
