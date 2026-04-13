package com.catalogo.brinquedos.service;

import com.catalogo.brinquedos.model.Brinquedo;
import com.catalogo.brinquedos.model.Categoria;
import com.catalogo.brinquedos.repository.BrinquedoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrinquedoService {

    private final BrinquedoRepository brinquedoRepository;
    private final FileStorageService fileStorageService;

    public List<Brinquedo> listarTodos() {
        return brinquedoRepository.findAll();
    }

    public List<Brinquedo> listarDestaques() {
        return brinquedoRepository.findByDestaqueTrue();
    }

    public List<Brinquedo> listarPorCategoria(Categoria categoria) {
        return brinquedoRepository.findByCategoria(categoria);
    }

    public Optional<Brinquedo> buscarPorId(Long id) {
        return brinquedoRepository.findById(id);
    }

    public Brinquedo salvar(Brinquedo brinquedo, MultipartFile imagem) throws IOException {
        if (imagem != null && !imagem.isEmpty()) {
            String nomeImagem = fileStorageService.salvarImagem(imagem);
            brinquedo.setImagem(nomeImagem);
        }
        return brinquedoRepository.save(brinquedo);
    }

    public Brinquedo atualizar(Brinquedo brinquedo, MultipartFile imagem) throws IOException {
        if (imagem != null && !imagem.isEmpty()) {
            // deleta a imagem antiga
            Optional<Brinquedo> antigo = brinquedoRepository.findById(brinquedo.getId());
            antigo.ifPresent(b -> fileStorageService.deletarImagem(b.getImagem()));
            String nomeImagem = fileStorageService.salvarImagem(imagem);
            brinquedo.setImagem(nomeImagem);
        }
        return brinquedoRepository.save(brinquedo);
    }

    public void deletar(Long id) {
        brinquedoRepository.findById(id).ifPresent(b -> {
            fileStorageService.deletarImagem(b.getImagem());
            brinquedoRepository.delete(b);
        });
    }
}
