package ngo.drc.core.api;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import ngo.drc.core.endpoint.ResponseProducer;
import ngo.drc.core.endpoint.dto.FailureResponse;
import ngo.drc.core.endpoint.dto.ValidationFailedResponse;
import ngo.drc.core.exception.EntityValidationException;
import ngo.drc.core.exception.GenericDisplayableException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler implements ResponseProducer {
    @ExceptionHandler(GenericDisplayableException.class)
    protected ResponseEntity<Object> handleFinPlatException(@NotNull GenericDisplayableException e) {
        return fail(e, () -> new FailureResponse(e.getMessage()), e.getStatus());
    }

    @ExceptionHandler(EntityValidationException.class)
    protected ResponseEntity<Object> handleEntityValidationException(@NotNull EntityValidationException e) {
        ArrayList<String> errorMessages = new ArrayList<>();
        e.getErrors().getFieldErrors()
                .forEach(error->errorMessages.add(error.getDefaultMessage()));
        return fail(e, () -> new ValidationFailedResponse(e.getMessage(), errorMessages));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
        return fail(e, () -> new FailureResponse(e.getMessage()));
    }
}
