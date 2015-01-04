package com.wtu.activity;

import java.util.ArrayList;
import java.util.List;

import com.wtu.activity.R;
import com.wtu.fragment.SettingFragment;
import com.wtu.fragment.HomeFragment;
import com.wtu.fragment.MapExFragment;
import com.wtu.fragment.StarFragment;
import android.os.Bundle;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
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
	private TextView headText;
	private TextView hintText;
	private ViewPager viewPager;
	private List<Fragment> fragments;
	private FragmentManager fragmentManager;
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	/** 页卡总数 **/
	private static final int pageSize = 4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_activity);

		// 初始化布局元素
		initViews();
		InitViewPager();
		
		Animation ani = new AlphaAnimation(0f,1f);
		ani.setDuration(1500);
		ani.setRepeatMode(Animation.REVERSE);
		ani.setRepeatCount(Animation.INFINITE);
		hintText.startAnimation(ani);
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
		headText 		= (TextView) findViewById(R.id.head_text);
		hintText		= (TextView) findViewById(R.id.hint_text);
		homeLayout.setOnClickListener(new MyOnClickListener(0));
		mapLayout.setOnClickListener(new MyOnClickListener(1));
		starLayout.setOnClickListener(new MyOnClickListener(2));
		settingLayout.setOnClickListener(new MyOnClickListener(3));
	}

	private void InitViewPager() {
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		fragments = new ArrayList<Fragment>();
		fragments.add(new HomeFragment());
		fragments.add(new MapExFragment());
		fragments.add(new StarFragment());
		fragments.add(new SettingFragment());
		viewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager(), fragments));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}
	
	private void setTabSelection(int index) {
		clearSelection();
		switch (index) {
		case 0:
			homeImage.setImageResource(R.drawable.tab_home_pressed);
			homeText.setTextColor(Color.rgb(0, 121, 255));
			headText.setText(R.string.home);
			break;
		case 1:
			mapImage.setImageResource(R.drawable.tab_map_pressed);
			mapText.setTextColor(Color.rgb(0, 121, 255));
			headText.setText(R.string.map);
			break;
		case 2:
			starImage.setImageResource(R.drawable.tab_star_pressed);
			starText.setTextColor(Color.rgb(0, 121, 255));
			headText.setText(R.string.star);
			break;
		case 3:
		default:
			settingImage.setImageResource(R.drawable.tab_settings_pressed);
			settingText.setTextColor(Color.rgb(0, 121, 255));
			headText.setText(R.string.setting);
			break;
		}
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

	// 有待改进
	@Override
	protected void onSaveInstanceState(Bundle outState) {
	}
	
	/**
	 * 图标点击监听
	 */
	private class MyOnClickListener implements OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		public void onClick(View v) {
			switch (index) {
			case 0:
				setTabSelection(0);
				break;
			case 1:
				setTabSelection(1);
				break;
			case 2:
				setTabSelection(2);
				break;
			case 3:
				setTabSelection(3);
				break;
			default:
				break;
			}
			viewPager.setCurrentItem(index);
		}
	}
	
	/**
	 * 为选项卡绑定监听器
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量

		public void onPageScrollStateChanged(int index) {
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		public void onPageSelected(int index) {
			switch (index) {
			case 0:
				setTabSelection(0);
				break;
			case 1:
				setTabSelection(1);
				break;
			case 2:
				setTabSelection(2);
				break;
			case 3:
				setTabSelection(3);
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * 定义适配器
	 */
	class myPagerAdapter extends FragmentPagerAdapter {
		private List<Fragment> fragmentList;

		public myPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
			super(fm);
			this.fragmentList = fragmentList;
		}

		/**
		 * 得到每个页面
		 */
		@Override
		public Fragment getItem(int arg0) {
			return (fragmentList == null || fragmentList.size() == 0) ? null
					: fragmentList.get(arg0);
		}

		/**
		 * 每个页面的title
		 */
		@Override
		public CharSequence getPageTitle(int position) {
			return null;
		}

		/**
		 * 页面的总个数
		 */
		@Override
		public int getCount() {
			return fragmentList == null ? 0 : fragmentList.size();
		}
	}
}