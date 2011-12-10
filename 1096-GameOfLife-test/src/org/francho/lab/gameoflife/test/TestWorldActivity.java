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
import org.francho.lab.gameoflife.WorldView;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.ToggleButton;

import com.jayway.android.robotium.solo.Solo;

/**
 * @author francho
 *
 */
public class TestWorldActivity extends ActivityInstrumentationTestCase2<WorldActivity> {

	private WorldActivity mActivity;
	private WorldView mWorldView;
	private Instrumentation mInstr;
	private ToggleButton mButtonOnOff;
	private Button mButtonClear;
	private Button mButtonNext;
	private Solo mSolo;

	/**
	 * @param pkg
	 * @param activityClass
	 */
	public TestWorldActivity() {
		super("org.francho.lab.gameoflife", WorldActivity.class);
	}
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		
		setActivityInitialTouchMode(true);
		
		mActivity = getActivity();
		mInstr = getInstrumentation();
		
		mWorldView = (WorldView) mActivity.findViewById(org.francho.lab.gameoflife.R.id.world);
		mButtonOnOff = (ToggleButton) mActivity.findViewById(org.francho.lab.gameoflife.R.id.btn_onOff);
		mButtonClear = (Button) mActivity.findViewById(org.francho.lab.gameoflife.R.id.btn_clear);
		mButtonNext = (Button) mActivity.findViewById(org.francho.lab.gameoflife.R.id.btn_next);
		
		mSolo = new Solo(getInstrumentation(), getActivity());
	}

	public void testPreConditions() {		
		assertNotNull(mActivity);
		assertNotNull(mWorldView);
		assertNotNull(mButtonOnOff);
		assertNotNull(mButtonClear);
		assertNotNull(mButtonNext);
	}
	
	@UiThreadTest
	public void testButtonAuto() {
		mButtonOnOff.performClick();
		assertTrue(true);
	}
	
	@UiThreadTest
	public void testButtonClear() {
		mButtonClear.performClick();
		assertTrue(true);
	}
	
	@UiThreadTest
	public void testButtonNext() {
		mButtonNext.performClick();
		assertTrue(true);
	}
	
	public void testAboutDialog() {
		mSolo.clickOnMenuItem(mSolo.getString(org.francho.lab.gameoflife.R.string.menu_about));
		String about_title=mSolo.getString(org.francho.lab.gameoflife.R.string.about_title);
		assertEquals(true, mSolo.searchText(about_title));
	}
	
	// TODO How to test onCreate with saved boundle?
	
//	public void testSavedInstance() {
//		mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//		
//		WorldView worldView1 = (WorldView) mActivity.findViewById(org.francho.lab.gameoflife.R.id.world);
//		
//		// At least one live cell
//		TouchUtils.clickView(this, worldView1);
//		
//		String world1 = worldView1.getWorld().toString();
//		
//		// Finish (save instance)
//		mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//		
//		// TODO Why this world2 is the same as world1? Why the activity don't
//		worldView1.getWorld().getCell(1,1).setHealth(Health.ALIVE);
//		
//		// Start again
//		WorldActivity act2 = getActivity();
//		
//		WorldView wordView2 = (WorldView)act2.findViewById(org.francho.lab.gameoflife.R.id.world);
//		
//		String world2 = wordView2.getWorld().toString();
//		
//		System.out.println(world1);
//		System.out.println("-----------------------------");
//		System.out.println(world2);
//		
//		assertEquals(world1, world2);
//	}

	
	@Override
	public void tearDown() throws Exception {
		mActivity.finish();
		super.tearDown();
	}
}
