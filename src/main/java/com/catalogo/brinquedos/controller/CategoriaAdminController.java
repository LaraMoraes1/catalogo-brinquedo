package com.catalogo.brinquedos.controller;

import com.catalogo.brinquedos.model.Categoria;
import com.catalogo.brinquedos.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/categorias")
public class CategoriaAdminController {

    private final CategoriaService categoriaService;

    public CategoriaAdminController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "admin/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Categoria nao encontrada")));
        return "admin/editar-categoria";
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Long id,
                            @ModelAttribute Categoria categoria,
                            @RequestParam("arquivoImagem") MultipartFile imagem,
                            RedirectAttributes redirectAttributes) {
        categoria.setId(id);
        try {
            categoriaService.salvar(categoria, imagem);
            redirectAttributes.addFlashAttribute("sucesso", "Categoria atualizada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar: " + e.getMessage());
        }
        return "redirect:/admin/categorias";
    }
}