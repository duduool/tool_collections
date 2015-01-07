package com.wtu.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.wtu.activity.R;
import com.wtu.view.SwitchButton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

class ViewHolder {    
    public TextView 	titleText;  
    public SwitchButton switchButton;
}    
  
public class SettinglListAdapter extends BaseAdapter {    
	private Context context	= null;
    private List list 		= null;
    private Map map 		= new HashMap();
    
    public SettinglListAdapter(Context context, List list){  
        super();  
        this.context = context;
        this.list 	 = list;
    }  
  
    @Override  
    public int getCount() {  
        return list.size();  
    }  
  
    @Override  
    public Object getItem(int position) {  
        return position;  
    }  
  
    @Override  
    public long getItemId(int position) {  
        return position;  
    }  
  
    @Override  
    public View getView(final int position, View convertView, ViewGroup parent) {  
  
        ViewHolder viewHolder = null;    
        if (convertView == null) {    
        	convertView = LayoutInflater.from(context).inflate(R.layout.item_setting, null) ;
        	
        	viewHolder = new ViewHolder();    
        	viewHolder.titleText = (TextView) convertView.findViewById(R.id.titleText);    
        	viewHolder.switchButton = (SwitchButton) convertView.findViewById(R.id.switchButton); 
        	
            convertView.setTag(viewHolder);    
        } else {    
        	viewHolder = (ViewHolder) convertView.getTag();    
        }  
  
        viewHolder.titleText.setText((String)(((HashMap)list.get(position)).get("title")));  
  
        viewHolder.switchButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean flag) {
				
				if (flag) {
					map.put(position, "check") ;
				} else {
					map.remove(position) ;
				}
			}
		}) ;
		
		if (map.get(position) != null) {
			viewHolder.switchButton.setChecked(true) ;
		} else {
			viewHolder.switchButton.setChecked(false) ;
		}
        
        return convertView;  
    }    
}  