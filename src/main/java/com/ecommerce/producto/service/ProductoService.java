package com.ecommerce.producto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.producto.model.Producto;
import com.ecommerce.producto.repository.ProductoRepository;



@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizar(Integer id, Producto productoActualizado) {
        Producto producto = buscarPorId(id);
        producto.setCodigo(productoActualizado.getCodigo());
        producto.setNombre(productoActualizado.getNombre());
        producto.setDescripcion(productoActualizado.getDescripcion());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setCategoria(productoActualizado.getCategoria());
        producto.setStock(productoActualizado.getStock());
        producto.setActivo(productoActualizado.getActivo());
        return productoRepository.save(producto);
    }

    public Producto actualizarStock(Integer id, Integer cantidad){

    Producto producto = productoRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Producto no existe"));
    int nuevoStock = producto.getStock() + cantidad;
    if(nuevoStock < 0){
        throw new RuntimeException(
            "Stock insuficiente"
        );
    }
    producto.setStock(nuevoStock);
    return productoRepository.save(producto);
}

    public void eliminar(Integer id) {
        productoRepository.deleteById(id);
    }

}