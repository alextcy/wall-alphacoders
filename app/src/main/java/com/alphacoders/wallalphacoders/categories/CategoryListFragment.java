package com.alphacoders.wallalphacoders.categories;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
//import android.widget.ArrayAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alphacoders.wallalphacoders.R;

import java.util.ArrayList;
//import com.alphacoders.wallalphacoders.categories.dummy.DummyContent;


public class CategoryListFragment extends ListFragment {

    ArrayList<Category> catList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CategoryListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.app_name);


        catList = new Storage(getActivity()).getCategories();
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);


    }


    private class CategoryAdapter extends ArrayAdapter<Category> {

        public CategoryAdapter(ArrayList<Category> catList)
        {
            super(getActivity(), 0, catList);
        }
    }

}
