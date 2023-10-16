    package com.leduy.backend.infrastructure.exception;

    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.MethodArgumentNotValidException;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.ResponseStatus;
    import org.springframework.web.bind.annotation.RestControllerAdvice;
    import org.springframework.web.context.request.WebRequest;

    import java.util.Date;
    import java.util.HashMap;
    import java.util.Map;

    @RestControllerAdvice
    public class ApplicationExceptionHandler {
        // valid entity
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public Map<String, String> handlerInvalidArgument(MethodArgumentNotValidException ex) {
            Map<String, String> errorMap = new HashMap<>();
            ex.getBindingResult().getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });
            return errorMap;
        }

        // Not found
        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException exception,
                                                                  WebRequest request) {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setTimestamp(new Date());
            errorDetails.setMessage(exception.getMessage());
            errorDetails.setDetails(request.getDescription(false));
            return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
        }

        //sever
        @ExceptionHandler(Exception.class)
        public ResponseEntity<?> handlerGlobalException(Exception exception,
                                                        WebRequest request) {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setTimestamp(new Date());
            errorDetails.setMessage(exception.getMessage());
            errorDetails.setDetails(request.getDescription(false));
            return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //as AccessDeniedException and ...

        @ExceptionHandler(ApiException.class)
        public ResponseEntity<?> handlerAPIException(ApiException exception,
                                                     WebRequest request) {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setTimestamp(new Date());
            errorDetails.setMessage(exception.getMessage());
            errorDetails.setDetails(request.getDescription(false));
            return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
        }
    }
