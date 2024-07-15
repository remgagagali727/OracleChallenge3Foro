package com.remproyectos.foro.domain.autor;

import jakarta.validation.constraints.NotBlank;

public record ObtenerDatosAutor(
        @NotBlank String nombre,
        @NotBlank String apellido
) {
}
