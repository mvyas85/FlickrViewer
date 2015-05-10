package photogallary.mvyas.skava.skavaphotogallary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by manisha.vyas on 5/10/15.
 *  Purpose: To retrieve images in the format of Bitmaps from specified URL
 * The images thus obtained from this class is directly displayed using ImageView on the second Activity
 */

public class DisplayImage extends AsyncTask<String, Void, Bitmap> {

    ImageView bmImage;
    int page;
    String TAG = "DisplayImage";
    View rootView;

    //Constructor for Display Image
    public DisplayImage(ImageView bmImage,int page,View rootView){
        this.bmImage = bmImage;
        this.page = page;
        this.rootView = rootView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        // Creates a new Entry class object and stores the detail of the photo obtained from the EntryList
        // The itImage is the index of the photo that is selected by the user in the Second Activity
        Log.d("MRV","here we have "+page);
        Entry entry = Data.EntryList.get(page);

        // A String urlDisplay is created using the details obtained from the EntryList
        String urlDisplay = new String("https://farm" + entry.farm + ".staticflickr.com/" + entry.server + "/" + entry.id + "_" + entry.secret + ".jpg");
        Bitmap image = null;
        Log.d("MRV", page+"URL ==  " + urlDisplay);
        // The corresponding image is retrieved from the url stored in image
        try {
            InputStream in = new URL(urlDisplay).openStream();

            image = BitmapFactory.decodeStream(in);

        } catch (IOException e) {
            Log.v(TAG,"IO Exception");
        }

        return image;
    }

    protected void onPostExecute(Bitmap result){

        rootView.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        bmImage.setImageBitmap(result);
    }
}
