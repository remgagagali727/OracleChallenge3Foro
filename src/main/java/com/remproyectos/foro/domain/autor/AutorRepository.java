package com.remproyectos.foro.domain.autor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AutorRepository extends JpaRepository<Autor, Long> {

    Page<Autor> findByEstatusTrue(Pageable p);
}
