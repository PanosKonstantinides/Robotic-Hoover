package com.robotic.hoover;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Defines the end-points for the hoover functionality.
 * 
 * @author panos
 *
 */
@Controller
@RequestMapping("/hoover")
public class HooverController {
	private static final int LOWER_LIMIT = 0;
	
	private final HooverService service;
	
	/**
	 * Constructor.
	 * 
	 * @param service	the service class that handles all business logic.
	 */
	public HooverController(final HooverService service) {
		this.service = service;
	}

	/**
	 * Cleans the patches of dirt.
	 * 
	 * @param request	the payload.
	 * @return			the response of the cleaning action.
	 */
	@Operation(summary = "It cleans the patches of dirt in a room according to the given instructions.",
            description = "It cleans the patches of dirt in a room according to the given instructions.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "When the cleaning is succssful",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class)) }),
            @ApiResponse(responseCode = "403", description = "When the request is invalid.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "When an unexpected error occurs.",
                    content = @Content) })
	@PostMapping(
			path = "cleaning", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cleanPatches(@Valid @RequestBody final Request request) {
		System.out.println("Method entered with " + request);
		
		// Check if hoover's original position is outside the room.
		if (request.getCoords()[0] < LOWER_LIMIT
			|| request.getCoords()[0] >= request.getRoomSize()[0]
			|| request.getCoords()[1] < LOWER_LIMIT
			|| request.getCoords()[1] >= request.getRoomSize()[1]) {
			
			Map<String, String> errors = Map.of("Starting Point", "Wrong starting point, outside the room.");
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		Response res = service.startCleaning(request);
		ResponseEntity<Response> response = ResponseEntity.ok(res);
		
		System.out.println("Method exited with " + response);
        return response;
    }
}
