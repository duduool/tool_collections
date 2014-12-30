package com.wtu.fragment;

import com.wtu.activity.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View homeLayout = inflater.inflate(R.layout.home_layout,
				container, false);
		return homeLayout;
	}
}
