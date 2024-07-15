package com.remproyectos.foro.domain.autor;

public record MostrarDatosAutor(
        Long id,
        String nombre,
        String apellido
) {
    public MostrarDatosAutor(Autor autor) {
        this(autor.getId(), autor.getNombre(), autor.getApellido());
    }
}
