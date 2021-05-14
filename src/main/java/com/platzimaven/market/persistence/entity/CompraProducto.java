package com.platzimaven.market.persistence.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "compras_productos")
public class CompraProducto {

	@EmbeddedId
	private ComprasProductosPK id;
	private Integer cantidad;
	private Double total;
	private boolean estado;

	@ManyToOne()
	@MapsId("idCompra")
	@JoinColumn(name = "id_compra", insertable = false, updatable = false)
	private Compra compra;

	@ManyToOne()
	@JoinColumn(name = "id_producto", insertable = false, updatable = false)
	private Producto producto;
	
	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public ComprasProductosPK getId() {
		return id;
	}

	public void setId(ComprasProductosPK id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double numeric) {
		this.total = numeric;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
