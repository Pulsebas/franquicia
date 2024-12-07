package com.prueba.franquicia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.franquicia.entity.Producto;
import com.prueba.franquicia.entity.Sucursal;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	List<Producto> findBySucursal(Sucursal sucursal);
	
}
