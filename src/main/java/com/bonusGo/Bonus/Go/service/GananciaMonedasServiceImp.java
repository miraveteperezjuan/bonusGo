package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.GananciaMonedas;
import com.bonusGo.Bonus.Go.model.Objetivo;
import com.bonusGo.Bonus.Go.model.Usuario;
import com.bonusGo.Bonus.Go.repository.GananciaMonedasRepository;
import com.bonusGo.Bonus.Go.repository.ObjetivoRepository;
import com.bonusGo.Bonus.Go.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GananciaMonedasServiceImp implements GananciaMonedasService{

    @Autowired
    private GananciaMonedasRepository gananciaMonedasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjetivoRepository objetivoRepository;

    @Override
    public GananciaMonedas habilitarObjetivoParaUsuario(int idUsuario, int idObjetivo) {
        Usuario usuarioEncontrado = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Objetivo objetivoEncontrado = objetivoRepository.findById(idObjetivo)
                .orElseThrow(() -> new IllegalArgumentException("Objetivo no encontrado"));

        // Verificar si ya existe una relación entre el usuario y el objetivo
        GananciaMonedas relacionUsuarioObjetivo = gananciaMonedasRepository.relacionUsuarioYObjetivo(idUsuario, idObjetivo);


        if (relacionUsuarioObjetivo == null) {
            // Si no existía la relación, la crea en la bbdd
            relacionUsuarioObjetivo = new GananciaMonedas(usuarioEncontrado, objetivoEncontrado, false, true);
        } else {
            // Si ya existe la habilita
            relacionUsuarioObjetivo.setHabilitado(true);
        }

        return gananciaMonedasRepository.save(relacionUsuarioObjetivo);
    }

    @Override
    public GananciaMonedas deshabilitarObjetivoParaUsuario(int idUsuario, int idObjetivo) {
        // Buscar la relación entre el usuario y el objetivo
        GananciaMonedas relacion = gananciaMonedasRepository.relacionUsuarioYObjetivo(idUsuario, idObjetivo);

        // Si no existe, es que el objetivo no esta habilitado
        if (relacion == null) {
            throw new IllegalStateException("No existe relación entre el usuario y el objetivo.");
        }

        // Deshabilitar el objetivo para el usuario
        relacion.setHabilitado(false);

        return gananciaMonedasRepository.save(relacion);
    }

    @Override
    public List<Integer> obtenerIdsUsuariosConObjetivoHabilitado(int idObjetivo) {
        return gananciaMonedasRepository.findUsuariosConObjetivoHabilitado(idObjetivo);
    }


    @Override
    public void marcarObjetivoComoReclamado(int idUsuario, int idObjetivo) {
        // Buscar la relación entre el usuario y el objetivo
        GananciaMonedas relacion = gananciaMonedasRepository.relacionUsuarioYObjetivo(idUsuario, idObjetivo);

        if (relacion == null || !relacion.isHabilitado()) {
            throw new IllegalStateException("El objetivo no está habilitado para este usuario o no existe la relación.");
        }

        // Marcar como reclamado y deshabilitar
        relacion.setReclamado(true);
        relacion.setHabilitado(false);

        gananciaMonedasRepository.save(relacion);
    }
}
