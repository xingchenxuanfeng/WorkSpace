package com.example.forceoffline;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText password;
	private EditText account;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityCollector.AddActivity(this);

		setContentView(R.layout.activity_main);
		account = (EditText) findViewById(R.id.account);
		password = (EditText) findViewById(R.id.password);
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (account.getText().toString().equals("12345")
						&& password.getText().toString().equals("12345")) {
					Intent intent = new Intent(MainActivity.this,
							Activity1.class);
					startActivity(intent);
					finish();
				}
			}
		});
	}

}
