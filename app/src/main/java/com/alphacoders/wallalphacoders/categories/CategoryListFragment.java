package com.alphacoders.wallalphacoders.categories;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.alphacoders.wallalphacoders.GalleryActivity;
import com.alphacoders.wallalphacoders.R;

import java.util.ArrayList;



public class CategoryListFragment extends ListFragment
{
    public static final String CAT_ID_KEY	= "com.alphacoders.wallwallalpacoders.cat_id";
    public static final String CAT_NAME_KEY	= "com.alphacoders.wallwallalpacoders.cat_name";

    ArrayList<Category> catList;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CategoryListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.app_name);

        //получаем список категорий из JSON файла
        catList = Storage.getInstance(getActivity()).getCategories();
        CategoryAdapter adapter = new CategoryAdapter(catList, getActivity());
        setListAdapter(adapter);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Category cat = (Category) getListAdapter().getItem(position);

        Intent intent = new Intent(getActivity(), GalleryActivity.class);
        intent.putExtra(CAT_ID_KEY, cat.getId()); //передаем ID категории чтобы показать фотки из нее
        intent.putExtra(CAT_NAME_KEY, cat.getName());

        startActivity(intent);
    }



}
