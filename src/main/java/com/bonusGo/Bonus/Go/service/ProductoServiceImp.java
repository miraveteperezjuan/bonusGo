package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Producto;
import com.bonusGo.Bonus.Go.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImp implements ProductoService{

    private ProductoRepository productoRepository;

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listaProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto buscaProducto(int id) {
        return productoRepository.findById(id).get();
    }

    @Override
    public Producto eliminaProducto(int id) {
        Producto producto = productoRepository.findById(id).get();
        if(producto != null) {
            productoRepository.delete(producto);
        }
        return producto;
    }
}
