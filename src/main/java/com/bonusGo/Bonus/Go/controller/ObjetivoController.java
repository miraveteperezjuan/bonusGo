package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.service.ObjetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("objetivo")
public class ObjetivoController {

    //http://localhost:8080/objetivo/

    @Autowired
    private ObjetivoService objetivoService;

}
