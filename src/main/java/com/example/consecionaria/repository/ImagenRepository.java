package com.example.consecionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.consecionaria.entity.Imagen;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {
	

}
