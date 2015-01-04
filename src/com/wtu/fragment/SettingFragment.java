package com.wtu.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ListView;
import com.wtu.activity.R;
import com.wtu.adapter.SettinglListAdapter;
import com.wtu.utils.SwitchAnimationUtil.AnimationType;

public class SettingFragment extends ListFragment {
	private SettinglListAdapter adapter = null;  
	private View converView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		List listItem = new ArrayList<HashMap<String, Object>>() ;
		for (int i = 0; i < 5; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>() ;
			map.put("image", R.drawable.car) ;
			map.put("title", "Level" + i) ;
			listItem.add(map) ;
		}
		adapter = new SettinglListAdapter(this.getActivity(), listItem) ;
        setListAdapter(adapter);    
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		converView = LayoutInflater.from(getActivity()).inflate(
				R.layout.setting_layout, null);
		converView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						new com.wtu.utils.SwitchAnimationUtil().startAnimation(converView,
								AnimationType.ALPHA);
					}
				});

		return converView;
	}
	
	@Override  
    public void onListItemClick(ListView l, View v, int position, long id) {  
        System.out.println("Click On List Item!!!");  
        super.onListItemClick(l, v, position, id);  
    }  
}