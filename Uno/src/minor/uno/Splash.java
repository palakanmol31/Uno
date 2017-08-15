package minor.uno;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		Button button = (Button) findViewById(R.id.button_id);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				final Intent i = new Intent(Splash.this, MainActivity.class);
						startActivity(i);
			}
		});
	}
}