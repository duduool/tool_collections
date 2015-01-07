package com.wtu.fragment;

import com.wtu.activity.DemoActivity;
import com.wtu.activity.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {
	private View homeLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		homeLayout = inflater.inflate(R.layout.layout_home,
				container, false);
		Button button = (Button) homeLayout.findViewById(R.id.jump);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getActivity(), DemoActivity.class);
				getActivity().startActivity(intent);
			}
		});
		
		return homeLayout;
	}
}
