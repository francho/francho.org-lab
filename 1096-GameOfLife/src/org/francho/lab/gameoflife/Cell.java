/**
 * By Francho Joven - http://francho.org/lab/
 *
 * This work is licensed under the Creative Commons Attribution-Noncommercial-Share Alike 3.0 Unported License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ 
 * or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 *
 */
package org.francho.lab.gameoflife;

/**
 * @author francho
 *
 */
public class Cell {
	public enum Health { ALIVE, DEAD }
	
	private Health health = Health.DEAD;
	private Boolean survivor=null;

	/**
	 * 
	 * @param health
	 */
	public void setHealth(Health health) {
		this.health = health;
	}
	
	/**
	 * @return
	 */
	public boolean isAlive() {
		return health == Health.ALIVE;
	}

	/**
	 * 
	 * @param survivor
	 */
	public void setSurvivor(Boolean survivor) {
		this.survivor=survivor;
	}

	/**
	 * @return
	 */
	public Boolean isSurvivor() {
		return survivor;
	}

	/**
	 * 
	 * @param neighbors
	 */
	public void prepareNextGeneration(int neighbors) {
		if(isAlive()) {
			boolean willSurvive = (neighbors==2 || neighbors==3);
			setSurvivor(willSurvive);
		} else {
			boolean willBorn = (neighbors==3);
			setSurvivor(willBorn);
		}
	}

	/**
	 * 
	 */
	public void fixNextGeneration() {
		setHealth(isSurvivor() ? Health.ALIVE : Health.DEAD);
		survivor=null;
	}
	
}
