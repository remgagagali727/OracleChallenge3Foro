package com.remproyectos.foro.domain.autor;

import jakarta.validation.constraints.NotNull;

public record DatosModificarAutor(
    @NotNull Long id,
    String nombre,
    String apellido
) { }
