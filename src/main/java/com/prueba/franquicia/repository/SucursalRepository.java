package com.prueba.franquicia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.franquicia.entity.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
	
	List<Sucursal> findByFranquiciaId(Long franquiciaId);
	
}
