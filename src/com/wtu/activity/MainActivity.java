package com.wtu.activity;

import com.wtu.activity.R;
import com.wtu.fragment.HomeFragment;
import com.wtu.fragment.MapExFragment;
import com.wtu.fragment.SettingFragment;
import com.wtu.fragment.StarFragment;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private HomeFragment homeFragment;
	private MapExFragment mapExFragment;
	private StarFragment starFragment;
	private SettingFragment settingFragment;
	private View homeLayout;
	private View mapLayout;
	private View starLayout;
	private View settingLayout;
	private ImageView homeImage;
	private ImageView mapImage;
	private ImageView starImage;
	private ImageView settingImage;
	private TextView homeText;
	private TextView mapText;
	private TextView starText;
	private TextView settingText;
	private FragmentManager fragmentManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_activity);

		// 初始化布局元素
		initViews();
		fragmentManager = getFragmentManager();
		// 第一次启动时选中第0个tab
		setTabSelection(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem mi = menu.add(Menu.NONE, 1, Menu.NONE,
				R.string.action_settings);
		mi.setIcon(R.drawable.ic_launcher);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) // 得到被点击的item的itemId
		{
		case Menu.FIRST + 0: 	  // 对应的ID就是在add方法中所设定的Id
			this.finish();
			System.exit(0);
			break;
		}
		return true;
	}

	/**
	 * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
	 */
	private void initViews() {
		homeLayout 		= findViewById(R.id.home_layout);
		mapLayout 		= findViewById(R.id.map_layout);
		starLayout 		= findViewById(R.id.star_layout);
		settingLayout 	= findViewById(R.id.setting_layout);
		homeImage 		= (ImageView) findViewById(R.id.home_image);
		mapImage 		= (ImageView) findViewById(R.id.map_image);
		starImage 		= (ImageView) findViewById(R.id.star_image);
		settingImage 	= (ImageView) findViewById(R.id.setting_image);
		homeText 		= (TextView) findViewById(R.id.home_text);
		mapText 		= (TextView) findViewById(R.id.map_text);
		starText 		= (TextView) findViewById(R.id.star_text);
		settingText 	= (TextView) findViewById(R.id.setting_text);
		homeLayout.setOnClickListener(this);
		mapLayout.setOnClickListener(this);
		starLayout.setOnClickListener(this);
		settingLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home_layout:
			setTabSelection(0);
			break;
		case R.id.map_layout:
			setTabSelection(1);
			break;
		case R.id.star_layout:
			setTabSelection(2);
			break;
		case R.id.setting_layout:
			setTabSelection(3);
			break;
		default:
			break;
		}
	}

	/**
	 * 根据传入的index参数来设置选中的tab页。
	 * 
	 * @param index
	 *            每个tab页对应的下标。0表示Home，1表示Map，2表示Star，3表示Setting
	 */
	private void setTabSelection(int index) {
		// 每次选中之前先清楚掉上次的选中状态
		clearSelection();
		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		switch (index) {
		case 0:
			homeImage.setImageResource(R.drawable.tab_home_pressed);
			homeText.setTextColor(Color.rgb(0, 121, 255)); // RGB(0, 121, 255)
			if (homeFragment == null) {
				homeFragment = new HomeFragment();
				transaction.add(R.id.content, homeFragment);
			} else {
				transaction.show(homeFragment);
			}
			break;
		case 1:
			mapImage.setImageResource(R.drawable.tab_map_pressed);
			mapText.setTextColor(Color.rgb(0, 121, 255));
			if (mapExFragment == null) {
				mapExFragment = new MapExFragment();
				transaction.add(R.id.content, mapExFragment);
			} else {
				transaction.show(mapExFragment);
			}
			break;
		case 2:
			starImage.setImageResource(R.drawable.tab_star_pressed);
			starText.setTextColor(Color.rgb(0, 121, 255));
			if (starFragment == null) {
				starFragment = new StarFragment();
				transaction.add(R.id.content, starFragment);
			} else {
				transaction.show(starFragment);
			}
			break;
		case 3:
		default:
			settingImage.setImageResource(R.drawable.tab_settings_pressed);
			settingText.setTextColor(Color.rgb(0, 121, 255));
			if (settingFragment == null) {
				settingFragment = new SettingFragment();
				transaction.add(R.id.content, settingFragment);
			} else {
				transaction.show(settingFragment);
			}
			break;
		}
		transaction.commit();
	}

	/**
	 * 清除掉所有的选中状态。
	 */
	private void clearSelection() {
		homeImage.setImageResource(R.drawable.tab_home_normal);
		homeText.setTextColor(Color.parseColor("#82858b"));
		mapImage.setImageResource(R.drawable.tab_map_normal);
		mapText.setTextColor(Color.parseColor("#82858b"));
		starImage.setImageResource(R.drawable.tab_star_normal);
		starText.setTextColor(Color.parseColor("#82858b"));
		settingImage.setImageResource(R.drawable.tab_settings_normal);
		settingText.setTextColor(Color.parseColor("#82858b"));
	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (homeFragment != null) {
			transaction.hide(homeFragment);
		}
		if (mapExFragment != null) {
			transaction.hide(mapExFragment);
		}
		if (starFragment != null) {
			transaction.hide(starFragment);
		}
		if (settingFragment != null) {
			transaction.hide(settingFragment);
		}
	}
	
	// 有待改进
	@Override
	protected void onSaveInstanceState(Bundle outState) {
	}
}