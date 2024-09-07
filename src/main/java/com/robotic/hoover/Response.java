package com.robotic.hoover;

import java.util.Arrays;

/**
 * The response, after the hoover performed its actions.
 * 
 * @author panos
 *
 */
public final class Response {
	private final int [] coords;
	private final int patches;
	
	/**
	 * Constructor.
	 * 
	 * @param coords	the coordinates of the final position of the hoover. 
	 * 					It should contain x and y coordinates.
	 * @param patches	the number of patches that have been cleaned up.
	 */
	public Response(final int [] coords, final int patches) {
		this.coords = coords.clone();
		this.patches = patches;
	}

	/**
	 * The coordinates of the final position of the hoover. It should contain x and y coordinates.
	 * 
	 * @return	the position of the hoover.
	 */
	public int [] getCoords() {
		return coords.clone();
	}
	
	/**
	 * The number of patches that have been cleaned-up.
	 * 
	 * @return	the number of patches that have been cleaned-up.
	 */
	public int getPatches() {
		return patches;
	}

	@Override
	public String toString() {
		return "Response [coords=" + Arrays.toString(coords) + ", patches=" + patches + "]";
	}
}
