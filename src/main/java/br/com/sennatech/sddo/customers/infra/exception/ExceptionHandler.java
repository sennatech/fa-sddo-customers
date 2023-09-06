//package br.com.sennatech.sddo.customers.infra.exception;
//
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.validation.FieldError;
//
//import java.sql.SQLIntegrityConstraintViolationException;
//
//@RestControllerAdvice
//public class ExceptionHandler {
//
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity HandleError404(){
//        return ResponseEntity.notFound().build();
//    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity  HandleError400(MethodArgumentNotValidException ex){
//        var error = ex.getFieldErrors();
//        return ResponseEntity.badRequest().body(error.stream().map(ValidationErrorDTO::new).toList());
//    }
//    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//    public ResponseEntity HandleNonUniqueEntryError(SQLIntegrityConstraintViolationException ex){
//        var sqlError = ex.getCause();
//        return ResponseEntity.badRequest().body(sqlError);
//    }
//
//
//    private record ValidationErrorDTO(String field,String message){
//        public ValidationErrorDTO(FieldError error){
//            this(error.getField(),error.getDefaultMessage());
//        }
//
//    }
//}