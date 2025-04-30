package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Producto;

import java.util.List;

public interface ProductoService {

    Producto registrarProducto(Producto producto);

    Producto actualizar(int id, Producto nuevoProducto);

    void eliminarProducto(int id);

    List<Producto> listaProductos();

    Producto buscarProductoId(int id);

    List<Producto> listarHabilitados();

    List<Producto> listarDeshabilitados();

    List<Producto> listarDisponiblesParaUsuario(int userId);

    void setEstado(int id, boolean habilitado);

    void canjearProducto(int id_Producto, int id_Usuario);

}
