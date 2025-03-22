package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.service.RolService;
import com.bonusGo.Bonus.Go.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tipo")
public class TipoController {

    //http://localhost:8080/tipo/

    @Autowired
    private TipoService tipoService;

}
