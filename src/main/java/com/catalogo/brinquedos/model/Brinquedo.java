package com.catalogo.brinquedos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "brinquedos")
public class Brinquedo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O codigo e obrigatorio")
    @Column(nullable = false, unique = true)
    private String codigo;

    @NotBlank(message = "A descricao e obrigatoria")
    @Column(nullable = false)
    private String descricao;

    @NotBlank(message = "A marca e obrigatoria")
    private String marca;

    private String imagem;

    @NotNull(message = "O valor e obrigatorio")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    @Column(nullable = false)
    private BigDecimal valor;

    @Column(columnDefinition = "TEXT")
    private String detalhes;

    private boolean destaque = false;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    @NotNull(message = "A categoria e obrigatoria")
    private Categoria categoria;
}
