package com.alphacoders.wallalphacoders.categories;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by alextcy on 22.09.14.
 */
public class Storage {

    private static Storage instance;
    private Context mAppContext;

    private String dataFilename = "categories.json";
    private ArrayList<Category> catList = new ArrayList<Category>();

    public static Storage getInstance(Context appContext)
    {
        if(instance == null) {
            instance = new Storage(appContext.getApplicationContext());
        }
        return instance;
    }

    private Storage(Context appContext)
    {
        try {
            mAppContext = appContext;

            String jsonString = loadJson(dataFilename);
            JSONArray itemsList = new JSONArray(jsonString);

            for(int i=0; i<itemsList.length(); i++) {
                JSONObject itemObject = itemsList.getJSONObject(i);
                catList.add( new Category(
                        itemObject.getInt("id"),
                        itemObject.getString("name"),
                        itemObject.getString("image")
                ) );
            }

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }


    public ArrayList<Category> getCategories()
    {
        return catList;
    }

    private String loadJson(String jsonFile)
    {
        try {
            InputStream is = mAppContext.getAssets().open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String bufferString = new String(buffer);

            return bufferString;

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
