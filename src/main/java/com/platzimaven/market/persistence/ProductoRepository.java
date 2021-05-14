package com.platzimaven.market.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.platzimaven.market.domain.Product;
import com.platzimaven.market.domain.repository.ProductRepository;
import com.platzimaven.market.persistence.crud.ProductoCrudRepository;
import com.platzimaven.market.persistence.entity.Producto;
import com.platzimaven.market.persistence.mapper.ProductMapper;



@Repository
public class ProductoRepository implements ProductRepository {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ProductoCrudRepository productoCrudRepository;

	@Override
	public List<Product> getAll() {
		List<Producto> productos = (List<Producto>) this.productoCrudRepository.findAll();
		return this.productMapper.toProducts(productos);
	}

	@Override
	public Product save(Product product) {
		Producto producto = this.productMapper.toProducto(product);
		return this.productMapper.toProduct(this.productoCrudRepository.save(producto));
	}

	@Override
	public void delete(int idProducto) {
		this.productoCrudRepository.deleteById(idProducto);
	}

	@Override
	public Optional<List<Product>> getByCategory(int idCategory) {
		List<Producto> productos = (List<Producto>) this.productoCrudRepository
				.findByIdCategoriaOrderByNombreAsc(idCategory);
		return Optional.of(this.productMapper.toProducts(productos));
	}

	@Override
	public Optional<List<Product>> getScarseProducts(int quantity) {
		// TODO Auto-generated method stub
		Optional<List<Producto>> productos = this.productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,
				true);
		return productos.map(products -> this.productMapper.toProducts(products));
	}

	@Override
	public Optional<Product> getProduct(int productId) {
		return this.productoCrudRepository.findById(productId).map(product -> this.productMapper.toProduct(product));
	}

}
