package com.platzimaven.market.persistence.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.platzimaven.market.persistence.entity.Compra;
import com.platzimaven.market.persistence.entity.Producto;


public interface CompraCrudRepository extends CrudRepository<Compra, Integer>  {


	Optional<List<Compra>> findByIdCliente(String idCliente);
	
}
