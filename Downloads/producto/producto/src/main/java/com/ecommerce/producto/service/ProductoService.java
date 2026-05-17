package com.ecommerce.producto.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.producto.model.Producto;
import com.ecommerce.producto.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;


    private List<Producto>listaProductos= new ArrayList<>();

    //Listar productos
    public List<Producto>listar(){
        return productoRepository.findAll();
    }

    //Busacar por id
    public Producto buscarPorId(Integer id) {
    return productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    //Guardar producto
    public Producto guardar(Producto producto){
         listaProductos.add(producto);
         return productoRepository.save(producto);
    }

    //Actualizar producto
    public Producto actualizar (Integer id, Producto productoActualizado){
        Producto producto = buscarPorId(id);

        if (producto != null){
            producto.setCodigo(productoActualizado.getCodigo());
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setCategoria(productoActualizado.getCategoria());
            producto.setStock(productoActualizado.getStock());
            producto.setActivo(productoActualizado.getActivo());

            return productoRepository.save(producto);
        }
        return null;
    }

    //Eiminar producto
    public void eliminar(Integer id){
        productoRepository.deleteById(id);
    }
    
}
