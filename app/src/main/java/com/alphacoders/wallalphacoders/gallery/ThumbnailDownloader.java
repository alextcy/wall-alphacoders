package com.alphacoders.wallalphacoders.gallery;

import android.os.HandlerThread;
import android.util.Log;

/**
 * Created by alextcy on 08.10.14.
 *
 */
public class ThumbnailDownloader<Token> extends HandlerThread
{
    public ThumbnailDownloader()
    {
        super("ThumbnailDownloader");
    }

    //добавляем в очередь ссылку на картинку которую нужно выкачать
    public void queueThumbnail(Token token, String url)
    {
        Log.i("ThumbnailDownloader", "add to queue URL: " + url);
    }
}
