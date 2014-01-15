package com.mybmi;

import java.text.DecimalFormat;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button mSubmit1, mSubmit2;
	private EditText mHight, mWidth;
	private TextView mOutView;
	private String outTg;
	//ActionBar actionBar;
	/*protected static final int MENU_ABOUT = Menu.FIRST;
	protected static final int MENU_Quit = Menu.FIRST+1;*/
	private static final int MU_SAVE = Menu.FIRST;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mHight = (EditText) findViewById(R.id.inH);
		mWidth = (EditText) findViewById(R.id.inW);
		mSubmit1 = (Button) findViewById(R.id.btSubmit1);
		mSubmit2 = (Button) findViewById(R.id.btSubmit2);
		mOutView = (TextView) findViewById(R.id.outView);
		mSubmit1.setOnClickListener(ck1);
		mSubmit2.setOnClickListener(ck2);
        /*actionBar=getActionBar();
        actionBar.show(); */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		MenuItem save=menu.add(0,MU_SAVE,0,getString(R.string.tgMSave)); 
		save.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM); 
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		super.onOptionsItemSelected(item);
		switch(item.getItemId()){
			case R.id.tgMAbout:
				ckAbout();
				break;
			case R.id.tgMOut:
				finish();
				break;

		}
		return true;
	};
	public View.OnClickListener ck1 = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO 自動產生的方法 Stub
			if(mHight.getText().length() != 0 && mWidth.getText().length() != 0){
				DecimalFormat nf = new DecimalFormat("0.0");
	            double height = Double.parseDouble(mHight.getText().toString());
	            if(height > 10){
	            	height = height / 100;
	            	mHight.setText(nf.format(height));
	            }
	            double weight = Double.parseDouble(mWidth.getText().toString());
	            double BMI = weight / (height * height);
	            
	            if(BMI > 24)
	            	outTg = getString(R.string.tgHight);
	            else if(BMI <= 18.5)
	            	outTg = getString(R.string.tgDown);
	            else
	            	outTg = getString(R.string.tgCi);
	            mOutView.setText(nf.format(BMI) + "\n" + outTg);
			}else
				mOutView.setText(getString(R.string.tgNoinset));
		}
	};
	
	public View.OnClickListener ck2 = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(mHight.getText().length() != 0 && mWidth.getText().length() != 0){
				// TODO 自動產生的方法 Stub
				DecimalFormat nf = new DecimalFormat("0.00");
	            double height = Double.parseDouble(mHight.getText().toString());
	            if(height > 10){
	            	height = height / 100;
	            	mHight.setText(nf.format(height));
	            }
	            double weight = Double.parseDouble(mWidth.getText().toString());
	            double BMI = (1.3 * weight) / Math.sqrt((height * height * height * height * height));
	            
	            if(BMI < 15)
	            	outTg = getString(R.string.tgStarvation);
	            else if(BMI >= 15 && BMI < 18.5)
	            	outTg = getString(R.string.tgUnderweight);
	            else if(BMI >= 18.5 && BMI < 24)
	            	outTg = getString(R.string.tgNormal);
	            else if(BMI >= 25 && BMI < 30)
	            	outTg = getString(R.string.tgOverweight);
	            else if(BMI >= 30 && BMI < 40)
	            	outTg = getString(R.string.tgObese);
	            else if(BMI >= 30 && BMI < 40)
	            	outTg = getString(R.string.tgObese);
	            else if(BMI <= 40)
	            	outTg = getString(R.string.tgMorbidlyObese);
	            else
	            	outTg = getString(R.string.tgGood);
	
	            mOutView.setText(nf.format(BMI) + "\n" + outTg);
			}else
				mOutView.setText(getString(R.string.tgNoinset));
		}
	};
	public void ckAbout(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle(R.string.about_title);
        dialog.setMessage(R.string.about_msg);
        dialog.setPositiveButton(getString(R.string.about_ok),
            new DialogInterface.OnClickListener(){
                public void onClick(
                        DialogInterface dialoginterface, int i){
                        }
                });
        dialog.setNegativeButton(getString(R.string.about_burl),
        		new DialogInterface.OnClickListener(){
        			public void onClick(
        					DialogInterface dialoginterface, int i){
        						//go to url
        						Uri uri = Uri.parse(getString(R.string.about_url));
        						Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        						startActivity(intent);
        					}
        });
        dialog.show();
	}
}
