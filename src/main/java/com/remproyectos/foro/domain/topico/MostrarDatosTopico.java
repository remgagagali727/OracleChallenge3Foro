package com.remproyectos.foro.domain.topico;

import java.time.LocalDateTime;

public record MostrarDatosTopico(
    long id,
    String titulo,
    String mensaje,
    LocalDateTime fechaCreacion,
    boolean estatus,
    Long idAutor,
    String curso
) {
    public MostrarDatosTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.isEstatus(), topico.getIdAutor(), topico.getCurso());
    }
}
