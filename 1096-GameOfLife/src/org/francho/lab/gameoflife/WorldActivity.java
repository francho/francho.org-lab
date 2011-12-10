package org.francho.lab.gameoflife;

import org.francho.lab.gameoflife.Cell.Health;
import org.francho.lab.gameoflife.WorldView.WorldListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class WorldActivity extends Activity implements OnCheckedChangeListener, WorldListener {

	private static final int WORLD_COLS = 20;
	private static final int WORLD_ROWS = 20;
	private static final String EXTRA_WORLD = "WorldActivity.EXTRA_WORLD";
	
	private WorldView mWorldView;
	
	private CountDownTimer countDownTimer;
	private ToggleButton mPlayButton;
	private TextView mTextGeneration;
	private TextView mTextLivecells;
	

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mPlayButton = (ToggleButton) findViewById(R.id.btn_onOff);
        mPlayButton.setOnCheckedChangeListener(this);
        
        mTextGeneration = (TextView) findViewById(R.id.text_generation);
        mTextLivecells = (TextView) findViewById(R.id.text_livecells);
        
        mWorldView = (WorldView) findViewById(R.id.world);
        mWorldView.setWorldListener(this);
        
        if(savedInstanceState != null && savedInstanceState.containsKey(EXTRA_WORLD)) {
        	World world = (World) savedInstanceState.getSerializable(EXTRA_WORLD);
        	mWorldView.setWorld(world);
        } else {
        	initWorld();
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if(item.getItemId() == R.id.menu_about) {
            showDialogAbout();
            return true;
        }
        
        return false;
    }

	/**
	 * 
	 */
	private void showDialogAbout() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.about_title)
			   .setMessage(R.string.about)
		       .setCancelable(false)
		       .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               dialog.dismiss();
		           }
		       }).show();
	}

	/**
	 * 
	 */
	private void initWorld() {
		mWorldView.setWorld(WORLD_ROWS,WORLD_COLS);
	}

	/* (non-Javadoc)
	 * @see android.widget.CompoundButton.OnCheckedChangeListener#onCheckedChanged(android.widget.CompoundButton, boolean)
	 */
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if(isChecked){
			startAutoGeneration();
		} else {
			stopAutoGeneration();
		}
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// outState.putSerializable(EXTRA_WORLD, mWorldView.getWorld());
		
		super.onSaveInstanceState(outState);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		super.onStart();
		if(mPlayButton.isChecked()) {
			startAutoGeneration();
		}
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		super.onStop();
		if(countDownTimer != null) {
			countDownTimer.cancel();
			countDownTimer=null;
		}
	}

	public void startAutoGeneration() {
		mWorldView.nextGeneration();
		countDownTimer = new CountDownTimer(60000,300) {
			@Override
			public void onTick(long millisUntilFinished) {
				mWorldView.nextGeneration();
			}
			
			@Override
			public void onFinish() {
				// Infinite loop (restart)
				startAutoGeneration();
			}
		};
		
		countDownTimer.start();
	}
	
	public void stopAutoGeneration() {
		if(countDownTimer != null) {
			countDownTimer.cancel();
		}
	}
	
	public void onClickButtonNext(View v) {
		mWorldView.nextGeneration();
	}
	
	public void onClickButtonClear(View v) {
		initWorld();
	}

	/* (non-Javadoc)
	 * @see org.francho.lab.gameoflife.WorldView.WorldListener#onInitWorld()
	 */
	@Override
	public void onInitWorld() {
		mTextGeneration.setText(getString(R.string.text_generation,0));
		mTextLivecells.setText(getString(R.string.text_livecells,0));
	}

	/* (non-Javadoc)
	 * @see org.francho.lab.gameoflife.WorldView.WorldListener#onNextGeneration(int, int)
	 */
	@Override
	public void onNextGeneration(int day, int liveCells) {
		mTextGeneration.setText(getString(R.string.text_generation,day));
		mTextLivecells.setText(getString(R.string.text_livecells,liveCells));
	}
}