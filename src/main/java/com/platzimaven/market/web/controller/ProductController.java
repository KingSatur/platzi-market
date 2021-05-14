package com.platzimaven.market.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.platzimaven.market.domain.Product;
import com.platzimaven.market.domain.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> getProducts() {
		return new ResponseEntity<List<Product>>(this.productService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ApiOperation("Get all products from database")
	@ApiResponse(code = 200, message = "Fetched")
	public ResponseEntity<Product> getProduct(@PathVariable("id") int productId) {

		Optional<Product> optional = this.productService.getProduct(productId);
		return optional.map(product -> new ResponseEntity<Product>(product, HttpStatus.OK))
				.orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
	}

	@ApiOperation("Search product with given category")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Fetched"),
		@ApiResponse(code = 404, message = "There are not products with that category Id")
	})
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<Product>> getByCategory(@ApiParam(value = "Id of the category", required = true, example = "7") @PathVariable int categoryId) {
		return this.productService.getByCategory(categoryId)
				.map(products -> new ResponseEntity<List<Product>>(products, HttpStatus.OK))
				.orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<Product> save(@RequestBody Product product) {
		return new ResponseEntity<Product>(this.productService.save(product), HttpStatus.CREATED);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity delete(@PathVariable int productId) {
		if(this.productService.delete(productId)) {
			return new ResponseEntity(HttpStatus.OK);
		}
		else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

}
