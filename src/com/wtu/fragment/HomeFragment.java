package com.wtu.fragment;

import com.wtu.activity.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HomeFragment extends Fragment {
	private ImageView backImage;
	private View homeLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		homeLayout = inflater.inflate(R.layout.home_layout,
				container, false);
		return homeLayout;
	}
}
