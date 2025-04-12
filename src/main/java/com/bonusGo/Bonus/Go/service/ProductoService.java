package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Producto;

import java.util.List;

public interface ProductoService {

    Producto registrarProducto(Producto producto);

    Producto actualizarMonedas(int id, int coste);

    void eliminarProducto(int id);

    List<Producto> listaProductos();

    Producto buscarProductoId(int id);

    Producto actualizarCoste(int id, int coste);

}
