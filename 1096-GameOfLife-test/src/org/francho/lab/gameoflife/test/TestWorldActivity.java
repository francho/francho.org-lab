/**
 * By Francho Joven - http://francho.org/lab/
 *
 * This work is licensed under the Creative Commons Attribution-Noncommercial-Share Alike 3.0 Unported License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ 
 * or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 *
 */
package org.francho.lab.gameoflife.test;

import org.francho.lab.gameoflife.WorldActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

/**
 * @author francho
 *
 */
public class TestWorldActivity extends ActivityInstrumentationTestCase2<WorldActivity> {

	/**
	 * @param pkg
	 * @param activityClass
	 */
	public TestWorldActivity() {
		super("org.francho.lab.gameoflife", WorldActivity.class);
	}

	public void testActivityCreation() {
		WorldActivity activity = getActivity();
		assertNotNull(activity);
	}
	
	@UiThreadTest
	public void testButtonAuto() {
		View button = getActivity().findViewById(org.francho.lab.gameoflife.R.id.btn_onOff);
		
		assertNotNull(button);
		assertTrue(button instanceof ToggleButton);
		
		((ToggleButton) button).performClick();
	}
	
	@UiThreadTest
	public void testButtonClear() {
		View button = getActivity().findViewById(org.francho.lab.gameoflife.R.id.btn_clear);
		
		assertNotNull(button);
		assertTrue(button instanceof Button);
		
		((Button) button).performClick();
	}
	
	@UiThreadTest
	public void testButtonNext() {
		View button = getActivity().findViewById(org.francho.lab.gameoflife.R.id.btn_next);
		
		assertNotNull(button);
		assertTrue(button instanceof Button);
		
		((Button) button).performClick();
	}
}
