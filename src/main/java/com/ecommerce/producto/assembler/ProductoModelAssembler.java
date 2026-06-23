
package com.ecommerce.producto.assembler;

import com.ecommerce.producto.controller.ProductoController;
import com.ecommerce.producto.model.Producto;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoModelAssembler
        implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    public EntityModel<Producto> toModel(Producto producto) {

        return EntityModel.of(producto,
                linkTo(methodOn(ProductoController.class)
                        .buscarPorId(producto.getId()))
                        .withSelfRel(),

                linkTo(methodOn(ProductoController.class)
                        .listar())
                        .withRel("productos"));
    }

    public CollectionModel<EntityModel<Producto>> toCollectionModel(List<Producto> productos) {

        List<EntityModel<Producto>> productosModel = productos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productosModel,
                linkTo(methodOn(ProductoController.class)
                        .listar())
                        .withSelfRel());
    }
}