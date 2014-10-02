package com.alphacoders.wallalphacoders.gallery;

import android.net.Uri;
import android.net.Uri.Builder;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by alextcy on 01.10.14.
 * Взаимодействие с REST API wall.alphacoders.com
 */
public class Storage {
    private static final String TAG = "StorageGallery";
    private static final String API_URL_BASE = "http://wall.alphacoders.com/api1.0/get.php";
    private static final String API_AUTH = "64249b29ec6fae64c2a1863d7cd65a44";

    //ArrayList<Photo> items = new ArrayList<Photo>();

    public ArrayList<Photo> fetchItems(int page, int categoryId)
    {
        ArrayList<Photo> itemsPhoto = new ArrayList<Photo>();

        try {
            String url = buildUrl(page, categoryId);
            String responseJson = getResponse(url);

            JSONObject responseJsonObject = new JSONObject(responseJson);
            JSONArray itemsList = responseJsonObject.getJSONArray("wallpapers");

            for(int i=0; i<itemsList.length(); i++) {
                JSONObject itemObject = itemsList.getJSONObject(i);

                Photo photo = new Photo();
                photo.setId( Integer.parseInt( itemObject.getString("id") ) );
                photo.setCategoryName(itemObject.getString("category"));
                photo.setOriginalUrl(itemObject.getString("url"));
                photo.setThumbUrl( itemObject.getJSONObject("thumb").getString("url") );
                photo.setThumbBigUrl( itemObject.getJSONObject("thumbbig").getString("url") );
                photo.setWidth( Integer.parseInt( itemObject.getString("width")) );
                photo.setHeight( Integer.parseInt( itemObject.getString("height")) );

                itemsPhoto.add(photo);
            }


        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (JSONException ex) {
            Log.e(TAG, "Failed to parse json", ex);
        }

        return itemsPhoto;
    }

    public String getResponse(String urlApi) throws IOException
    {
        return new String(getResponseBytes(urlApi));
    }

    /**
     * Получаем ответ от сервиса wall.alphacoders.com в виде массива байт
     * @param urlApi String
     * @return byte[]
     * @throws IOException
     */
    byte[] getResponseBytes(String urlApi) throws IOException
    {
        URL url = new URL(urlApi);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream inSm = connection.getInputStream();
            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = inSm.read(buffer)) > 0 ) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();

            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }


    private String buildUrl(int page, int categoryId)
    {
        Builder uriBuilder = Uri.parse(API_URL_BASE).buildUpon();
        uriBuilder.appendQueryParameter("auth", API_AUTH);
        uriBuilder.appendQueryParameter("page", Integer.toString(page));
        if(categoryId != 0) {
            uriBuilder.appendQueryParameter("category_id", Integer.toString(categoryId));
        }

        String url = uriBuilder.build().toString();
        return url;
    }
}
