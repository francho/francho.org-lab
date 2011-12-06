/**
 * By Francho Joven - http://francho.org/lab/
 *
 * This work is licensed under the Creative Commons Attribution-Noncommercial-Share Alike 3.0 Unported License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ 
 * or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 *
 */
package org.francho.lab.gameoflife.test;

import org.francho.lab.gameoflife.WorldView;
import org.francho.lab.gameoflife.WorldView.WorldListener;

import android.test.InstrumentationTestCase;

/**
 * @author francho
 *
 */
public class TestWorldView extends InstrumentationTestCase {

	
	
	public void testViewCreation() {
		WorldView world = new WorldView(getInstrumentation().getContext());
		
		assertNotNull(world);
		
		world.setWorld(3, 3);
	}
	
	public void testWorldListener() {
		WorldListener listener = new WorldListener() {	
			@Override
			public void onNextGeneration(int day, int liveCells) {
			}
			
			@Override
			public void onInitWorld() {
			}
		};
		
		WorldView world = new WorldView(getInstrumentation().getContext());
		world.setWorldListener(listener);
		
		assertNotNull(world);
	}
	
	public void testNextGeneration() {
		WorldView world = new WorldView(getInstrumentation().getContext());
		world.setWorld(3, 3);
		
		world.setWorldListener(new WorldListener() {	
			@Override
			public void onNextGeneration(int day, int liveCells) {
				assertEquals(1, day);
			}
			
			@Override
			public void onInitWorld() {
			}
		});
		
		world.nextGeneration();
	}
}
