package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Producto;
import com.bonusGo.Bonus.Go.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto registrarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(int id, Producto nuevoProducto) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        productoExistente.setNombre(nuevoProducto.getNombre());
        productoExistente.setDescripcion(nuevoProducto.getDescripcion());
        productoExistente.setTipo(nuevoProducto.getTipo());
        productoExistente.setCoste(nuevoProducto.getCoste());
        productoExistente.setImagen(nuevoProducto.getImagen());

        return productoRepository.save(productoExistente);
    }

    @Override
    public void eliminarProducto(int id) {
        Optional<Producto> producto = productoRepository.findById(id);

        if (!producto.isPresent()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> listaProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto buscarProductoId(int id) {
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}
