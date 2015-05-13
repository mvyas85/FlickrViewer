package photogallary.mvyas.skava.skavaphotogallary;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by manisha.vyas on 5/10/15.
 *
 * Purpose: This class uses the Flickr API method people.getPhotos
 * to return details of the publically available photos
 * in JSON format and stores the details in a List of Entry class
 */

/*https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=3e4c6cc06971191d5115828a46d2283d&per_page=10&page=1&format=json&nojsoncallback=1
 */
public class URLReader extends AsyncTask<String, Void, Void> {


    String FlickrBaseURL =
            "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=3e4c6cc06971191d5115828a46d2283d";
    String per_page = "&per_page=20&page=";
    String FlickrUrlEnd = "&format=json&nojsoncallback=1";
    String jsonText = null; // The doInBackground function uses this string text to read from the URL which specifies the FlickrAPI method
    static int page_number=0;

    String TAG = "URLReader class";


    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    URLReader(int page_number){
        this.page_number = page_number;
    }
    @Override
    protected Void doInBackground(String... params) {

        String url = new String(FlickrBaseURL+per_page+page_number+FlickrUrlEnd); // URL that specifies the FlickrAPI method
        Log.v("MRV ------>>>>>>>>>","starting" +FlickrBaseURL+per_page+page_number+FlickrUrlEnd);
        JSONObject jObj = getJSONFromUrl(url);
        try {
            JSONObject Json_photos = jObj.getJSONObject("photos");
            JSONArray JsonArray_photo = Json_photos.getJSONArray("photo");
            for(int i = 0; i < (JsonArray_photo.length()); i++) {
                JSONObject FlickrPhoto = JsonArray_photo.getJSONObject(i);

                // Stores objects of Entry class in the Entry List extracted from the JSONObject obtained from the URL
                // Each entry has 4 attributes: Farm, Server, Photo Id, Secret
                Entry newEntry = new Entry(FlickrPhoto.getString("farm"), FlickrPhoto.getString("server"), FlickrPhoto.getString("id"), FlickrPhoto.getString("secret"),FlickrPhoto.getString("title"));

                boolean duplicate = false;
                for(int j =0 ; j<Data.EntryList.size();j++ ){

                    if(Data.EntryList.get(j).equals(newEntry))
                        duplicate = true;
                }
                if(!duplicate)
                    Data.EntryList.add(newEntry);
            }

        } catch (JSONException e) {
            Log.d(TAG,"JSON Exception");
        }

        return null;
    }

    public JSONObject getJSONFromUrl(String url) {

        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader( is, "utf-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;

    }

    public static int loadNextPageImages(){
        page_number++;
        Log.d("MRV - ","page number increased to "+page_number);
        return page_number;
    }
}

