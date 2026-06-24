package com.ecommerce.producto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ecommerce.producto.model.Producto;
import com.ecommerce.producto.repository.ProductoRepository;


public class ProductoServiceTest {
    

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    //1. PRUEBA LISTAR PRODUCTOS
    @Test
    void deberiaListarProductos(){
        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Notebook");
        when (productoRepository.findAll())
            .thenReturn(List.of(producto));

        List<Producto> resultado =
            productoService.listar();

        assertEquals(1, resultado.size());
        assertEquals("Notebook",
                resultado.get(0).getNombre());
        
        verify(productoRepository)
            .findAll();
    }

    //2.PRUEBA BUSCAR PRODUCTO POR ID
    @Test
    void deberiaBuscarProductoPorId(){
        Producto producto =new Producto();
        producto.setId(1);
        producto.setNombre("Mouse");

        when(productoRepository.findById(1))
            .thenReturn(Optional.of(producto));
        Producto resultado =
            productoService.buscarPorId(1);
        
        assertEquals("Mouse",
            resultado.getNombre());
        verify(productoRepository)
            .findById(1);
    }

    //3. PRUEBA PARA GUARDAR PRODUCTO
    @Test
    void deberiaGuardarProducto(){
        Producto producto = new Producto();
        producto.setNombre("Teclado");

        when(productoRepository.save(producto))
            .thenReturn(producto);
        Producto resultado =
            productoService.guardar(producto);
        assertEquals("Teclado",
            resultado.getNombre());
        verify(productoRepository)
            .save(producto);
    }
    //4.PRUEBA ACTUALIZAR STOCK CORRECTAMENTE
    @Test
    void deberiaActualizarStock(){
        Producto producto = new Producto();
        producto.setId(1);
        producto.setStock(10);

        when(productoRepository.findById(1))
            .thenReturn(Optional.of(producto));
        when(productoRepository.save(producto))
            .thenReturn(producto);
        Producto resultado =
            productoService.actualizarStock(1,-3);
        assertEquals(7,
                 resultado.getStock());
        verify(productoRepository)
                .save(producto);
    }
}

