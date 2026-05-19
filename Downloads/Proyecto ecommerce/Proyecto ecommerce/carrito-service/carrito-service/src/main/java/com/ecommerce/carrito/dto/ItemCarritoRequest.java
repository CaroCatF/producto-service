package com.ecommerce.carrito.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemCarritoRequest {

    @NotNull(message = "El productoId es obligatorio")
    private Integer productoId;

    @NotNull(message = "El nombre del producto es obligatorio")
    private String nombreProducto;

    @NotNull(message = "El precio unitario es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Double precioUnitario;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad mínima es 1")
    private Integer cantidad;
}
