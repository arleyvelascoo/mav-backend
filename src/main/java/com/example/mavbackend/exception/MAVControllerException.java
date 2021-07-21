package com.example.mavbackend.exception;

import com.example.mavbackend.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * Controller for exception
 */

@Slf4j
@RestControllerAdvice
public class MAVControllerException extends Exception {
    private static final long serialVersionUID = -7223039678427116785L;

    /**
     * Throw this when catch a MethodArgumentNotValidException
     *
     * @param request -
     * @param e       -
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDTO> invalidObjectEntry(HttpServletRequest request, MethodArgumentNotValidException e) {
        final var httpCode = HttpStatus.BAD_REQUEST;

        var result = e.getBindingResult();
        var fieldErrorList = result.getFieldErrors();

        var errorMessage = new StringBuilder();
        fieldErrorList.forEach(f -> errorMessage.append(f.getField()).append(" ").
                append(f.getDefaultMessage()).append(" "));

        return new ResponseEntity<>(new ErrorDTO(httpCode.value(), errorMessage.toString(), "warning",
                request.getRequestURI()), httpCode);
    }

    /**
     * Catch a exception related with database
     *
     * @param request -
     * @param e       -
     */
    @ExceptionHandler({ConstraintViolationException.class, TransientPropertyValueException.class,
            org.hibernate.exception.ConstraintViolationException.class,
            java.sql.SQLIntegrityConstraintViolationException.class, javax.persistence.PersistenceException.class,
            InvalidDataAccessApiUsageException.class, GenericJDBCException.class})
    public ResponseEntity<ErrorDTO> transactionNotSuccessfulDataBase(HttpServletRequest request, Exception e) {
        final var httpCode = HttpStatus.CONFLICT;
        return new ResponseEntity<>(new ErrorDTO(httpCode.value(), "valores inválidos de base de datos",
                "error", request.getRequestURI()), httpCode);
    }

    /**
     * Catch a NullPointerException
     *
     * @param request -
     * @param e       -
     */
    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ErrorDTO> nullPointerException(HttpServletRequest request, NullPointerException e) {
        final var httpCode = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(new ErrorDTO(httpCode.value(), "valores nulos", "error",
                request.getRequestURI()), httpCode);
    }

    /**
     * Catch a MAVValidationException
     *
     * @param request -
     * @param e       -
     */
    @ExceptionHandler(MAVValidationException.class)
    public ResponseEntity<ErrorDTO> validationException(HttpServletRequest request, MAVValidationException e) {
        final var httpCode = HttpStatus.CONFLICT;
        return new ResponseEntity<>(new ErrorDTO(httpCode.value(), e.getMessage(), "warning",
                request.getRequestURI()), httpCode);
    }
}
