package com.alphacoders.wallalphacoders.categories;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
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

        ImageView categoryImageView = (ImageView) convertView.findViewById(R.id.category_image);

        //ресурсные картинки НЕ могут совпадать с зарезервироваными словами (abstract,if,else,class ... )
        //имя картинки должно быть без расширения
        String imageNameWithoutExt = cat.getImage().substring(0, cat.getImage().lastIndexOf("."));
        //получаем идентификатор ресурса картинки по ее имени
        int imageResource = getContext().getResources().getIdentifier(
            imageNameWithoutExt, "drawable", getContext().getPackageName()
        );

        if(imageResource != 0) {
            categoryImageView.setImageResource(imageResource);
        } else {
            categoryImageView.setImageResource(R.drawable.ic_launcher);
        }

        return convertView;
    }
}
