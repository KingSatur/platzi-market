package com.platzimaven.market.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.platzimaven.market.domain.Purchase;
import com.platzimaven.market.domain.repository.PurchaseRepository;
import com.platzimaven.market.persistence.crud.CompraCrudRepository;
import com.platzimaven.market.persistence.entity.Compra;
import com.platzimaven.market.persistence.mapper.PurchaseMapper;

@Repository
public class CompraRepository implements PurchaseRepository {

	@Autowired
	private CompraCrudRepository compraCrudRepository;

	@Autowired
	private PurchaseMapper purchaseMapper;

	@Override
	public List<Purchase> getAll() {
		return this.purchaseMapper.toPurchases((List<Compra>) this.compraCrudRepository.findAll());
	}

	@Override
	public Optional<List<Purchase>> getByCliente(String clientId) {
		return this.compraCrudRepository.findByIdCliente(clientId)
				.map(compras -> this.purchaseMapper.toPurchases(compras));
	}

	@Override
	public Purchase save(Purchase purchase) {
		Compra compra = this.purchaseMapper.toCompra(purchase);
		compra.getProductos().forEach(producto -> producto.setCompra(compra));

		return this.purchaseMapper.toPurchase(this.compraCrudRepository.save(compra));
	}

}
