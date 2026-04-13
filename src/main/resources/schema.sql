-- =============================================
-- Script SQL - Catalogo de Brinquedos
-- Execute antes de rodar o projeto
-- =============================================

CREATE DATABASE IF NOT EXISTS catalogo_brinquedos
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE catalogo_brinquedos;

-- Tabela de categorias
CREATE TABLE IF NOT EXISTS categorias (
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome    VARCHAR(100) NOT NULL UNIQUE,
    imagem  VARCHAR(255)
);

-- Tabela de brinquedos
CREATE TABLE IF NOT EXISTS brinquedos (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo        VARCHAR(50)    NOT NULL UNIQUE,
    descricao     VARCHAR(200)   NOT NULL,
    marca         VARCHAR(100)   NOT NULL,
    imagem        VARCHAR(255),
    valor         DECIMAL(10,2)  NOT NULL,
    detalhes      TEXT,
    destaque      BOOLEAN        DEFAULT FALSE,
    categoria_id  BIGINT         NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

-- Dados de exemplo (opcional - apague se nao quiser)
INSERT IGNORE INTO categorias (nome) VALUES
    ('Educativos'),
    ('Esportes'),
    ('Bonecas e Bonecos');

INSERT IGNORE INTO brinquedos (codigo, descricao, marca, valor, detalhes, destaque, categoria_id)
VALUES
    ('BRQ001', 'Quebra-Cabeca 500 pecas', 'Grow', 49.90, 'Quebra-cabeca educativo para maiores de 8 anos. 500 pecas coloridas.', TRUE, 1),
    ('BRQ002', 'Bola de Futebol Oficial', 'Penalty', 89.90, 'Bola de futebol tamanho oficial, material PU resistente.', TRUE, 2),
    ('BRQ003', 'Boneca Articulada', 'Barbie', 129.90, 'Boneca com 12 pontos de articulacao e acessorios inclusos.', FALSE, 3);
