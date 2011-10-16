/**
 * By Francho Joven - http://francho.org/lab/
 *
 * This work is licensed under the Creative Commons Attribution-Noncommercial-Share Alike 3.0 Unported License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ 
 * or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 *
 */
package org.francho.lab.customviews;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        SubjectMarkView mark = (SubjectMarkView) findViewById(R.id.mark);
        
        mark.setStudent("Fido Dido");
        mark.setSubject("Biology");
        mark.setMark(4.5);
    }
}