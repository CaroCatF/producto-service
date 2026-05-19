package com.ecommerce.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.producto.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}