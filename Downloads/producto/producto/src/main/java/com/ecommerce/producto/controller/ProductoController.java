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

import com.ecommerce.producto.model.Producto;
import com.ecommerce.producto.service.ProductoService;

@RestController
@RequestMapping ("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    //listar productos
    @GetMapping
    public List<Producto>listar(){
        return productoService.listar();
    }

    //Buscar por id
    @GetMapping("/{id}")
    public Producto buscarPorId(@PathVariable Integer id){
        return productoService.buscarPorId(id);
    }

    //Guardar producto
    @PostMapping
    public Producto guardar(@RequestBody Producto producto){
        return productoService.guardar(producto);
    }

    //Actualizar producto
    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Integer id,
                                @RequestBody Producto producto){
        return productoService.actualizar(id,producto);
                                }
    
    //Eliminar producto
    @DeleteMapping ("/{id}")
    public void eliminar(@PathVariable Integer id){
         productoService.eliminar(id);
    }
    
}
