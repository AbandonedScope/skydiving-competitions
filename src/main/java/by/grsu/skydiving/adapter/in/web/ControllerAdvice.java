package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.application.domain.exception.business.BusinessException;
import by.grsu.skydiving.application.domain.exception.business.IncorrectPasswordException;
import by.grsu.skydiving.application.domain.exception.business.SkydiverWithNameAndBirthDateAlreadyExistsException;
import by.grsu.skydiving.application.domain.exception.business.UserNotFoundException;
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

    @ExceptionHandler(SkydiverWithNameAndBirthDateAlreadyExistsException.class)
    public ProblemDetail handleSkydiverWithNameAndBirthDateAlreadyExistsException(
        SkydiverWithNameAndBirthDateAlreadyExistsException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);

        problemDetail.setDetail(ex.getMessage());
        problemDetail.setTitle("skydiver-creation-error");
        problemDetail.setProperty("errorType", "business-error-type-conflict");

        log.error(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler({IncorrectPasswordException.class, UserNotFoundException.class})
    public ProblemDetail handleAuthExceptions(BusinessException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);

        problemDetail.setDetail(ex.getMessage());
        problemDetail.setTitle("business-error");

        log.error(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex) {
        ProblemDetail problemDetail = getProblemDetailBusiness();

        problemDetail.setDetail(ex.getMessage());
        problemDetail.setTitle("business-error");

        log.error(ex.getMessage());
        return problemDetail;
    }

    private ProblemDetail getProblemDetailBusiness() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setProperty("errorType", "business-error-type-validation");

        return problemDetail;
    }
}
