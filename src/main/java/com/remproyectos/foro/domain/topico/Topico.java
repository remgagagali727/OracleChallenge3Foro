package com.remproyectos.foro.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private Long idAutor;
    private String curso;
    private LocalDateTime fechaCreacion;
    private boolean estatus;

    public Topico(ObtenerDatosTopico datosTopico) {
        this.titulo = datosTopico.titulo();
        this.idAutor = datosTopico.idAutor();
        this.curso = datosTopico.curso();
        this.mensaje = datosTopico.mensaje();
        this.estatus = true;
        this.fechaCreacion = LocalDateTime.now();
    }

    public void actualizarTopico(DatosModificarTopico datosModificarTopico) {
        if(datosModificarTopico.titulo() != null)
            this.titulo = datosModificarTopico.titulo();
        if(datosModificarTopico.mensaje() != null)
            this.mensaje = datosModificarTopico.mensaje();
    }

    public void deshabilitar() {
        this.estatus = false;
    }
}
