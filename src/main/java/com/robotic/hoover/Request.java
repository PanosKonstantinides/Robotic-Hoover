package com.robotic.hoover;

import java.util.Arrays;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The hoover request.
 * 
 * @author panos
 */
public class Request {
	@NotEmpty(message = "The room size array must not be empty")
    @Size( min = 2, max = 2, message = "The room size array size must be 2")
	private int [] roomSize;
	@NotEmpty(message = "The coordinations array must not be empty")
    @Size( min = 2, max = 2, message = "The coordinations array size must be 2")
	private int [] coords;
	@NotNull(message = "The patches array must not be null")
	private int [][] patches;
	@NotEmpty(message = "The instructions must not be empty")
	private String instructions;

	/**
	 * The room dimensions. It should contain x and y coordinates.
	 * 
	 * @return	the room dimensions.
	 */
	public int[] getRoomSize() {
		return roomSize;
	}
	
	/**
	 * Sets the imaginary room dimensions. It should contain x and y coordinates.
	 * 
	 * @param roomSize	the room dimensions.
	 */
	public void setRoomSize(final int [] roomSize) {
		this.roomSize = roomSize;
	}
	
	/**
	 * The initial position of the hoover. It should contain x and y coordinates.
	 * 
	 * @return	the coordinates.
	 */
	public int [] getCoords() {
		return coords;
	}
	
	/**
	 * Sets the initial position of the hoover. It should contain x and y coordinates.
	 * 
	 * @param coords	the coordinates.
	 */
	public void setCoords(final int[] coords) {
		this.coords = coords;
	}
	
	/**
	 * The patches of dirt. This is a two dimensional array which should contains several
	 * pairs of x and y coordinates.
	 * 
	 * @return	the coordinates of the patches of dirt.
	 */
	public int [][] getPatches() {
		return patches;
	}
	
	/**
	 * Sets the patches of dirt. This is a two dimensional array which should contains several
	 * pairs of x and y coordinates.
	 * 
	 * @param patches	the coordinates of the patches of dirt.
	 */
	public void setPatches(final int [][] patches) {
		this.patches = patches;
	}
	
	/**
	 * The direction of the hoover. Those are the instructions that direct
	 * the hoover which way to go. Only 'N', 'S', 'E' and 'W' are allowed as values.
	 * Any other value will be ignored.
	 * 
	 * @return	the instructions.
	 */
	public String getInstructions() {
		return instructions;
	}
	
	/**
	 * Sets the instructions. Those are the instructions that direct the hoover 
	 * which way to go. Only 'N', 'S', 'E' and 'W' are allowed as values. Any 
	 * other value will be ignored.
	 * 
	 * @param instructions	the cardinal directions.
	 */
	public void setInstructions(final String instructions) {
		this.instructions = instructions;
	}

	/**
	 * A string representation of this object.
	 */
	@Override
	public String toString() {
		return "Request [roomSize=" + Arrays.toString(roomSize) + ", coords=" + Arrays.toString(coords) + ", patches="
				+ Arrays.deepToString(patches) + ", instructions=" + instructions + "]";
	}
}
