package com.platzimaven.market.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzimaven.market.domain.Purchase;
import com.platzimaven.market.domain.repository.PurchaseRepository;


@Service
public class PurchaseService {
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	

	public List<Purchase> getAll() {
		return this.purchaseRepository.getAll();
	}
	
	public Purchase createPurchase(Purchase purchase) {
		return this.purchaseRepository.save(purchase);
	}

}
