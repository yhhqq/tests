package com.example.administrator.hhh;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;
import android.view.LayoutInflater;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/7/3/003.
 */

public class ListAdapter extends BaseAdapter {
    private List<rjb> r1=new ArrayList<>();
    private LayoutInflater mInflater;
    public ListAdapter(Context context, List<rjb> rj)
    {
        mInflater = LayoutInflater.from(context);
        r1 = rj;
    }
    @Override
    public int getCount() {
        return r1.size() ;
    }

    @Override
    public Object getItem(int i) {
        return r1.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
                     view = mInflater.inflate(R.layout.main11, viewGroup, false);
                     holder = new ViewHolder();
                     holder.id=(TextView)view.findViewById(R.id.idv);
                     holder.title = (TextView) view.findViewById(R.id.t);
                     holder.time = (TextView) view.findViewById(R.id.time);
                     holder.content=(TextView)view.findViewById(R.id.c);
                    view.setTag(holder);
                 } else {
                    holder = (ViewHolder) view.getTag();
                 }
             rjb rj = r1.get(i);
             holder.id.setText(rj.getId());
             holder.title.setText(rj.getTitle());
             holder.time.setText(rj.getTime());
             holder.content.setText(rj.getContent());
        return view;
    }
    private class ViewHolder {
        TextView title;
        TextView time;
        TextView id;
        TextView content;

   }
}
