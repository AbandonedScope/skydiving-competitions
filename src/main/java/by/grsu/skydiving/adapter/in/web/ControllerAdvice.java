package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {
    @ExceptionHandler(ValidationException.class)
    public ProblemDetail handleValidationException(ValidationException ex) {
        ProblemDetail problemDetail = getProblemDetailBusiness();

        problemDetail.setProperty("errors", ex.getErrors());
        problemDetail.setTitle("validation-error");

        log.error(ex.getMessage());
        return problemDetail;
    }

    private ProblemDetail getProblemDetailBusiness() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setProperty("errorType", "business-error-type-validation");

        return problemDetail;
    }
}
