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

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;

        if(obj instanceof Entry)
        {
            Entry etr = (Entry) obj;
            if(etr.getId().equals(this.id) && etr.getFarm().equals(this.farm)
                    && etr.getSecret().equals(this.secret) && etr.getServer().equals(this.server)){
                return true;
            }
        }
        return false;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
