package com.remproyectos.foro.domain.autor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "autores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private boolean estatus;

    public Autor(ObtenerDatosAutor datosAutor) {
        this.estatus = true;
        this.nombre = datosAutor.nombre();
        this.apellido = datosAutor.apellido();
    }

    public void deshabilitar() {
        this.estatus = false;
    }

    public void actualizarAutor(DatosModificarAutor datosModificarAutor) {
        if(datosModificarAutor.nombre() != null)
            this.nombre = datosModificarAutor.nombre();
        if(datosModificarAutor.apellido() != null)
            this.apellido = datosModificarAutor.apellido();
    }
}
