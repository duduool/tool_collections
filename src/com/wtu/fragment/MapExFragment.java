package com.wtu.fragment;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.SupportMapFragment;
import com.wtu.activity.R;
import com.wtu.residemenu.ResideMenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapExFragment extends SupportMapFragment {
	private static MapExFragment fragment = null;
	public static final int POSITION = 0;

	private MapView mapView;
	private AMap aMap;
	private View mapLayout;
	private ResideMenu resideMenu;
	
	public static SupportMapFragment newInstance() {
		if (fragment == null) {
			synchronized (MapExFragment.class) {
				if (fragment == null) {
					fragment = new MapExFragment();
				}
			}
		}
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if (mapLayout == null) {
			mapLayout = inflater.inflate(R.layout.layout_map, null);
			mapView = (MapView) mapLayout.findViewById(R.id.mapView);
			mapView.onCreate(savedInstanceState);
			if (aMap == null) {
				aMap = mapView.getMap();
			}
		} else {
			if (mapLayout.getParent() != null) {
				((ViewGroup) mapLayout.getParent()).removeView(mapLayout);
			}
		}
		
		return mapLayout;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		mapView.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mapView.onPause();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}
}