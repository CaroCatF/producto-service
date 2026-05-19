package com.ecommerce.carrito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.carrito.model.Carrito;
import com.ecommerce.carrito.model.ItemCarrito;
import com.ecommerce.carrito.repository.CarritoRepository;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    // -------------------------------------------------------
    // Obtener el carrito del usuario autenticado (o crear uno)
    // -------------------------------------------------------
    public Carrito obtenerOCrearCarrito(String usuarioEmail) {
        return carritoRepository.findByUsuarioEmail(usuarioEmail)
                .orElseGet(() -> {
                    Carrito nuevo = new Carrito();
                    nuevo.setUsuarioEmail(usuarioEmail);
                    nuevo.setTotal(0.0);
                    return carritoRepository.save(nuevo);
                });
    }

    // -------------------------------------------------------
    // Agregar un producto al carrito
    // -------------------------------------------------------
    public Carrito agregarItem(String usuarioEmail, ItemCarrito nuevoItem) {
        Carrito carrito = obtenerOCrearCarrito(usuarioEmail);

        // Si el producto ya está en el carrito, solo suma la cantidad
        for (ItemCarrito item : carrito.getItems()) {
            if (item.getProductoId().equals(nuevoItem.getProductoId())) {
                item.setCantidad(item.getCantidad() + nuevoItem.getCantidad());
                recalcularTotal(carrito);
                return carritoRepository.save(carrito);
            }
        }

        // Si es un producto nuevo, lo agrega a la lista
        nuevoItem.setCarrito(carrito);
        carrito.getItems().add(nuevoItem);
        recalcularTotal(carrito);
        return carritoRepository.save(carrito);
    }

    // -------------------------------------------------------
    // Actualizar cantidad de un item
    // -------------------------------------------------------
    public Carrito actualizarCantidad(String usuarioEmail, Integer itemId, Integer nuevaCantidad) {
        Carrito carrito = obtenerOCrearCarrito(usuarioEmail);

        ItemCarrito itemEncontrado = carrito.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Item con id " + itemId + " no encontrado en el carrito"));

        if (nuevaCantidad <= 0) {
            carrito.getItems().remove(itemEncontrado);
        } else {
            itemEncontrado.setCantidad(nuevaCantidad);
        }

        recalcularTotal(carrito);
        return carritoRepository.save(carrito);
    }

    // -------------------------------------------------------
    // Eliminar un item del carrito
    // -------------------------------------------------------
    public Carrito eliminarItem(String usuarioEmail, Integer itemId) {
        Carrito carrito = obtenerOCrearCarrito(usuarioEmail);

        boolean eliminado = carrito.getItems().removeIf(item -> item.getId().equals(itemId));
        if (!eliminado) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Item con id " + itemId + " no encontrado en el carrito");
        }

        recalcularTotal(carrito);
        return carritoRepository.save(carrito);
    }

    // -------------------------------------------------------
    // Vaciar el carrito completo
    // -------------------------------------------------------
    public Carrito vaciarCarrito(String usuarioEmail) {
        Carrito carrito = obtenerOCrearCarrito(usuarioEmail);
        carrito.getItems().clear();
        carrito.setTotal(0.0);
        return carritoRepository.save(carrito);
    }

    // -------------------------------------------------------
    // Listar todos los carritos (útil para admin)
    // -------------------------------------------------------
    public List<Carrito> listarTodos() {
        return carritoRepository.findAll();
    }

    // -------------------------------------------------------
    // Recalcular el total del carrito (precio x cantidad de cada item)
    // -------------------------------------------------------
    private void recalcularTotal(Carrito carrito) {
        double total = carrito.getItems().stream()
                .mapToDouble(ItemCarrito::getSubtotal)
                .sum();
        carrito.setTotal(total);
    }

}

