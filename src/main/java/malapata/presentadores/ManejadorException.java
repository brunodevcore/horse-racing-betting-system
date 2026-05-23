package malapata.presentadores;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejadorException {
    
    @ExceptionHandler(Exception.class)
    public Commands manejarExcepcion(Exception e) {
        e.printStackTrace();
        return Commands.create(
            new Command(
                "error", "Ocurrió un error inesperado: " + e.getMessage()
            )
        );
    }
}
