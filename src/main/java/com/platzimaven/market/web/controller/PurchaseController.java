package com.platzimaven.market.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzimaven.market.domain.Purchase;
import com.platzimaven.market.domain.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;

	@GetMapping
	public ResponseEntity<List<Purchase>> getAll() {
		return new ResponseEntity<List<Purchase>>(this.purchaseService.getAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase) {
		return new ResponseEntity<Purchase>(this.purchaseService.createPurchase(purchase), HttpStatus.OK);
	}

}
