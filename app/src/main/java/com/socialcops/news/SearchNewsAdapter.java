package com.socialcops.news;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;


class SearchNewsAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;

    public SearchNewsAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data = d;
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        SearchNewsViewHolder holder = null;
        if (convertView == null) {
            holder = new SearchNewsViewHolder();
            convertView = LayoutInflater.from(activity).inflate(
                    R.layout.news_item, parent, false);
            holder.galleryImage = (ImageView) convertView.findViewById(R.id.newsimage);

            holder.title = (TextView) convertView.findViewById(R.id.headline);
            //  holder.source = (TextView) convertView.findViewById(R.id.newssource);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder = (SearchNewsViewHolder) convertView.getTag();
        }
        holder.galleryImage.setId(position);

        holder.title.setId(position);

        holder.time.setId(position);
        // holder.source.setId(position);
        HashMap<String, String> song;
        song = data.get(position);

        try {
            String arr[] = song.get(SearchFragment.KEY_PUBLISHEDAT).split("T");
            holder.title.setText(song.get(SearchFragment.KEY_TITLE));
            holder.time.setText(arr[0]);
            // holder.time.setText(song.get(SearchFragment.NEWS_SOURCE));
            DisplayMetrics displaymetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

            int wt = displaymetrics.widthPixels;

            if (song.get(SearchFragment.KEY_URLTOIMAGE).length() < 5) {
                System.out.println("If  " + song.get(SearchFragment.KEY_URLTOIMAGE));
                holder.galleryImage.setVisibility(View.GONE);
            } else {
                System.out.println(song.get(SearchFragment.KEY_URLTOIMAGE));
                Picasso.with(activity)
                        .load(song.get(SearchFragment.KEY_URLTOIMAGE))
                        .centerCrop()
                        .resize(wt - 32, 315)
                        .into(holder.galleryImage);
            }
        } catch (Exception e) {
            System.out.println("Hello   " + song.get(SearchFragment.KEY_URLTOIMAGE));
        }
        return convertView;
    }
}

class SearchNewsViewHolder {
    ImageView galleryImage;
    TextView title, time, source;

}