package com.ecommerce.producto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    private String codigo;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;
    @NotNull(message = "El precio es obligatorio")
     @Min(value = 0, message = "El precio no puede ser negativo")
    private Double precio;

    private String categoria;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

     @NotNull(message = "El estado activo es obligatorio")
    private Boolean activo;

}
