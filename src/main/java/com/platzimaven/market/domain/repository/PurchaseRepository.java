package com.platzimaven.market.domain.repository;

import java.util.List;
import java.util.Optional;

import com.platzimaven.market.domain.Purchase;

public interface PurchaseRepository {

	public List<Purchase> getAll();

	public Optional<List<Purchase>> getByCliente(String clientId);

	Purchase save(Purchase purchase);

}
