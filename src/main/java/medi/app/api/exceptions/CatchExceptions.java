package medi.app.api.exceptions;


import jakarta.persistence.EntityNotFoundException;
import jakarta.xml.bind.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CatchExceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity Catch404Exception(){
        return ResponseEntity.notFound().build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity CatchValidationException(MethodArgumentNotValidException exception){

        var erros = exception.getFieldErrors();


        return ResponseEntity.badRequest().body(erros.stream().map(ValidationDataError::new).toList());
    }


    private record ValidationDataError(String campo, String mensagem){
        public ValidationDataError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
