package com.catalogo.brinquedos.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@Service
public class FileStorageService {

    private final Cloudinary cloudinary;

    public FileStorageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String salvarImagem(MultipartFile arquivo) throws IOException {
        if (arquivo == null || arquivo.isEmpty()) return null;

        Map uploadResult = cloudinary.uploader().upload(
                arquivo.getBytes(),
                ObjectUtils.asMap("folder", "catalogo-brinquedos")
        );

        return uploadResult.get("secure_url").toString();
    }

    public void deletarImagem(String url) {
        if (url == null || url.isBlank()) return;
        try {
            // Extrai o public_id da URL
            String publicId = url.substring(url.lastIndexOf("/catalogo-brinquedos/") + 1)
                    .replace(".jpg", "")
                    .replace(".jpeg", "")
                    .replace(".png", "");
            cloudinary.uploader().destroy("catalogo-brinquedos/" + publicId, ObjectUtils.emptyMap());
        } catch (Exception e) {
            // ignora erro ao deletar
        }
    }
}