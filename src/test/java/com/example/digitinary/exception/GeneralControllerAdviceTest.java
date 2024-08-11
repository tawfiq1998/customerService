package com.example.digitinary.exception;

import com.example.digitinary.util.ResponseMessage;
import jakarta.validation.Path;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GeneralControllerAdviceTest {

    @InjectMocks
    private GeneralControllerAdvice generalControllerAdvice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRuntimeExceptionHandler() {
        RuntimeException exception = new RuntimeException("Runtime Exception");

        ResponseEntity<ResponseMessage> response = generalControllerAdvice.runtimeExceptionHandler(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Runtime Exception", response.getBody().getMessage());
    }

    @Test
    public void testHandleConstraintViolationException() {
        ConstraintViolation<?> violation = new MockConstraintViolation(PathImpl.createPathFromString("fieldName"), "errorMessage");
        Set<ConstraintViolation<?>> violations = Set.of(violation);
        ConstraintViolationException exception = new ConstraintViolationException(violations);

        ResponseEntity<Map<String, String>> response = generalControllerAdvice.handleConstraintViolationException(exception);

        Map<String, String> expectedErrors = new HashMap<>();
        expectedErrors.put("fieldName", "errorMessage");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedErrors, response.getBody());
    }

    @Test
    public void testHandleValidationExceptions() {
        FieldError fieldError = new FieldError("objectName", "fieldName", "errorMessage");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getAllErrors()).thenReturn(List.of(fieldError));
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);
        when(exception.getBindingResult().getAllErrors()).thenReturn(List.of(fieldError));

        ResponseEntity<Map<String, String>> response = generalControllerAdvice.handleValidationExceptions(exception);

        Map<String, String> expectedErrors = new HashMap<>();
        expectedErrors.put("fieldName", "errorMessage");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedErrors, response.getBody());
    }

    // Mock ConstraintViolation for testing purposes
    private static class MockConstraintViolation implements ConstraintViolation<Object> {
        private final Path propertyPath;
        private final String message;

        public MockConstraintViolation(Path propertyPath, String message) {
            this.propertyPath = propertyPath;
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public String getMessageTemplate() {
            return "";
        }

        @Override
        public Object getRootBean() {
            return null;
        }

        @Override
        public Class<Object> getRootBeanClass() {
            return null;
        }

        @Override
        public Object getInvalidValue() {
            return null;
        }

        @Override
        public ConstraintDescriptor<?> getConstraintDescriptor() {
            return null;
        }

        @Override
        public <U> U unwrap(Class<U> aClass) {
            return null;
        }

        @Override
        public Path getPropertyPath() {
            return propertyPath;
        }

        @Override
        public Class<?> getLeafBean() {
            return Object.class;
        }

        @Override
        public Object[] getExecutableParameters() {
            return new Object[0];
        }

        @Override
        public Object getExecutableReturnValue() {
            return null;
        }


    }
}
