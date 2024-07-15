package com.remproyectos.foro.infra;

import com.remproyectos.foro.domain.exception.MostrarException;
import com.remproyectos.foro.infra.exceptions.InvalidLoginException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
        List<DatosErrorValidation> errores = e.getFieldErrors()
                .stream().map(DatosErrorValidation::new)
                .toList();
        return ResponseEntity.badRequest()
                .body(new MostrarException(errores.toString()));
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity tratarError403(InvalidLoginException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MostrarException(e.getMessage()));
    }



    private record DatosErrorValidation(String campo, String error) {
        public DatosErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}