package de.derandroidpro.taschenlampetutorial;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	




	Switch s1;
	
	Camera cam;
	Parameters param;
	boolean lichtan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		s1 = (Switch) findViewById(R.id.switch1);
		s1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if (isChecked == true){
					
					if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
						
						if (cam == null){
							
							cam = Camera.open();
						}
						
						
						param = cam.getParameters();
						param.setFlashMode(Parameters.FLASH_MODE_TORCH);
						cam.setParameters(param);
						cam.startPreview();
						
						lichtan = true;
						
					} else {
						
						Toast.makeText(getApplicationContext(), "keine Foto-LED", Toast.LENGTH_SHORT).show();
					}
					
					
					
					
				}
				
				if ( isChecked == false){
					
					
					lichtaus();
					
				}
				
				
				
				
				
			}
		});
		
		
	}

	
	
	public void lichtaus(){
		
		if (lichtan == false){
			
			cam = Camera.open();
		}
		
		
		param = cam.getParameters();
		param.setFlashMode(Parameters.FLASH_MODE_OFF);
		cam.setParameters(param);
		cam.stopPreview();
		
		lichtan = false;
		
		
		
	}
	
	
	
	@Override
	protected void onPause() {
		
		if (lichtan == true){
		lichtaus();
		
		cam.release();
		
		}
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		s1.setChecked(false);
		super.onResume();
	}

}
