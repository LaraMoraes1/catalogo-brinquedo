package com.catalogo.brinquedos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da categoria e obrigatorio")
    @Column(nullable = false, unique = true)
    private String nome;

    private String imagem;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Brinquedo> brinquedos;
}
