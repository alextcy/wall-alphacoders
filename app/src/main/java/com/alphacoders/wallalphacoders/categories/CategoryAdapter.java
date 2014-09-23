package com.alphacoders.wallalphacoders.categories;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alphacoders.wallalphacoders.R;

import java.util.ArrayList;

/**
 * Created by alextcy on 23.09.14.
 */
public class CategoryAdapter extends ArrayAdapter<Category>
{
    private ArrayList<Category> catList;

    public CategoryAdapter(ArrayList<Category> itemsList, Context appContext)
    {
        super(appContext, 0, itemsList);

        catList = itemsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent )
    {
        if(convertView == null) {
            //convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_picture, null);
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_category, null);
        }
        Category cat = catList.get(position);

        TextView categoryNameView = (TextView) convertView.findViewById(R.id.category_name);
        categoryNameView.setText(cat.getName());

        //картинку из drawable показать
        ImageView categoryImageView = (ImageView) convertView.findViewById(R.id.category_image);
        //categoryImageView.setImageDrawable(R.drawable.);

        //String uri = "@drawable/" + cat.getImage();
        //String uri = cat.getImage();
        String uri = "abstarct";
        int imageResource = getContext().getResources().getIdentifier(uri, null, getContext().getPackageName());
        Drawable res = getContext().getResources().getDrawable(imageResource);
        categoryImageView.setImageDrawable(res);

        return convertView;
    }
}
