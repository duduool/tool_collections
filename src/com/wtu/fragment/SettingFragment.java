package com.wtu.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.wtu.activity.R;
import com.wtu.adapter.SettinglListAdapter;
import com.wtu.jazzylistview.JazzyHelper;
import com.wtu.jazzylistview.JazzyListView;

public class SettingFragment extends ListFragment {
	private static final String KEY_TRANSITION_EFFECT = "transition_effect";

	private SettinglListAdapter adapter = null;
	private JazzyListView jazzylist;
	private View converView = null;
	private int mCurrentTransitionEffect = JazzyHelper.CARDS;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		List listItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 20; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("title", "Level" + i);
			listItem.add(map);
		}

		adapter = new SettinglListAdapter(this.getActivity(), listItem);
		setListAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		converView = LayoutInflater.from(getActivity()).inflate(
				R.layout.setting_layout, null);
		jazzylist = (JazzyListView) converView.findViewById(android.R.id.list);
		if (savedInstanceState != null) {
			mCurrentTransitionEffect = savedInstanceState.getInt(
					KEY_TRANSITION_EFFECT, JazzyHelper.CARDS);
			setupJazziness(mCurrentTransitionEffect);
		}
		
		return converView;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		System.out.println("Click On List Item!!!");
		super.onListItemClick(l, v, position, id);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_TRANSITION_EFFECT, mCurrentTransitionEffect);
	}

	private void setupJazziness(int effect) {
		mCurrentTransitionEffect = effect;
		jazzylist.setTransitionEffect(mCurrentTransitionEffect);
	}
}