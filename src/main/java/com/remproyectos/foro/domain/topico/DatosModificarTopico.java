package com.remproyectos.foro.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosModificarTopico(
        @NotNull Long id,
        String titulo,
        String mensaje
) { }
