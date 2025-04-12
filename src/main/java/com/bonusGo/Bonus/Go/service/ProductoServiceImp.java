package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Producto;
import com.bonusGo.Bonus.Go.model.Tipo;
import com.bonusGo.Bonus.Go.model.Transaccion;
import com.bonusGo.Bonus.Go.model.Usuario;
import com.bonusGo.Bonus.Go.repository.ProductoRepository;
import com.bonusGo.Bonus.Go.repository.TransaccionRepository;
import com.bonusGo.Bonus.Go.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Override
    public Producto registrarProducto(Producto producto) {
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

    @Override
    public List<Producto> listarHabilitados() {
        return productoRepository.findByIsEnabledTrue();
    }

    @Override
    public List<Producto> listarDeshabilitados() {
        return productoRepository.findByIsEnabledFalse();
    }

    @Override
    public void setEstado(int id, boolean habilitado) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setEnabled(habilitado);
        productoRepository.save(producto);
    }

    //Canjear los productos para el user
    /*@Override
    public void canjearProducto(int idProducto, int idUsuario) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (!producto.isEnabled()) {
            throw new RuntimeException("Producto no disponible");
        }

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario.getMoneda() < producto.getCoste()) {
            throw new RuntimeException("Saldo insuficiente para canjear este producto");
        }

        usuario.setMoneda(usuario.getMoneda() - producto.getCoste());

        //producto.getUsuarios().add(usuario);

        Transaccion transaccion = new Transaccion();
        transaccion.setUsuario(usuario);
        transaccion.setProducto(producto);
        transaccion.setCanjeado(true);

        transaccionRepository.save(transaccion);

        usuarioRepository.save(usuario);
        productoRepository.save(producto);
    }*/

    @Override
    public void canjearProducto(int id_Producto, int id_Usuario) {
        Producto producto = productoRepository.findById(id_Producto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Usuario usuario = usuarioRepository.findById(id_Usuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        /*if (transaccionRepository.existsByUsuario_IdUsuarioAndProducto_IdProducto(id_Usuario, id_Producto)) {
            throw new RuntimeException("Ya has canjeado este producto");
        }*/

        if (!producto.isEnabled()) {
            throw new RuntimeException("Producto no disponible");
        }

        if (usuario.getMoneda() < producto.getCoste()) {
            throw new RuntimeException("Saldo insuficiente para canjear este producto");
        }

        usuario.setMoneda(usuario.getMoneda() - producto.getCoste());

        Transaccion transaccion = new Transaccion();
        transaccion.setUsuario(usuario);
        transaccion.setProducto(producto);
        transaccion.setCanjeado(true);

        transaccionRepository.save(transaccion);
        usuarioRepository.save(usuario);
    }

    public List<Producto> listarDisponiblesParaUsuario(int userId) {
        List<Producto> todos = productoRepository.findByIsEnabledTrue();
        List<Producto> yaCanjeados = transaccionRepository.findProductosCanjeadosByUsuarioId(userId);

        return todos.stream()
                .filter(p -> !yaCanjeados.contains(p))
                .toList();
    }





}
