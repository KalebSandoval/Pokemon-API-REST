package sandoval.brian.pokeApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sandoval.brian.pokeApi.dto.ErrorDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> manejarValidaciones(MethodArgumentNotValidException ex) {
        List<String> detalles = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            detalles.add(error.getField() + ":" + error.getDefaultMessage());
        }
        ErrorDTO errorDTO = new ErrorDTO("Error en la validación de los datos enviados",
                detalles,
                LocalDateTime.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFundException.class)
    public ResponseEntity<ErrorDTO> manejarRecursoNoEncontrado(ResourceNotFundException ex) {
        List<String> detalles = new ArrayList<>();

        detalles.add(ex.getMessage());

        ErrorDTO errorDTO = new ErrorDTO("Error no encontrado",
                detalles,
                LocalDateTime.now());

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> manejarExcepcionGlobal(Exception ex) {
        List<String> detalles = new ArrayList<>();
        detalles.add(ex.getLocalizedMessage());

        ErrorDTO errorDTO = new ErrorDTO("Ocurrió un error interno en el servidor",
                detalles,
                LocalDateTime.now());

        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
