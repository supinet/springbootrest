package med.supi.api.infra.exception;

import med.supi.api.domain.ExceptionValidation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorHandler404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandler400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ErrorValidationDto::new).toList());
    }

    @ExceptionHandler(ExceptionValidation.class)
    public ResponseEntity businessRulesHandler(ExceptionValidation ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    private record ErrorValidationDto(String field, String message) {
        public ErrorValidationDto(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
