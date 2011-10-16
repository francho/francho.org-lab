/**
 * By Francho Joven - http://francho.org/lab/
 *
 * This work is licensed under the Creative Commons Attribution-Noncommercial-Share Alike 3.0 Unported License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ 
 * or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 *
 */
package org.francho.lab.customviews;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Sample compound view to show school marks:
 * 
 * - The color of the mark depends of the qualification.
 * - The mark is shows as text too
 * 
 * 
 * @author francho
 *
 */
public class SubjectMarkView extends RelativeLayout {

	private TextView mStudent;
	private TextView mSubject;
	private TextView mMarkNumber;
	private TextView mMarkText;

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public SubjectMarkView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public SubjectMarkView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	/**
	 * @param context
	 */
	public SubjectMarkView(Context context) {
		super(context);
		initView(context);
	}

	/**
	 * Configure our custom view
	 * 
	 * @param context
	 */
	private void initView(Context context) {
		final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
		inflater.inflate(R.layout.subjectmarkview, this, true);
		
		mStudent = (TextView) findViewById(R.id.sm_student);
		mSubject = (TextView) findViewById(R.id.sm_subject);
		mMarkNumber = (TextView) findViewById(R.id.sm_marknumber);
		mMarkText = (TextView) findViewById(R.id.sm_marktext);
	}

	/**
	 * Set the student name
	 * 
	 * @param student
	 */
	public void setStudent(CharSequence student) {
		mStudent.setText(student);
	}
	
	/**
	 * Set the subject name
	 * 
	 * @param subject
	 */
	public void setSubject(CharSequence subject) {
		mSubject.setText(subject);
	}
	
	/**
	 * Set the mark
	 * 
	 * @param mark
	 */
	public void setMark(double mark) {
		mMarkNumber.setText(""+mark);
	
		int markTextId=R.string.mark_unknown;
		
		if(mark >= 9){
			markTextId = R.string.mark_a;
		} else if(mark >= 7) {
			markTextId = R.string.mark_b;
		} else if(mark >= 6) {
			markTextId = R.string.mark_c;
		} else if(mark >= 5) {
			markTextId = R.string.mark_d;
		} else if(mark >= 0) {
			markTextId = R.string.mark_e;
		}
		
		mMarkText.setText(markTextId);
		
		int color= (mark>=5) ? Color.GREEN : Color.RED;
		
		mMarkText.setTextColor(color);
		mMarkNumber.setTextColor(color);
		
	}
 }
