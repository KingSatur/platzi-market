package com.platzimaven.market.persistence.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.platzimaven.market.persistence.entity.Producto;


/**
 * Al extender de CrudRepository ya extiende el estereotipo @Repository
 * 
 * @author Usuario
 *
 */
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

	@Query(value = "SELECT * FROM productos WHERE id_categoria = ? ORDER BY nombre ASC", nativeQuery = true)
	List<Producto> getByCategoria(int idCategoria);

	List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

	/**
	 * Encontrar los productos que tienen un stock menor al indicado y el estado sea
	 * igual al del metodo
	 * 
	 * @param cantidadStock
	 * @param estado
	 * @return
	 */
	Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
