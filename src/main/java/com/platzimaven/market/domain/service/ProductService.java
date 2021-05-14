package com.platzimaven.market.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzimaven.market.domain.Product;
import com.platzimaven.market.domain.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productoRepository;

	public List<Product> getAll() {
		return this.productoRepository.getAll();
	}

	public Optional<Product> getProduct(int productId) {
		return this.productoRepository.getProduct(productId);
	}

	public Optional<List<Product>> getByCategory(int categoryId) {
		return this.productoRepository.getByCategory(categoryId);
	}

	public Product save(Product product) {
		return this.productoRepository.save(product);
	}

	public boolean delete(int productId) {
		return getProduct(productId).map(product -> {
			this.productoRepository.delete(productId);
			return true;
		}).orElse(false);
	}

}
