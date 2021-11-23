package com.example.BancoAutenticacion.configuracion;

import com.example.BancoAutenticacion.controlador.LogeoCorrectoUsuario;
import com.example.BancoAutenticacion.controlador.noExisteUsuarioExcepcion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExcepcionUsuarioHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({noExisteUsuarioExcepcion.class})
    protected ResponseEntity<Object> handleNotFound(
            Exception ex, WebRequest request){
        return handleExceptionInternal(ex,"Usuario no encontrado",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
