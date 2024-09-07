package com.robotic.hoover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ExtendWith(MockitoExtension.class)
public class ValidationErrorHandlerTest {

	ValidationErrorHandler errorHandler;

    @BeforeEach
    void init() {
    	errorHandler = new ValidationErrorHandler();
    }
    
    @Test
    void handleErrors() {
    	final FieldError roomSizeError = new FieldError("roomSize", "roomSize", "The room size array size must be 2");
    	final FieldError coordsError = new FieldError("coords", "coords", "The coordinations array size must be 2");
    	final List<FieldError> fieldErrors = List.of(roomSizeError, coordsError);
    	
    	final BindingResult br = Mockito.mock(BindingResult.class);
    	final MethodParameter mp = Mockito.mock(MethodParameter.class);
    	MethodArgumentNotValidException ex = new MethodArgumentNotValidException(mp, br);
    	
    	when(br.getFieldErrors()).thenReturn(fieldErrors);
    			
    	ResponseEntity<Map<String, String>> response = errorHandler.handleValidationErrors(ex);
    	
    	verify(br).getFieldErrors();
    	
    	assertEquals(2, response.getBody().size());
    	
    	Map<String, String> body = response.getBody();
    	
    	assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    	assertEquals("The room size array size must be 2", body.get("roomSize"));
    	assertEquals("The coordinations array size must be 2", body.get("coords"));
    }
    
}
