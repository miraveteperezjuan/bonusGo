package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Producto;

import java.util.List;

public interface ProductoService {

    Producto save(Producto producto);

    List<Producto> listaProductos();

    Producto buscaProducto(int id);

    Producto eliminaProducto(int id);
}
