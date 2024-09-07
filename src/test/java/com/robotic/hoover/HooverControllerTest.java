package com.robotic.hoover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import javax.validation.Valid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ExtendWith(MockitoExtension.class)
public class HooverControllerTest {
	@Mock
    HooverService service;
	
	HooverController controller;

    @BeforeEach
    void init() {
        controller = new HooverController(service);
    }
    
    @Test
    void cleanPatches() {
    	final Request request = new Request();
    	request.setCoords(new int [] {1, 2});
    	request.setInstructions("NWNWEEESW");
    	request.setPatches(new int [][] {{2,3}, {3,4}, {5,6}});
    	request.setRoomSize(new int [] {7,7});
    	
    	final Response response = new Response(new int [] {1, 3}, 1);
    	
    	when(service.startCleaning(request)).thenReturn(response);
    	
		final ResponseEntity<?> res = controller.cleanPatches(request);
		
		verify(service).startCleaning(request);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
    }
    
    @Test
    void wrongXCoordinatesLowerLimit() {
    	final Request request = new Request();
    	request.setCoords(new int [] {-1, 2});
    	request.setInstructions("NWNWEEESW");
    	request.setPatches(new int [][] {{2,3}, {3,4}, {5,6}});
    	request.setRoomSize(new int [] {7,7});
    	
    	final Response response = new Response(new int [] {1, 3}, 1);
    	
    	final ResponseEntity<?> res = controller.cleanPatches(request);
    	
    	verify(service, never()).startCleaning(request);
    	
    	assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
    	assertEquals("Wrong starting point, outside the room.", ((Map<String, String>) res.getBody()).get("Starting Point"));
    }
    
    @Test
    void wrongXCoordinatesUpperLimit() {
    	final Request request = new Request();
    	request.setCoords(new int [] {8, 2});
    	request.setInstructions("NWNWEEESW");
    	request.setPatches(new int [][] {{2,3}, {3,4}, {5,6}});
    	request.setRoomSize(new int [] {7,7});
    	
    	final Response response = new Response(new int [] {1, 3}, 1);
    	
    	final ResponseEntity<?> res = controller.cleanPatches(request);
    	
    	verify(service, never()).startCleaning(request);
    	
    	assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
    	assertEquals("Wrong starting point, outside the room.", ((Map<String, String>) res.getBody()).get("Starting Point"));
    }
    
    @Test
    void wrongYCoordinatesUpperLimit() {
    	final Request request = new Request();
    	request.setCoords(new int [] {1, 7});
    	request.setInstructions("NWNWEEESW");
    	request.setPatches(new int [][] {{2,3}, {3,4}, {5,6}});
    	request.setRoomSize(new int [] {7,7});
    	
    	final Response response = new Response(new int [] {1, 3}, 1);
    	
    	final ResponseEntity<?> res = controller.cleanPatches(request);
    	
    	verify(service, never()).startCleaning(request);
    	
    	assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
    	assertEquals("Wrong starting point, outside the room.", ((Map<String, String>) res.getBody()).get("Starting Point"));
    }
    
    @Test
    void wrongYCoordinatesLowerLimit() {
    	final Request request = new Request();
    	request.setCoords(new int [] {1, -5});
    	request.setInstructions("NWNWEEESW");
    	request.setPatches(new int [][] {{2,3}, {3,4}, {5,6}});
    	request.setRoomSize(new int [] {7,7});
    	
    	final Response response = new Response(new int [] {1, 3}, 1);
    	
    	final ResponseEntity<?> res = controller.cleanPatches(request);
    	
    	verify(service, never()).startCleaning(request);
    	
    	assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
    	assertEquals("Wrong starting point, outside the room.", ((Map<String, String>) res.getBody()).get("Starting Point"));
    }
    
}
