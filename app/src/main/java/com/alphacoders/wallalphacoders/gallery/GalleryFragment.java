package com.alphacoders.wallalphacoders.gallery;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.alphacoders.wallalphacoders.R;

import java.util.ArrayList;


public class GalleryFragment extends Fragment
{
    public static final String CAT_ID_KEY	= "com.alphacoders.wallwallalpacoders.cat_id";
    public static final String CAT_NAME_KEY	= "com.alphacoders.wallwallalpacoders.cat_name";

    GridView galleryGridView;
    ArrayList<Photo> itemsPhoto = new ArrayList<Photo>();
    GalleryAdapter galleryAdapter;
    ThumbnailDownloader<ImageView> thumbnailThread;
    int categoryId;

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

        categoryId = getArguments().getInt(CAT_ID_KEY);
        //Control whether a fragment instance is retained across Activity re-creation (such as from a configuration change).
        //setRetainInstance(true);

        //вызывать AsyncTask для первичного получения в фоновом потоке списка фоток (при запуске фрагмента первый раз)
        //последующие вызовы нужно делать в onScroll-чегототам обработчик скролера (получать новые фотки)
        //подумать как пердавать номер страницы в Storage ?

        new FetchPhotoItemsAsyncTask().execute(1);

        thumbnailThread = new ThumbnailDownloader<ImageView>();
        thumbnailThread.start();
        thumbnailThread.getLooper();
        Log.i("GalleryFragment", "Background thread started");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);

        galleryGridView = (GridView) v.findViewById(R.id.gridView);

        galleryGridView.setOnScrollListener(new EndlessScrollListener(2) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                new FetchPhotoItemsAsyncTask().execute(page);
            }
        });

        return v;
    }

    public void setupAdapter()
    {
        if(galleryAdapter != null) {
            galleryAdapter.notifyDataSetChanged();
        } else {
            galleryAdapter = new GalleryAdapter(itemsPhoto, thumbnailThread, getActivity());
            galleryGridView.setAdapter(galleryAdapter);
        }

        /*if(itemsPhoto != null) {
            GalleryAdapter galleryAdapter = new GalleryAdapter(itemsPhoto, getActivity());
            galleryGridView.setAdapter(galleryAdapter);

        } else {
            galleryGridView.setAdapter(null);
        }*/
    }


    @Override
    public void onDestroy()
    {
        super.onDestroy();
        thumbnailThread.quit();
        Log.i("GalleryFragment", "Background thread destroyed");
    }

    private class FetchPhotoItemsAsyncTask extends AsyncTask<Integer, Void, ArrayList<Photo>>
    {
        private ProgressDialog dialog = new ProgressDialog(getActivity());

        protected void onPreExecute() {
            dialog.setMessage("Please wait...");
            dialog.show();
        }

        @Override
        protected  ArrayList<Photo> doInBackground(Integer... params)
        {
            //сделать их свойствами класса для глобальногт доступа
            //обновлять это значение в onScroll...
            //int page = 1;
            Integer page = params[0];

            return new Storage().fetchItems(page, categoryId);
        }

        @Override
        protected void onPostExecute(ArrayList<Photo> items)
        {
            //itemsPhoto = items;
            itemsPhoto.addAll(items);
            setupAdapter();

            dialog.dismiss();
        }
    }

}
