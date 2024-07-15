CREATE TABLE topicos (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje VARCHAR(500) NOT NULL,
    id_autor BIGINT NOT NULL,
    curso VARCHAR(255) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    estatus BOOLEAN NOT NULL,
    FOREIGN KEY (id_autor) REFERENCES autores(id)
);