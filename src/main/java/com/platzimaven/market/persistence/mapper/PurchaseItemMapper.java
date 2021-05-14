package com.platzimaven.market.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.platzimaven.market.domain.PurchaseItem;
import com.platzimaven.market.persistence.entity.CompraProducto;

@Mapper(componentModel = "spring",uses = {ProductMapper.class})
public interface PurchaseItemMapper {
	
	@Mappings({
		@Mapping(source = "id.idProducto", target = "productId"),
		@Mapping(source = "cantidad", target = "quantity"),
		//No es necesario mapear la propiedad total, dado que tanto para el source como para el target tienen el mismo nombre
		@Mapping(source = "estado",target = "active")
	})
	PurchaseItem toPurchaseItem(CompraProducto compraProducto);
	
	@InheritInverseConfiguration
	@Mappings({
		@Mapping(target = "compra", ignore = true),
		@Mapping(target = "producto", ignore = true),
		@Mapping(target = "id.idCompra", ignore = true)
	})
	CompraProducto toCompraProducto(PurchaseItem purchaseItem);
}
