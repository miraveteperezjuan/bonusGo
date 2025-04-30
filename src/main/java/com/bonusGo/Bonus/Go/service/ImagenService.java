package com.bonusGo.Bonus.Go.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImagenService {

    public String guardarImagen(MultipartFile archivo);

}
