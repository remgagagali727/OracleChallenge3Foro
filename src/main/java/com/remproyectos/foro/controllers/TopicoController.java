package com.remproyectos.foro.controllers;


import com.remproyectos.foro.domain.topico.*;
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
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<MostrarDatosTopico> subirTopico(@RequestBody @Valid ObtenerDatosTopico datosTopico,
            UriComponentsBuilder uriComponentsBuilder)
            throws URISyntaxException {
        Topico topico = new Topico(datosTopico);
        topicoRepository.save(topico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url)
                .body(new MostrarDatosTopico(topico));
    }

    @GetMapping
    public ResponseEntity mostrarTopicos(@PageableDefault(size = 10) Pageable pageable) {
        var respuestaHTTP = topicoRepository.findByEstatusTrue(pageable)
                .map(MostrarDatosTopico::new);
        return ResponseEntity.ok(respuestaHTTP);
    }

    @PutMapping
    @Transactional
    public ResponseEntity modificarTopico(@RequestBody @Valid DatosModificarTopico datosModificarTopico) {
        Topico topico = topicoRepository.getReferenceById(datosModificarTopico.id());
        topico.actualizarTopico(datosModificarTopico);
        return ResponseEntity.ok(
                new MostrarDatosTopico(topico)
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.deshabilitar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity mostrarTopico(@PathVariable Long id) {
        return ResponseEntity.ok(
                new MostrarDatosTopico(
                        topicoRepository.getReferenceById(id)
                )
        );
    }
}
