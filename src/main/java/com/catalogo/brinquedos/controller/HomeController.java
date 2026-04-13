package com.catalogo.brinquedos.controller;

import com.catalogo.brinquedos.service.BrinquedoService;
import com.catalogo.brinquedos.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BrinquedoService brinquedoService;
    private final CategoriaService categoriaService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("destaques", brinquedoService.listarDestaques());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "home";
    }

    @GetMapping("/sobre")
    public String sobre() {
        return "sobre";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
