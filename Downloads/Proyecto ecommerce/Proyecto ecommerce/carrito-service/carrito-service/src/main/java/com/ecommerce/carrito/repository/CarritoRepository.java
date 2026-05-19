package com.ecommerce.carrito.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.carrito.model.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {

    // Buscar el carrito de un usuario por su email (extraído del JWT)
    Optional<Carrito> findByUsuarioEmail(String usuarioEmail);

}
