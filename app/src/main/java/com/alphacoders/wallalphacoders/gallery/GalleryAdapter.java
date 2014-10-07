package com.alphacoders.wallalphacoders.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alphacoders.wallalphacoders.R;

import java.util.ArrayList;

/**
 * Created by alextcy on 06.10.14.
 *
 */
public class GalleryAdapter extends ArrayAdapter<Photo>
{
    public GalleryAdapter(ArrayList<Photo> itemsList, Context appContext)
    {
        super(appContext, 0, itemsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gallery_photo_item, null);
        }

        Photo photo = getItem(position);

        //ImageView photoImageView = (ImageView) convertView.findViewById(R.id.gallery_photo_item);
        //photoImageView.setImageResource(R.drawable.ic_launcher);
        //нужно реализовать функционал внедрения картинок в imageView

        //тестовый вывод ID картинок для проверки получения списка фоток
        TextView photoTextView = (TextView)convertView.findViewById(R.id.gallery_photo_name);
        photoTextView.setText(Integer.toString(photo.getId()));

        return convertView;
    }
}
