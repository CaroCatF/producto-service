package com.ecommerce.carrito.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.carrito.dto.ItemCarritoRequest;
import com.ecommerce.carrito.model.Carrito;
import com.ecommerce.carrito.model.ItemCarrito;
import com.ecommerce.carrito.service.CarritoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carrito")
@Validated
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    // GET /carrito/todos — listar todos los carritos (solo admin)
    @GetMapping("/todos")
    public List<Carrito> listarTodos() {
        return carritoService.listarTodos();
    }

    // GET /carrito — ver MI carrito (email extraído del JWT)
    @GetMapping
    public Carrito obtenerMiCarrito(@AuthenticationPrincipal String email) {
        return carritoService.obtenerOCrearCarrito(email);
    }

    // POST /carrito/items — agregar un producto a MI carrito
    // Body ejemplo:
    // {
    //   "productoId": 5,
    //   "nombreProducto": "Cuaderno universitario",
    //   "precioUnitario": 2990.0,
    //   "cantidad": 2
    // }
    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public Carrito agregarItem(@AuthenticationPrincipal String email,
                               @Valid @RequestBody ItemCarritoRequest request) {
        ItemCarrito item = new ItemCarrito();
        item.setProductoId(request.getProductoId());
        item.setNombreProducto(request.getNombreProducto());
        item.setPrecioUnitario(request.getPrecioUnitario());
        item.setCantidad(request.getCantidad());
        return carritoService.agregarItem(email, item);
    }

    // PUT /carrito/items/{itemId} — actualizar cantidad de un item en MI carrito
    // Body ejemplo: { "cantidad": 3 }
    @PutMapping("/items/{itemId}")
    public Carrito actualizarCantidad(@AuthenticationPrincipal String email,
                                      @PathVariable Integer itemId,
                                      @Valid @RequestBody ItemCarritoRequest request) {
        return carritoService.actualizarCantidad(email, itemId, request.getCantidad());
    }

    // DELETE /carrito/items/{itemId} — eliminar un item de MI carrito
    @DeleteMapping("/items/{itemId}")
    public Carrito eliminarItem(@AuthenticationPrincipal String email,
                                @PathVariable Integer itemId) {
        return carritoService.eliminarItem(email, itemId);
    }

    // DELETE /carrito — vaciar MI carrito completo
    @DeleteMapping
    public Carrito vaciarCarrito(@AuthenticationPrincipal String email) {
        return carritoService.vaciarCarrito(email);
    }
}

