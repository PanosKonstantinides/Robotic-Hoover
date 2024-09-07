package com.robotic.hoover;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 * The service class. Will handle all business logic.
 * 
 * @author panos
 *
 */
@Service
class HooverService {
	private static final int LOWER_LIMIT = 0;

	/**
	 * Does the cleaning of the patches.
	 * 
	 * @param request	the request with all relevant data.
	 * @return			the hoover's final position.
	 */
	Response startCleaning(final Request request) {
		final int xAxisUpperLimit = request.getRoomSize()[0];
		final int yAxisUpperLimit = request.getRoomSize()[1];
		
		final char [] directions = request.getInstructions().toCharArray();
		final int [] initialPosition = request.getCoords();
		int x = initialPosition[0];
		int y = initialPosition[1];
		
		final int [] currentPosition = {x, y};
		
		final Set<Patch> patches = getPatchesAsSet(request.getPatches());
		// We call this in case the starting point of the hoover is on a patch.
		int patchesCleaned = cleanPatchIfNecessary(x, y, patches, 0);
		
		for (char direction : directions) {
			switch (direction) {
				case 'N':	// UP
					if (y + 1 >= yAxisUpperLimit) {
						checkIfLimitIsReached();
					} else {
						y++;
						setCurrentPosition(x, y, currentPosition);
						patchesCleaned = cleanPatchIfNecessary(x, y, patches, patchesCleaned);
					}
					break;
				case 'S':	// DOWN
					if (y - 1 < LOWER_LIMIT) {
						checkIfLimitIsReached();
					} else {
						y--;
						setCurrentPosition(x, y, currentPosition);
						patchesCleaned = cleanPatchIfNecessary(x, y, patches, patchesCleaned);
					}
					break;
				case 'E':	// RIGHT
					if (x + 1 >= xAxisUpperLimit) {
						checkIfLimitIsReached();
					} else {
						x++;
						setCurrentPosition(x, y, currentPosition);
						patchesCleaned = cleanPatchIfNecessary(x, y, patches, patchesCleaned);
					}
					break;
				case 'W':	// LEFT
					if (x - 1 < LOWER_LIMIT) {
						checkIfLimitIsReached();
					} else {
						x--;
						setCurrentPosition(x, y, currentPosition);
						patchesCleaned = cleanPatchIfNecessary(x, y, patches, patchesCleaned);
					}
					break;
				default:
					System.out.println("Invalid direction '" + direction + "'. Will ignore...");
			}
			
		}
		return new Response(currentPosition, patchesCleaned);
	}

	private int cleanPatchIfNecessary(
			final int x, final int y, final Set<Patch> patches, int patchesCleaned) {
		final Patch p = new Patch(x, y);
		if (patches.contains(p)) {
			patchesCleaned++;
			patches.remove(p);
		}
		return patchesCleaned;
	}

	private void setCurrentPosition(int x, int y, int[] currentPosition) {
		currentPosition[0] = x;
		currentPosition[1] = y;
	}

	private Set<Patch> getPatchesAsSet(final int [][] patches) {
		final Set<Patch> currentpatches = new HashSet<>(patches.length);
		for (int [] patch : patches) {
			// Only include data that has both coordinates. Empty data or data 
			// that contains only one (or more than 2) coordinate(s) will be ignored.
			if (patch.length == 2) {
				currentpatches.add(new Patch(patch[0], patch[1]));
			}
		}
		return currentpatches;
	}

	private void checkIfLimitIsReached() {
		System.out.println("I reached the boundaries. Will stay where I am.");
	}
	
	/*
	 * Defines a patch. This is a convenience class in order to help me process the
	 * cleaned patches easier.
	 */
	private final class Patch {
		private final int x;
		private final int y;

		/*
		 * Constructor.
		 * 
		 * @param x	the x coordinate (column).
		 * @param y	the y coordinate (row).
		 */
		Patch(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Patch other = (Patch) obj;
			return x == other.x && y == other.y;
		}
	}
}
