package com.alphacoders.wallalphacoders.gallery;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.alphacoders.wallalphacoders.R;


public class GalleryFragment extends Fragment
{
    public static final String CAT_ID_KEY	= "com.alphacoders.wallwallalpacoders.cat_id";
    public static final String CAT_NAME_KEY	= "com.alphacoders.wallwallalpacoders.cat_name";

    GridView galleryGridView;

    public static GalleryFragment newInstance(int catId, String catName)
    {
        Bundle args = new Bundle();
        args.putInt(CAT_ID_KEY, catId);
        args.putString(CAT_NAME_KEY, catName);

        GalleryFragment fragment = new GalleryFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getArguments().getString(CAT_NAME_KEY));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);

        galleryGridView = (GridView) v.findViewById(R.id.gridView);

        return v;
    }



}
