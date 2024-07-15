package com.remproyectos.foro.controllers;

import com.remproyectos.foro.domain.autor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    public ResponseEntity subirAutor(@RequestBody @Valid ObtenerDatosAutor datosAutor,
                                                          UriComponentsBuilder uriComponentsBuilder)
            throws URISyntaxException {
        Autor autor = new Autor(datosAutor);
        autorRepository.save(autor);
        URI url = uriComponentsBuilder.path("/Autors/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(url)
                .body(new MostrarDatosAutor(autor));
    }

    @GetMapping
    public ResponseEntity mostrarAutors(@PageableDefault(size = 10) Pageable pageable) {
        var respuestaHTTP = autorRepository.findByEstatusTrue(pageable)
                .map(MostrarDatosAutor::new);
        return ResponseEntity.ok(respuestaHTTP);
    }

    @PutMapping
    @Transactional
    public ResponseEntity modificarAutor(@RequestBody @Valid DatosModificarAutor datosModificarAutor) {
        Autor autor = autorRepository.getReferenceById(datosModificarAutor.id());
        autor.actualizarAutor(datosModificarAutor);
        return ResponseEntity.ok(
                new MostrarDatosAutor(autor)
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarAutor(@PathVariable Long id) {
        Autor autor = autorRepository.getReferenceById(id);
        autor.deshabilitar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity mostrarAutor(@PathVariable Long id) {
        return ResponseEntity.ok(
                new MostrarDatosAutor(
                        autorRepository.getReferenceById(id)
                )
        );
    }

}
