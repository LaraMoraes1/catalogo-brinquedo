package com.catalogo.brinquedos.controller;

import com.catalogo.brinquedos.model.Brinquedo;
import com.catalogo.brinquedos.service.BrinquedoService;
import com.catalogo.brinquedos.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final BrinquedoService brinquedoService;
    private final CategoriaService categoriaService;

    // Lista todos os brinquedos para administrar
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("brinquedos", brinquedoService.listarTodos());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "admin/lista";
    }

    // Formulario de novo brinquedo
    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("brinquedo", new Brinquedo());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "admin/formulario";
    }

    // Salva novo brinquedo
    @PostMapping("/novo")
    public String salvar(@Valid @ModelAttribute Brinquedo brinquedo,
                         BindingResult result,
                         @RequestParam("arquivoImagem") MultipartFile imagem,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "admin/formulario";
        }
        try {
            brinquedoService.salvar(brinquedo, imagem);
            redirectAttributes.addFlashAttribute("sucesso", "Brinquedo cadastrado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao salvar: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    // Formulario de edicao
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("brinquedo", brinquedoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Brinquedo nao encontrado")));
        model.addAttribute("categorias", categoriaService.listarTodas());
        model.addAttribute("editando", true);
        return "admin/formulario";
    }

    // Salva edicao
    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute Brinquedo brinquedo,
                            BindingResult result,
                            @RequestParam("arquivoImagem") MultipartFile imagem,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        brinquedo.setId(id);
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listarTodas());
            model.addAttribute("editando", true);
            return "admin/formulario";
        }
        try {
            brinquedoService.atualizar(brinquedo, imagem);
            redirectAttributes.addFlashAttribute("sucesso", "Brinquedo atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    // Exclui brinquedo
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            brinquedoService.deletar(id);
            redirectAttributes.addFlashAttribute("sucesso", "Brinquedo excluido com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir: " + e.getMessage());
        }
        return "redirect:/admin";
    }
}
