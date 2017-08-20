package com.example.imageview_basic;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity {

   
    CheckBox chxs,chgame,chmovie;
    RatingBar rat;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chxs=(CheckBox)this.findViewById(R.id.checkBox1);
        chgame=(CheckBox)this.findViewById(R.id.checkBox2);
        chmovie=(CheckBox)this.findViewById(R.id.checkBox3);
        rat=(RatingBar)this.findViewById(R.id.ratingBar1);
        chxs.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				switch(arg0.getId()){
				case R.id.checkBox1:
					if(arg1){
						Toast.makeText(MainActivity.this, "点击了小说", 0).show();
					}
					break;
				}
				
			}
		});
        rat.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
				if(arg2){
					Toast.makeText(MainActivity.this, "几颗星"+arg1, 0).show();
				}
				
			}
		});
    }


    
}
