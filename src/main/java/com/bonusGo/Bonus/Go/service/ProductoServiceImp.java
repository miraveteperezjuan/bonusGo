package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Producto;
import com.bonusGo.Bonus.Go.model.Tipo;
import com.bonusGo.Bonus.Go.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImp implements ProductoService {

    private ProductoRepository productoRepository;

    @Override
    public Producto registrarProducto(Producto producto, String tipo) {

        Tipo t = Tipo.valueOf(tipo); //Se convierte a enum
        producto.setTipo(t); //Asignamos el tipo que le queremos dar al producto

        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarMonedas(int id, int coste) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto inexistente"));
        producto.setCoste(coste);
        return productoRepository.save(producto);
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
