package com.robotic.hoover;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HooverServiceTest {

	HooverService service;
	
	@BeforeEach
    void init() {
		service = new HooverService();
    }
	
	@Test
	void testCleanOnePatch() {
		final Request request = new Request();
    	request.setCoords(new int [] {1, 2});
    	request.setInstructions("NNESEESWNWW");
    	request.setPatches(new int [][] {{1, 0}, {2, 2}, {2, 3}});
    	request.setRoomSize(new int [] {5, 5});
    	
    	Response response = service.startCleaning(request);
    	
    	assertEquals(1, response.getPatches());
    	assertEquals(1, response.getCoords()[0]);
    	assertEquals(3, response.getCoords()[1]);
	}
	
	@Test
	void testCleanOnePatchWithInvalidDirection() {
		final Request request = new Request();
		request.setCoords(new int [] {1, 2});
		request.setInstructions("NNESEUDLRESWNWW");
		request.setPatches(new int [][] {{1, 0}, {2, 2}, {2,3 }});
		request.setRoomSize(new int [] {5, 5});
		
		Response response = service.startCleaning(request);
		
		assertEquals(1, response.getPatches());
		assertEquals(1, response.getCoords()[0]);
		assertEquals(3, response.getCoords()[1]);
	}
	
	@Test
	void testCleanTwoPatches() {
		final Request request = new Request();
    	request.setCoords(new int [] {1, 1});
    	request.setInstructions("NEENW");
    	request.setPatches(new int [][] {{3, 2}, {3, 3}, {5, 0}, {5, 3}});
    	request.setRoomSize(new int [] {6, 6});
    	
    	Response response = service.startCleaning(request);
    	
    	assertEquals(2, response.getPatches());
    	assertEquals(2, response.getCoords()[0]);
    	assertEquals(3, response.getCoords()[1]);
	}
	
	@Test
	void testCleanTwoPatchesWithRobotHittingTheWestWall() {
		final Request request = new Request();
		request.setCoords(new int [] {1, 1});
		request.setInstructions("WWNEEEENWWWW");
		request.setPatches(new int [][] {{3, 2}, {3, 3}, {5, 0}, {5, 3}});
		request.setRoomSize(new int [] {6, 6});
		
		Response response = service.startCleaning(request);
		
		assertEquals(2, response.getPatches());
		assertEquals(0, response.getCoords()[0]);
		assertEquals(3, response.getCoords()[1]);
	}
	
	@Test
	void testCleanSevenPatches() {
		final Request request = new Request();
		request.setCoords(new int [] {0, 0});
		request.setInstructions("NNNSEEEENNNNWWEEEESSSESSS");
		request.setPatches(new int [][] {{0, 3}, {2, 6}, {4, 2}, {5, 6}, {6, 3}, {6, 4}, {6, 6}});
		request.setRoomSize(new int [] {8, 8});
		
		Response response = service.startCleaning(request);
		
		assertEquals(7, response.getPatches());
		assertEquals(7, response.getCoords()[0]);
		assertEquals(0, response.getCoords()[1]);
	}
	
	@Test
	void testCleanSevenPatchesHittingNorthWall() {
		final Request request = new Request();
		request.setCoords(new int [] {0, 0});
		request.setInstructions("NNNNNNNNNNNNSSSSSEEEENNNNWWEEEESSSESSS");
		request.setPatches(new int [][] {{0, 3}, {2, 6}, {4, 2}, {5, 6}, {6, 3}, {6, 4}, {6, 6}});
		request.setRoomSize(new int [] {8, 8});
		
		Response response = service.startCleaning(request);
		
		assertEquals(7, response.getPatches());
		assertEquals(7, response.getCoords()[0]);
		assertEquals(0, response.getCoords()[1]);
	}
	
	@Test
	void testCleanSevenPatchesHittingSouthWall() {
		final Request request = new Request();
		request.setCoords(new int [] {0, 0});
		request.setInstructions("SNNNSEEEENNNNWWEEEESSSESSS");
		request.setPatches(new int [][] {{0, 3}, {2, 6}, {4, 2}, {5, 6}, {6, 3}, {6, 4}, {6, 6}});
		request.setRoomSize(new int [] {8, 8});
		
		Response response = service.startCleaning(request);
		
		assertEquals(7, response.getPatches());
		assertEquals(7, response.getCoords()[0]);
		assertEquals(0, response.getCoords()[1]);
	}
	
	@Test
	void testCleanSevenPatchesHittingEastWall() {
		final Request request = new Request();
		request.setCoords(new int [] {0, 0});
		request.setInstructions("NNNSEEEENNNNWWEEEESSSESSSEE");
		request.setPatches(new int [][] {{0, 3}, {2, 6}, {4, 2}, {5, 6}, {6, 3}, {6, 4}, {6, 6}});
		request.setRoomSize(new int [] {8, 8});
		
		Response response = service.startCleaning(request);
		
		assertEquals(7, response.getPatches());
		assertEquals(7, response.getCoords()[0]);
		assertEquals(0, response.getCoords()[1]);
	}
	
	@Test
	void testCleanSingleCellRoom() {
		final Request request = new Request();
		request.setCoords(new int [] {0, 0});
		request.setInstructions("NSEW");
		request.setPatches(new int [][] {{}});
		request.setRoomSize(new int [] {1, 1});
		
		Response response = service.startCleaning(request);
		
		assertEquals(0, response.getPatches());
		assertEquals(0, response.getCoords()[0]);
		assertEquals(0, response.getCoords()[1]);
	}
	
	@Test
	void testCleanSingleCellRoomWithOneEmptyElement() {
		final Request request = new Request();
		request.setCoords(new int [] {0, 0});
		request.setInstructions("NSEW");
		request.setPatches(new int [][] {{}, {0, 0}});
		request.setRoomSize(new int [] {1, 1});
		
		Response response = service.startCleaning(request);
		
		assertEquals(1, response.getPatches());
		assertEquals(0, response.getCoords()[0]);
		assertEquals(0, response.getCoords()[1]);
	}
	
	@Test
	void testCleanRoomwithDifferentDimensions() {
		final Request request = new Request();
		request.setCoords(new int [] {2, 1});
		request.setInstructions("SSSSNNEEEEESWNEEEEEEEEEEE");
		request.setPatches(new int [][] {
			{2, 1}, 
			{2, 2},
			{3},
			{4, 2},
			{6, 1},
			{6, 2},
			{7, 1},
			{7, 2},
			{9, 1},
			{11, 1},
			{11, 2}});
		request.setRoomSize(new int [] {12, 3});
		
		Response response = service.startCleaning(request);
		
		assertEquals(8, response.getPatches());
		assertEquals(11, response.getCoords()[0]);
		assertEquals(2, response.getCoords()[1]);
	}
	
	@Test
	void testCleanRoomwithDifferentDimensionsPatchOutsideBorders() {
		final Request request = new Request();
		request.setCoords(new int [] {2, 1});
		request.setInstructions("SSSSNNEEEEESWNEEEEEEEEEEE");
		request.setPatches(new int [][] {
			{2, 1}, 
			{2, 2},
			{3},
			{4, 2},
			{6, 1},
			{6, 2},
			{7, 1},
			{7, 2},
			{14, 1},
			{0, 20},
			{0, 20, 10},
			{9, 1},
			{11, 1},
			{11, 2}});
		request.setRoomSize(new int [] {12, 3});
		
		Response response = service.startCleaning(request);
		
		assertEquals(8, response.getPatches());
		assertEquals(11, response.getCoords()[0]);
		assertEquals(2, response.getCoords()[1]);
	}
}
