package com.remproyectos.foro.domain.topico;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties
public record ObtenerDatosTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Long idAutor,
        @NotBlank String curso
) { }
