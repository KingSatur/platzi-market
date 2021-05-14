package com.platzimaven.market.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.platzimaven.market.domain.Purchase;
import com.platzimaven.market.persistence.entity.Compra;

@Mapper(componentModel = "spring", uses = { PurchaseItemMapper.class })
public interface PurchaseMapper {

	@Mappings({ @Mapping(source = "idCompra", target = "purchaseId"),
			@Mapping(source = "idCliente", target = "clienteId"), @Mapping(source = "fecha", target = "date"),
			@Mapping(source = "medioPago", target = "paymenMethod"), @Mapping(source = "estado", target = "state"),
			@Mapping(source = "comentario", target = "comment"), @Mapping(source = "productos", target = "items"), })
	Purchase toPurchase(Compra compra);

	/**
	 * Automaticamente adquiere toda la configuracion para convertir cada Compra a
	 * un Purchase
	 * 
	 * @param compras
	 * @return
	 */
	List<Purchase> toPurchases(List<Compra> compras);

	@InheritInverseConfiguration
	@Mapping(target = "cliente", ignore = true)
	Compra toCompra(Purchase purchase);

}
