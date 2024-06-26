package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.application.domain.exception.business.BusinessException;
import by.grsu.skydiving.application.domain.exception.business.IncorrectPasswordException;
import by.grsu.skydiving.application.domain.exception.business.UserNotFoundException;
import by.grsu.skydiving.application.domain.exception.domain.DomainException;
import by.grsu.skydiving.application.domain.exception.domain.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {
    private static final String VALIDATION_ERRORS_FIELD_NAME = "errors";
    private static final String VALIDATION_ERROR_TITLE = "validation-error";
    private static final String ERROR_TYPE = "errorType";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetail onException(
        Exception ex
    ) {
        ProblemDetail problemDetail = getProblemDetailInternalServerError();
        problemDetail.setDetail("Произошла непредвиденная ошибка сервера, попробуйте повторить запрос позже.");

        log.error(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail onConstraintValidationException(
        ConstraintViolationException e
    ) {
        ProblemDetail problemDetail = getProblemDetailBusiness();

        Map<String, String> errors = e.getConstraintViolations().stream()
            .collect(Collectors.toMap(
                violation -> violation.getPropertyPath().toString(),
                ConstraintViolation::getMessage
            ));

        problemDetail.setProperty(VALIDATION_ERRORS_FIELD_NAME, errors);
        problemDetail.setTitle(VALIDATION_ERROR_TITLE);

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail onMethodArgumentNotValidException(
        MethodArgumentNotValidException e
    ) {
        ProblemDetail problemDetail = getProblemDetailBusiness();

        Map<String, String> errors = e.getFieldErrors().stream()
            .collect(Collectors.toMap(
                FieldError::getField,
                DefaultMessageSourceResolvable::getDefaultMessage
            ));

        problemDetail.setProperty(VALIDATION_ERRORS_FIELD_NAME, errors);
        problemDetail.setTitle(VALIDATION_ERROR_TITLE);

        return problemDetail;
    }

    @ExceptionHandler(ValidationException.class)
    public ProblemDetail handleValidationException(ValidationException ex) {
        ProblemDetail problemDetail = getProblemDetailBusiness();

        problemDetail.setProperty(VALIDATION_ERRORS_FIELD_NAME, ex.getErrors());
        problemDetail.setTitle(VALIDATION_ERROR_TITLE);

        log.error(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler({IncorrectPasswordException.class, UserNotFoundException.class})
    public ProblemDetail handleAuthExceptions(BusinessException ex) {
        ProblemDetail problemDetail = getProblemDetailBusiness();

        problemDetail.setStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setTitle("Ошибка авторизации");

        log.error(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex) {
        ProblemDetail problemDetail = getProblemDetailBusiness();

        problemDetail.setDetail(ex.getMessage());

        log.error(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(DomainException.class)
    public ProblemDetail handleDomainException(DomainException ex) {
        ProblemDetail problemDetail = getProblemDetailDomain();

        problemDetail.setDetail(ex.getMessage());

        log.error(ex.getMessage());
        return problemDetail;
    }

    private ProblemDetail getProblemDetailDomain() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setProperty(ERROR_TYPE, "domain-error-type-validation");
        problemDetail.setTitle("Ошибка логики предметной области");


        return problemDetail;
    }

    private ProblemDetail getProblemDetailBusiness() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setProperty(ERROR_TYPE, "business-error-type-validation");
        problemDetail.setTitle("Ошибка бизнес логики");


        return problemDetail;
    }

    private ProblemDetail getProblemDetailInternalServerError() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setProperty(ERROR_TYPE, "internal-server-error");
        problemDetail.setTitle("Внутренняя ошибка сервера");

        return problemDetail;
    }
}
