package com.wtu.adapter;

import java.util.HashMap;
import java.util.List;
import com.wtu.activity.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
  
class StarViewHolder {    
    public TextView 	titleText;  
}   

public class StarListAdapter extends SimpleAdapter {    
	private Context context	= null;
    private List list 		= null;
    
    public StarListAdapter(Context context, List list){  
        super(context, list, 0, null, null); 
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
  
    	StarViewHolder viewHolder = null;    
        if (convertView == null) {    
        	convertView = LayoutInflater.from(context).inflate(R.layout.item_star, null) ;
        	
        	viewHolder = new StarViewHolder();    
        	viewHolder.titleText = (TextView) convertView.findViewById(R.id.star_text);    
        	
            convertView.setTag(viewHolder);    
        } else {    
        	viewHolder = (StarViewHolder) convertView.getTag();    
        }  
  
        viewHolder.titleText.setText((String)(((HashMap)list.get(position)).get("title")));  
  
        return convertView;  
    }     
}  