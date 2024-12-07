package com.prueba.franquicia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.franquicia.entity.Franquicia;

@Repository
public interface FranquiciaRepository extends JpaRepository<Franquicia, Long> {}
