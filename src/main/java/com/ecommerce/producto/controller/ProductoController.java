package com.ecommerce.producto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.ecommerce.producto.assembler.ProductoModelAssembler;

import com.ecommerce.producto.model.Producto;
import com.ecommerce.producto.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;;

@Tag(name="Productos", description = "API de gestión de productos")
@RestController
@RequestMapping ("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoModelAssembler assembler;

    //listar productos
    @Operation(summary = "Listar todos los productos")
    @GetMapping
    public CollectionModel<EntityModel<Producto>> listar() {

    List<Producto> productos = productoService.listar();

    return assembler.toCollectionModel(productos);
    }

    //Buscar por id
    @Operation(summary = "Buscar producto por Id")
    @GetMapping("/{id}")
    public EntityModel<Producto> buscarPorId(@PathVariable Integer id) {

    Producto producto = productoService.buscarPorId(id);

    return assembler.toModel(producto);
    }

    //Guardar producto
    @Operation(summary = "Crear nuevo producto") 
    @PostMapping
    public Producto guardar(@Valid @RequestBody Producto producto) {
        return productoService.guardar(producto);
    }


    //Actualizar producto
    @Operation(summary = "Actualizar un producto")
    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Integer id,
                                @Valid @RequestBody Producto producto) {
        return productoService.actualizar(id, producto);
    }
                              
    
    //Eliminar producto
    @Operation(summary = "Eliminar un producto")
    @DeleteMapping ("/{id}")
    public void eliminar(@PathVariable Integer id){
         productoService.eliminar(id);
    }
    
}