package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.*;
import com.bonusGo.Bonus.Go.repository.GananciaRepository;
import com.bonusGo.Bonus.Go.repository.ObjetivoRepository;
import com.bonusGo.Bonus.Go.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjetivoServiceImp implements ObjetivoService {

    @Autowired
    private ObjetivoRepository objetivoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GananciaRepository gananciaRepository;

    @Override
    public Objetivo registObjetivo(Objetivo objetivo) {
        return objetivoRepository.save(objetivo);
    }

    @Override
    public void eliminarObjetivo(int id) {
        Objetivo objetivo = objetivoRepository.findById(id).get();

        if (objetivo != null) {
            objetivoRepository.delete(objetivo);
        }else {
            System.out.println("Este objetivo no existe");
        }
    }

    @Override
    public List<Objetivo> listarObjetivos() {
        return objetivoRepository.findAll();
    }

    @Override
    public Objetivo buscarObjetivo(int id) {
        return objetivoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public Objetivo actualizarMonedas(int id, int monedas) {
        Objetivo objetivo = objetivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objetivo no encontrado"));
        objetivo.setMonedas(monedas);
        return objetivoRepository.save(objetivo);
    }

    @Override
    public List<Objetivo> listarHabilitados() {
        return objetivoRepository.findByIsEnabledTrue();
    }

    @Override
    public List<Objetivo> listarDeshabilitados() {
        return objetivoRepository.findByIsEnabledFalse();
    }

    @Override
    public void setEstado(int id, boolean habilitado) {
        Objetivo objetivo = objetivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objetivo no encontrado"));
        objetivo.setEnabled(habilitado);
        objetivoRepository.save(objetivo);
    }

    @Override
    public void canjearObjetivo(int idObjetivo, int idUsuario) {
        Objetivo objetivo = objetivoRepository.findById(idObjetivo)
                .orElseThrow(() -> new RuntimeException("Objetivo no encontrado"));

        if (!objetivo.isEnabled()) {
            throw new RuntimeException("Objetivo no disponible");
        }

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setMoneda(usuario.getMoneda() + objetivo.getMonedas());

        GananciaMonedas gananciaMonedas = new GananciaMonedas();
        gananciaMonedas.setUsuario(usuario);
        gananciaMonedas.setObjetivo(objetivo);
        gananciaMonedas.setCanjeado(true);

        //objetivo.getUsuarios().add(usuario);
        gananciaRepository.save(gananciaMonedas);
        usuarioRepository.save(usuario);
        //objetivoRepository.save(objetivo);
    }

    public List<Objetivo> listarDisponiblesParaUsuario(int userId) {
        List<Objetivo> todos = objetivoRepository.findByIsEnabledTrue();
        List<Objetivo> yaCanjeados = gananciaRepository.findObjetivosCanjeadosByUsuarioId(userId);

        return todos.stream()
                .filter(p -> !yaCanjeados.contains(p))
                .toList();
    }



}
