/**
 * Intent samples
 * http://francho.org
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.francho.test.intents;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class IntentsActivity extends Activity {
    private static final int REQUEST_PHOTO = 1;
    
	private HashMap<String, Intent> mPruebas;
	private LinearLayout mContainer;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mContainer = new LinearLayout(this);
        mContainer.setOrientation(LinearLayout.VERTICAL);
        mContainer.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
        setContentView(mContainer);
        
 
        
        Intent intent;
        
        // Web
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://francho.org/"));
        createButton("Web", intent);
        
        // Web filtrada
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/"));
        createButton("Google maps (web)", intent);

        // Geo (maps)
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:41.656313,-0.877351"));
        createButton("Google maps geopunto", intent);

        // Llamadas
        intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:976123123"));
        createButton("Llamada telef—nica", intent);
        
        // Enviar texto (sencillo)
        intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");  
        intent.putExtra(Intent.EXTRA_SUBJECT, "asunto de prueba");
        intent.putExtra(Intent.EXTRA_TEXT, "probando el envio");
        createButton("compartir (selector)", intent.createChooser(intent, "enviar"));
        
        // enviar un mail
        intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");  
        intent.putExtra(Intent.EXTRA_SUBJECT, "asunto de prueba");
        intent.putExtra(Intent.EXTRA_TEXT, "probando el envio");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"sheldon@cooper.com"});
        createButton("Mail To", intent);
        
        // tomar una foto
        intent = new Intent("android.media.action.IMAGE_CAPTURE");
        createButton("Tomar foto", intent, REQUEST_PHOTO);
    }
    
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == Activity.RESULT_OK) {
			Bundle extras;
			switch(requestCode) {
			case REQUEST_PHOTO:
				extras = data.getExtras();
				String result = data.toURI();
				Toast.makeText(IntentsActivity.this, result, Toast.LENGTH_LONG).show();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}


	protected void createButton(String label, Intent intent) {
    	createButton(label, intent, null);
    }
    
    protected void createButton(String label, Intent intent, Integer requestCode) {
    	 Button btn = new Button(this);
    	 btn.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
    	 btn.setText(label);
    	 btn.setOnClickListener(new BtnOnClickListener(intent, requestCode));
    	 
    	 mContainer.addView(btn); 
    }
    
    class BtnOnClickListener implements OnClickListener  {
    	
    	private Intent mIntent;
		private Integer mRequestCode;

		BtnOnClickListener(Intent intent, Integer requestCode) {
    		mIntent = intent;
    		mRequestCode = requestCode;
    	}
    	
		public void onClick(View v) {
			try {
				if( mRequestCode != null) {
					startActivityForResult(mIntent, mRequestCode);
				} else {
					startActivity(mIntent);
				} 
			} catch(Exception e) {
				Toast.makeText(IntentsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
		}
    	
    }
    
}