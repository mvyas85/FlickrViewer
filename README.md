# FlickrViewer

This is simple application for Flickr picture viewer applicaiton. It uses Flickr API to view infinite scrolling images.

#Features ::
 - flickr.photos.getRecent API
    - method=flickr.photos.getRecent
    - format=json
 - Reading JSONObject using call to getJSONFromUrl()
 - Loading images using AsyncTask.
    - These application downloads 10 images at first load, when user scrolls to 7th/17th/27th.. image it will load next set of 10 images that way it controls Caching.
 - This application uses ViewPager with FragmentStatePagerAdapter.
 - I have used ZoomOutPageTransformer to show cool page swipe animation.
 
![Image of Yaktocat](https://github.com/mvyas85/FlickrViewer/blob/master/ScreenCaptures/Sc1.png)

![Image of Yaktocat](https://github.com/mvyas85/FlickrViewer/blob/master/ScreenCaptures/sc2.png)

