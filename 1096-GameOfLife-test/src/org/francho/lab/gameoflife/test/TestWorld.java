/**
 * By Francho Joven - http://francho.org/lab/
 *
 * This work is licensed under the Creative Commons Attribution-Noncommercial-Share Alike 3.0 Unported License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ 
 * or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 *
 */
package org.francho.lab.gameoflife.test;

import org.francho.lab.gameoflife.Cell;
import org.francho.lab.gameoflife.World;
import org.francho.lab.gameoflife.Cell.Health;

import junit.framework.TestCase;

/**
 * @author francho
 *
 */
public class TestWorld extends TestCase {

	public void testInitWorld() {
		World world = new World(3,3);
		assertNotNull(world);
	}
	
	public void testGetCell() {
		World world = new World(3,3);
		
		Cell cell = world.getCell(0,0);
		assertNotNull(cell);

		cell = world.getCell(2,2);
		assertNotNull(cell);
	}
	
	public void testGetLiveNeighbors() {
		World world = new World(3,3);
		initWorldAllLives(world);
		
		int neighbords=world.getLiveNeighbors(1,1);
		assertEquals(8, neighbords);
		
		neighbords=world.getLiveNeighbors(0,0);
		assertEquals(3, neighbords);
		
		neighbords=world.getLiveNeighbors(2,2);
		assertEquals(3, neighbords);
	}
	
	public void testNextDay() {
		World world = new World(3,4);
		
		// * * * * 
		// * - * *
		// * * * *
		initWorldAllLives(world);
		world.getCell(1,1).setHealth(Health.DEAD);
		
		// * - - *
		// - - - -
		// * - - *
		world.nextGeneration();
		
		assertTrue(world.getCell(0, 0).isAlive());
		assertFalse(world.getCell(0, 1).isAlive());
		assertFalse(world.getCell(0, 2).isAlive());
		assertTrue(world.getCell(0, 3).isAlive());
		
		assertFalse(world.getCell(1, 0).isAlive());
		assertFalse(world.getCell(1, 1).isAlive());
		assertFalse(world.getCell(1, 2).isAlive());
		assertFalse(world.getCell(1, 3).isAlive());
		
		assertTrue(world.getCell(2, 0).isAlive());
		assertFalse(world.getCell(2, 1).isAlive());
		assertFalse(world.getCell(2, 2).isAlive());
		assertTrue(world.getCell(2, 3).isAlive());
		
	}
	
	public void testNumGenerations() {
		World world = new World(3,4);
		
		assertEquals(0, world.getNumGenerations());
		world.nextGeneration();
		assertEquals(1, world.getNumGenerations());
	}

	
	public void testNumLiveCells() {
		World world = new World(3,3);
		initWorldAllLives(world);
		
		assertEquals(9, world.getNumLiveCells());
	}
	
	/**
	 * @param world
	 */
	private void initWorldAllLives(World world) {
		for(int row=0;row<world.getNumRows();row++) {
			for(int col=0;col<world.getNumCols();col++) {
				Cell cell = world.getCell(row, col);
				cell.setHealth(Health.ALIVE);
			}
		}
	}
}
