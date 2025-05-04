package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Producto;
import com.bonusGo.Bonus.Go.model.Transaccion;
import com.bonusGo.Bonus.Go.model.Usuario;
import com.bonusGo.Bonus.Go.repository.ProductoRepository;
import com.bonusGo.Bonus.Go.repository.TransaccionRepository;
import com.bonusGo.Bonus.Go.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionServiceImp implements TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Transaccion canjearProducto(int userId, int productoId) {
        Transaccion existente = transaccionRepository.findTransaccionCanjeadaPorUsuarioYProducto(userId, productoId);
        if (existente != null) {
            throw new RuntimeException("El producto ya fue canjeado por este usuario.");
        }

        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (usuario.getMoneda() < producto.getCoste()) {
            throw new RuntimeException("No tienes suficientes PigCoins para canjear este producto.");
        }

        usuario.setMoneda(usuario.getMoneda() - producto.getCoste());
        usuarioRepository.save(usuario);

        Transaccion transaccion = new Transaccion(usuario, producto, true);
        return transaccionRepository.save(transaccion);
    }

    @Override
    public List<Producto> obtenerProductosCanjeadosPorUsuario(int userId) {
        return transaccionRepository.findProductosCanjeadosByUsuarioId(userId);
    }
}
