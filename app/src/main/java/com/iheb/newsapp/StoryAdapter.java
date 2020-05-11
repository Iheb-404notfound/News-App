package com.iheb.newsapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StoryAdapter extends ArrayAdapter<Story> {
    public StoryAdapter(@NonNull Context context, int resource, @NonNull List<Story> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View list_item = convertView;
        TextView title;
        TextView date;
        TextView SectionName;
        Story current = getItem(position);
        if (list_item == null) {
            list_item = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        title = list_item.findViewById(R.id.section_title);
        date = list_item.findViewById(R.id.date);
        SectionName = list_item.findViewById(R.id.section_name);
        title.setText(current.getWebTitle());
        date.setText(current.getWebPublicationDate());
        SectionName.setText(current.getSectionName());
        return list_item;
    }
}
