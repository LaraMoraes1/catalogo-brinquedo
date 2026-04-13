package com.catalogo.brinquedos.controller;

import com.catalogo.brinquedos.model.Categoria;
import com.catalogo.brinquedos.service.BrinquedoService;
import com.catalogo.brinquedos.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/catalogo")
@RequiredArgsConstructor
public class CatalogoController {

    private final BrinquedoService brinquedoService;
    private final CategoriaService categoriaService;

    // Lista todas as categorias
    @GetMapping
    public String categorias(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "catalog/categorias";
    }

    // Lista brinquedos de uma categoria
    @GetMapping("/categoria/{id}")
    public String brinquedosPorCategoria(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Categoria nao encontrada"));
        model.addAttribute("categoria", categoria);
        model.addAttribute("brinquedos", brinquedoService.listarPorCategoria(categoria));
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "catalog/brinquedos";
    }

    // Detalhe de um brinquedo
    @GetMapping("/brinquedo/{id}")
    public String detalheBrinquedo(@PathVariable Long id, Model model) {
        model.addAttribute("brinquedo", brinquedoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Brinquedo nao encontrado")));
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "catalog/detalhe";
    }
}
