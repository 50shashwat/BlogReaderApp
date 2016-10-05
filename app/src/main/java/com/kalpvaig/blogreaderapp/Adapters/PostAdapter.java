package com.kalpvaig.blogreaderapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kalpvaig.blogreaderapp.Model.Post;
import com.kalpvaig.blogreaderapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by akshay on 5/10/16.
 */

public class PostAdapter extends ArrayAdapter<Post> {

    private ArrayList<Post> list;
    private LayoutInflater inflater;
    private Context mContext;

    public PostAdapter(Context context, int resource, ArrayList<Post> list) {
        super(context, resource, list);
        this.list = list;
        mContext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount(){
        if (list == null)
            list = new ArrayList<>();

        if (list.size()>0)
            return list.size();

        else
            return 0;
    }


    class ViewHolder{
        TextView date,author,title;
        ImageView thumbnail;
    }

    public View getView(int position, View convertView, ViewGroup view){

        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.single_post,null);
            holder = new ViewHolder();
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.author = (TextView) convertView.findViewById(R.id.author);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.date.setText(list.get(position).getDate());
        holder.title.setText(list.get(position).getTitle());
        holder.author.setText(list.get(position).getAuthor());

        Picasso.with(mContext)
                .load(list.get(position).getThubnail())
                .into(holder.thumbnail);

        return convertView;

    }


}
