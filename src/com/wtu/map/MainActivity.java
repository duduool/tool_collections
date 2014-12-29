package com.wtu.map;

import com.wtu.map.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_activity);

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent1 = new Intent(MainActivity.this, BasicMapActivity.class);
				startActivity(intent1);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem mi = menu.add(Menu.NONE, 1, Menu.NONE, R.string.action_settings);
		mi.setIcon(R.drawable.ic_launcher);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId())// 得到被点击的item的itemId
		{
		case Menu.FIRST + 0: // 对应的ID就是在add方法中所设定的Id
			this.finish();
			System.exit(0);
			break;
		}
		return true;
	}

}
