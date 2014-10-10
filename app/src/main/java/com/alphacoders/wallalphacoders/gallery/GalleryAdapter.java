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
    ThumbnailDownloader<ImageView> thumbnailThread;

    public GalleryAdapter(ArrayList<Photo> itemsList, ThumbnailDownloader<ImageView> thumbThread, Context appContext)
    {
        super(appContext, 0, itemsList);
        this.thumbnailThread = thumbThread;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gallery_photo_item, null);
        }

        Photo photo = getItem(position);

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photo_item);
        photoImageView.setImageResource(R.drawable.wait_icon);

        thumbnailThread.queueThumbnail(photoImageView, photo.getThumbUrl());


        //тестовый вывод ID картинок для проверки получения списка фоток
        //TextView photoTextView = (TextView)convertView.findViewById(R.id.gallery_photo_name);
        //photoTextView.setText(Integer.toString(photo.getId()));

        return convertView;
    }
}
