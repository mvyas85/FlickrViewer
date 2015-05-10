package photogallary.mvyas.skava.skavaphotogallary;

/**
 * Created by manisha.vyas on 5/10/15.
 * Purpose: A class which has attributes that can be used to store the important
 * information of the photos needed to be retrieved from the Flickr Website
 */
public class Entry {

    String farm;  // farm
    String server; // server
    String id;  // photo id
    String secret; // secret
    String title;

    // Constructor for class Entry
    Entry(String farm, String server, String id, String secret,String title){
        this.farm = farm;
        this.server = server;
        this.id = id;
        this.secret = secret;
        this.title = title;
    }
}
