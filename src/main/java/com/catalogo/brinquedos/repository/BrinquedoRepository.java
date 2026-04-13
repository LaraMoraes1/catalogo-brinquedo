package com.catalogo.brinquedos.repository;

import com.catalogo.brinquedos.model.Brinquedo;
import com.catalogo.brinquedos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BrinquedoRepository extends JpaRepository<Brinquedo, Long> {
    List<Brinquedo> findByDestaqueTrue();
    List<Brinquedo> findByCategoria(Categoria categoria);
    List<Brinquedo> findByDescricaoContainingIgnoreCase(String descricao);
}
