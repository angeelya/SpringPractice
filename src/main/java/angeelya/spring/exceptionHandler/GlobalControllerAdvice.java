package angeelya.spring.exceptionHandler;

import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.exceptionHandler.exception.DeleteException;
import angeelya.spring.exceptionHandler.exception.NoAddException;
import angeelya.spring.exceptionHandler.exception.NotFoundException;
import angeelya.spring.exceptionHandler.exception.ValidationErrorsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {
        @ExceptionHandler(ValidationErrorsException.class)
        public ResponseEntity<MessageResponse> handlerBadRequestException(Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        @ExceptionHandler({NotFoundException.class})
        public ResponseEntity<MessageResponse> handlerDatabaseNotFoundException(NotFoundException e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
        @ExceptionHandler({NoAddException.class, DeleteException.class})
        public ResponseEntity<MessageResponse> handlerServerException(Exception e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
