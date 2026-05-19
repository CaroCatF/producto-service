package com.ecommerce.carrito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "items_carrito")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_carrito_seq")
    @SequenceGenerator(name = "item_carrito_seq", sequenceName = "item_carrito_seq", allocationSize = 1)
    private Integer id;

    // Muchos items pertenecen a un carrito
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;

    // ID del producto (viene del microservicio producto, puerto 8082)
    private Integer productoId;

    // Nombre guardado al momento de agregar (por si el producto cambia después)
    private String nombreProducto;

    // Precio unitario guardado al momento de agregar
    private Double precioUnitario;

    // Cantidad de este producto en el carrito
    private Integer cantidad;

    // Subtotal = precioUnitario x cantidad (se calcula automáticamente)
    public Double getSubtotal() {
        if (precioUnitario != null && cantidad != null) {
            return precioUnitario * cantidad;
        }
        return 0.0;
    }

}
