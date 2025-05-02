package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Transaccion;

import java.util.List;

public interface TransaccionService {

    // 1. Canjear un producto
    Transaccion canjearProducto(int userId, int productoId);

    // 2. Ver historial de productos canjeados
    List<Transaccion> obtenerTransaccionesCanjeadasPorUsuario(int userId);

    // 3. Comprobar si un producto ya fue canjeado
    Transaccion obtenerTransaccionCanjeadaPorUsuarioYProducto(int userId, int productoId);

    // 4. Eliminar todas las transacciones asociadas a un producto (por si el admin elimina el producto)
    void eliminarTransaccionesPorProducto(int productoId);

}
