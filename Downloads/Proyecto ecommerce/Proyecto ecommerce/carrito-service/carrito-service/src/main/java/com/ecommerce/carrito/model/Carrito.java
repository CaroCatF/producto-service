package com.ecommerce.carrito.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carritos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carrito_seq")
    @SequenceGenerator(name = "carrito_seq", sequenceName = "carrito_seq", allocationSize = 1)
    private Integer id;

    // Email del usuario dueño del carrito (extraído del JWT, único por usuario)
    @Column(name = "usuario_email", unique = true, nullable = false)
    private String usuarioEmail;

    // Un carrito tiene muchos items
    // CascadeType.ALL: si se borra el carrito, se borran sus items también
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrito> items = new ArrayList<>();

    // Total calculado (precio x cantidad de cada item)
    private Double total;

}
