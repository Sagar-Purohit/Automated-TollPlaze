package com.example.dell.tollapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by dell on 12-Feb-18.
 */
public class Customadapterroot extends BaseAdapter
        {
        Context context;
        ArrayList<Rootview> cm;
        Deleget delegate;

public Customadapterroot(Context context, ArrayList<Rootview> cm, Deleget delegate) {
        this.context = context;
        this.cm = cm;
        this.delegate = delegate;
        }

@Override
public int getCount() {
        return cm.size();
        }

@Override
public Object getItem(int position) {
        return null;
        }

@Override
public long getItemId(int position) {
        return 0;
        }
@Override
public View getView(int position, View convertView, ViewGroup parent)
        {
        try {


final viewholder viewholder;

        if(convertView==null) {
        viewholder=new viewholder();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.row, null);
        viewholder.tvName = (TextView) convertView.findViewById(R.id.name);
        viewholder.source = (TextView) convertView.findViewById(R.id.source);
        viewholder.destination = (TextView) convertView.findViewById(R.id.destination);
        viewholder.to =(TextView) convertView.findViewById(R.id.to);
        viewholder.linear = (LinearLayout) convertView.findViewById(R.id.linear);

        convertView.setTag(position);

        viewholder.linear.setTag(position);
        }
        else
        {
        viewholder=(viewholder)convertView.getTag();
        }
        //   ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        viewholder.linear.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        int pos=(Integer)viewholder.linear.getTag();
        Toast.makeText(context, "pos::" + pos, Toast.LENGTH_SHORT).show();

delegate.onclickemethod(cm.get(pos));
      //  delegate.onitemclick(cm.get(pos));

        }
        });


            viewholder.tvName.setText(cm.get(position).getName());
            viewholder.source.setText(cm.get(position).getSource());
            viewholder.destination.setText(cm.get(position).getDestination());
          }catch (Exception e)
        {
        e.printStackTrace();
        }
        return convertView;
        }
public  class viewholder{
    TextView tvName,source,destination,to;
        LinearLayout linear;


}
}
