package com.catalogo.brinquedos.service;

import com.catalogo.brinquedos.model.Categoria;
import com.catalogo.brinquedos.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final FileStorageService fileStorageService;

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria salvar(Categoria categoria, MultipartFile imagem) throws IOException {
        if (imagem != null && !imagem.isEmpty()) {
            String nomeImagem = fileStorageService.salvarImagem(imagem);
            categoria.setImagem(nomeImagem);
        }
        return categoriaRepository.save(categoria);
    }
}
