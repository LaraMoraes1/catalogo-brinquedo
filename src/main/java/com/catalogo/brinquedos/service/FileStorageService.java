package com.catalogo.brinquedos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${app.upload.dir:uploads/}")
    private String uploadDir;

    public String salvarImagem(MultipartFile arquivo) throws IOException {
        if (arquivo == null || arquivo.isEmpty()) return null;

        Path pastaUpload = Paths.get(uploadDir);
        if (!Files.exists(pastaUpload)) {
            Files.createDirectories(pastaUpload);
        }

        String nomeArquivo = UUID.randomUUID() + "_" + arquivo.getOriginalFilename();
        Path destino = pastaUpload.resolve(nomeArquivo);
        Files.copy(arquivo.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

        return nomeArquivo;
    }

    public void deletarImagem(String nomeArquivo) {
        if (nomeArquivo == null || nomeArquivo.isBlank()) return;
        try {
            Path arquivo = Paths.get(uploadDir).resolve(nomeArquivo);
            Files.deleteIfExists(arquivo);
        } catch (IOException e) {
            // log ignorado para simplicidade
        }
    }
}
