package com.wtu.activity;

import java.util.ArrayList;
import java.util.List;
import com.wtu.activity.R;
import com.wtu.fragment.SettingFragment;
import com.wtu.fragment.HomeFragment;
import com.wtu.fragment.MapExFragment;
import com.wtu.fragment.StarFragment;
import com.wtu.jazzyviewpager.JazzyViewPager;
import com.wtu.jazzyviewpager.ParallaxPagerTransformer;
import com.wtu.jazzyviewpager.JazzyViewPager.TransitionEffect;
import com.wtu.residemenu.ResideMenu;
import com.wtu.residemenu.ResideMenuItem;
import com.wtu.slidingactivity.SlidingFragmentActivity;
import com.wtu.slidingmenu.SlidingMenu;
import com.wtu.slidingmenu.SlidingMenu.CanvasTransformer;
import com.wtu.swipeactivity.SwipeBackActivityBase;
import com.wtu.swipeactivity.SwipeBackActivityHelper;
import com.wtu.swipeback.SwipeBackLayout;
import com.wtu.swipeback.Utils;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends SlidingFragmentActivity implements
		SwipeBackActivityBase {
	private static final int VIBRATE_DURATION = 20;
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
	private JazzyViewPager jazzyViewPager;
	private List<Fragment> fragments;
	private FragmentManager fragmentManager;
	private CanvasTransformer transformer;
	private SlidingMenu slidingMenu;
	private SwipeBackActivityHelper mHelper;
	private ResideMenu resideMenu;
	private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemCalendar;
    private ResideMenuItem itemSettings;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		mHelper = new SwipeBackActivityHelper(this);
		mHelper.onActivityCreate();
		
		// 初始化界面上的View主界面及菜单
		this.initViews();
		this.initAnimation();
		
		this.setupResideMenu();
		this.setupSlidingMenu();
		this.setupJazziness(TransitionEffect.Tablet);
	}

	private static Interpolator interp = new Interpolator() {
		@Override
		public float getInterpolation(float t) {
			t -= 1.0f;
			return t * t * t + 1.0f;
		}
	};

	// 初始化菜单动画效果
	private void initAnimation() {
		// 上滑字体动画效果
		Animation ani = new AlphaAnimation(0f, 1f);
		ani.setDuration(1500);
		ani.setRepeatMode(Animation.REVERSE);
		ani.setRepeatCount(Animation.INFINITE);
		hintText.startAnimation(ani);
		
		transformer = new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				float scale = (float) (percentOpen * 0.25 + 0.75);
				canvas.scale(scale, scale, canvas.getWidth()/2,
						canvas.getHeight()/2);
				// canvas.scale(percentOpen, 1, 0, 0);
				// canvas.translate(
				//		0,
				//		canvas.getHeight()
				//				* (1 - interp.getInterpolation(percentOpen)));
			}
		};
	}

	// 获取控件的实例，并设置点击事件
	private void initViews() {
		homeLayout 	= findViewById(R.id.home_layout);
		mapLayout 	= findViewById(R.id.map_layout);
		starLayout 	= findViewById(R.id.star_layout);
		settingLayout = findViewById(R.id.setting_layout);
		homeImage 	= (ImageView) findViewById(R.id.home_image);
		mapImage 	= (ImageView) findViewById(R.id.map_image);
		starImage 	= (ImageView) findViewById(R.id.star_image);
		settingImage = (ImageView) findViewById(R.id.setting_image);
		homeText 	= (TextView) findViewById(R.id.home_text);
		mapText 	= (TextView) findViewById(R.id.map_text);
		starText 	= (TextView) findViewById(R.id.star_text);
		settingText = (TextView) findViewById(R.id.setting_text);
		headText 	= (TextView) findViewById(R.id.head_text);
		hintText 	= (TextView) findViewById(R.id.hint_text);
		
		homeLayout.setOnClickListener(new MyOnClickListener(0));
		mapLayout.setOnClickListener(new MyOnClickListener(1));
		starLayout.setOnClickListener(new MyOnClickListener(2));
		settingLayout.setOnClickListener(new MyOnClickListener(3));
	}

	// 设置滑动菜单的属性值
	private void setupSlidingMenu() {
		// 设置主界面视图 ...
		// 设置滑动菜单视图
		setBehindContentView(R.layout.layout_menu);
		
		slidingMenu = getSlidingMenu();
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setShadowDrawable(R.drawable.sliding_shadow);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.setBehindScrollScale(0.0f);
		slidingMenu.setBehindCanvasTransformer(transformer);
	}

	// 设置jazzyviewpager的属性
	private void setupJazziness(TransitionEffect effect) {
		fragments = new ArrayList<Fragment>();
		fragments.add(new HomeFragment());
		fragments.add(new MapExFragment());
		fragments.add(new StarFragment());
		fragments.add(new SettingFragment());
		jazzyViewPager = (JazzyViewPager) findViewById(R.id.jazzy_pager);
		jazzyViewPager.setOffscreenPageLimit(1);
		jazzyViewPager.setTransitionEffect(effect);	// 设置页面之间的切换效果
		jazzyViewPager.setPageTransformer(false, new ParallaxPagerTransformer(
				R.id.parallaxContent));	// 设置页面上的切换效果
		jazzyViewPager.setAdapter(new MyPagerAdapter(
				getSupportFragmentManager(), fragments));
		jazzyViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		jazzyViewPager.setPageMargin(30);

		this.setTabSelection(0);
		jazzyViewPager.setCurrentItem(0);
	}

	// 设置 residemenu
	private void setupResideMenu() {
        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
        resideMenu.setScaleValue(0.7f);

        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.icon_home,     "Home");
        itemProfile  = new ResideMenuItem(this, R.drawable.icon_profile,  "Profile");
        itemCalendar = new ResideMenuItem(this, R.drawable.icon_calendar, "Calendar");
        itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, "Settings");

        itemHome.setOnClickListener    (new ResideMenuOnClickListener());
        itemProfile.setOnClickListener (new ResideMenuOnClickListener());
        itemCalendar.setOnClickListener(new ResideMenuOnClickListener());
        itemSettings.setOnClickListener(new ResideMenuOnClickListener());

        resideMenu.addMenuItem(itemHome, 	 ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemProfile,  ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_RIGHT);

        // You can disable a direction by setting ->
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);
        //resideMenu.addIgnoredView((LinearLayout) findViewById(R.id.ignored_view));
        
        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	slidingMenu.showMenu();
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }
	
	// 点击底部图标时的动作
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

	// 清除掉所有的选中状态
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
	
	// ResideMenuListener
	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            // Toast.makeText(MainActivity.this, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            // Toast.makeText(MainActivity.this, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };
	
    private class ResideMenuOnClickListener implements OnClickListener {
		
		@Override
		public void onClick(View view) {
			if (view == itemHome){
				Intent intent = new Intent(MainActivity.this, DemoActivity.class);
				startActivity(intent);
	        }else if (view == itemProfile){
	        }else if (view == itemCalendar){
	        }else if (view == itemSettings){
	        }

	        resideMenu.closeMenu();
		}
	}
    
	// 点击底部图片切换fragment
	private class MyOnClickListener implements OnClickListener {
		private int index = 0;
		
		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View view) {
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
			jazzyViewPager.setCurrentItem(index);
		}
	}

	// 当fragment滑动时，底部的图片要跟着变化
	private class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int index) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int index) {
			switch (index) {
			case 0:
				setTabSelection(0);
				// 防止viewpager 和 slidingmenu 滑动冲突
				slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
				break;
			case 1:
				setTabSelection(1);
				slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
				break;
			case 2:
				setTabSelection(2);
				slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
				break;
			case 3:
				setTabSelection(3);
				slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
				break;
			default:
				break;
			}
			jazzyViewPager.setCurrentItem(index);
		}
	}

	// 每个fragment显示时，所需调用的适配器
	private class MyPagerAdapter extends FragmentPagerAdapter {
		private List<Fragment> fragmentList;

		public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
			super(fm);
			this.fragmentList = fragmentList;
		}

		// 产生动画效果的关键
		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			Object obj = super.instantiateItem(container, position);
			jazzyViewPager.setObjectForPosition(obj, position);
			return obj;
		}

		@Override
		public Fragment getItem(int index) {
			return (fragmentList == null || fragmentList.size() == 0) ? null
					: fragmentList.get(index);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return null;
		}

		@Override
		public int getCount() {
			return fragmentList == null ? 0 : fragmentList.size();
		}
	}

	// 有待改进
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//super.onSaveInstanceState(outState);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem mi = menu.add(Menu.NONE, 1, Menu.NONE,
				R.string.action_settings);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) // 得到被点击的item的itemId
		{
		case Menu.FIRST + 0: // 对应的ID就是在add方法中所设定的Id
			this.finish();
			System.exit(0);
			break;
		}
		return true;
	}
	
	// 滑动返回事件重写的方法
	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mHelper.onPostCreate();
	}

	@Override
	public View findViewById(int id) {
		View v = super.findViewById(id);
		if (v == null && mHelper != null)
			return mHelper.findViewById(id);
		return v;
	}

	@Override
	public SwipeBackLayout getSwipeBackLayout() {
		return mHelper.getSwipeBackLayout();
	}

	@Override
	public void setSwipeBackEnable(boolean enable) {
		getSwipeBackLayout().setEnableGesture(enable);
	}

	@Override
	public void scrollToFinishActivity() {
		Utils.convertActivityToTranslucent(this);
		getSwipeBackLayout().scrollToFinishActivity();
	}
	
	// What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
}