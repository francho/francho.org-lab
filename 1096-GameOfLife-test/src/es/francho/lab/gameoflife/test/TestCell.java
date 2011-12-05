/**
 * By Francho Joven - http://francho.org/lab/
 *
 * This work is licensed under the Creative Commons Attribution-Noncommercial-Share Alike 3.0 Unported License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ 
 * or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 *
 */
package es.francho.lab.gameoflife.test;

import junit.framework.TestCase;
import es.francho.lab.gameoflife.Cell;
import es.francho.lab.gameoflife.Cell.Health;

/**
 * @author francho
 *
 */
public class TestCell extends TestCase {

	private Cell mCell;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		mCell = new Cell();
	}
	
	public void testInitHealth() {
		Cell cell = new Cell();
		assertFalse(cell.isAlive());	
	}
	
	public void testSetHealthAlive() {
		mCell.setHealth(Health.ALIVE);
		assertTrue(mCell.isAlive());
	}
	
	public void testSetHealthDeath() {
		mCell.setHealth(Health.DEAD);
		assertFalse(mCell.isAlive());
	}
	
	public void testSetNextHealthAlive() {
		mCell.setSurvivor(true);
		assertTrue(mCell.isSurvivor());
	}
	
	public void testSetNextHealthDeath() {
		mCell.setSurvivor(false);
		assertFalse(mCell.isSurvivor());
	}
	
	public void testBorn() {
		mCell.setHealth(Health.DEAD);
		mCell.prepareNextGeneration(3);
		assertTrue(mCell.isSurvivor());
	}
	
	public void testKeepAlive() {
		int[] neighboursKeepAlive = new int[]{ 2,3 };
		
		for (int neighbors : neighboursKeepAlive) {
			mCell.setHealth(Health.ALIVE);
			mCell.prepareNextGeneration(neighbors);
			assertTrue("a cell live with " + neighbors + " neighbours will survive" , mCell.isSurvivor());
		}
	}
	
	public void testDie() {
		int[] neighboursKeepAlive = new int[]{ 1,4,5,6,7,8 };
		
		for (int neighbors : neighboursKeepAlive) {
			mCell.setHealth(Health.ALIVE);
			mCell.prepareNextGeneration(neighbors);
			assertFalse("a cell live with " + neighbors + " neighbours will die" , mCell.isSurvivor());
		}
	}
	
}
