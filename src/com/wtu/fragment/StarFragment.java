package com.wtu.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.wtu.activity.R;
import com.wtu.adapter.StarListAdapter;
import com.wtu.pulltozommlistview.DensityUtil;
import com.wtu.pulltozommlistview.PullToZoomListView;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

public class StarFragment extends ListFragment {
	private PullToZoomListView mListView;
	private StarListAdapter mAdapter;
	private View starLayout;
	
	public void onCreate(Bundle savedInstanceState) {   
        super.onCreate(savedInstanceState);  
    	
    	List listItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 20; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("title", "Level" + i);
			listItem.add(map);
		}
		
		mAdapter = new StarListAdapter(this.getActivity(), listItem);
		setListAdapter(mAdapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		starLayout =  inflater.inflate(R.layout.layout_star, container, false);
		initListView();
		return starLayout;
	}

	private void initListView() {

		mListView = (PullToZoomListView) starLayout.findViewById(android.R.id.list); 
		
		mListView.getHeaderView().setScaleType(ImageView.ScaleType.CENTER_CROP);
		mListView.getHeaderView().setImageResource(R.drawable.story_member_center_bg);
		mListView.setmHeaderHeight(DensityUtil.dip2px(getActivity(), 260));

		View mHeaderView = getActivity().getLayoutInflater().inflate(R.layout.layout_star_userinfo, null);
		mListView.getHeaderContainer().addView(mHeaderView);
		mListView.setHeaderView();
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	}
}
