package com.bonusGo.Bonus.Go.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImagenServiceImp implements ImagenService{

    private final String UPLOAD_DIR = "uploads/";

    public String guardarImagen(MultipartFile archivo) {
        // Crear nombre único
        String nombreArchivo = UUID.randomUUID() + "_" + archivo.getOriginalFilename();

        try {
            // Crear ruta destino
            File destino = new File(UPLOAD_DIR + nombreArchivo);

            // Asegurar que el directorio exista
            File directorio = new File(UPLOAD_DIR);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Guardar archivo físico
            archivo.transferTo(destino);

            // Devolver URL relativa para acceder a la imagen
            return "/uploads/" + nombreArchivo;

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen: " + e.getMessage());
        }
    }
}
