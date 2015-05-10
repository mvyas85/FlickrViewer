package photogallary.mvyas.skava.skavaphotogallary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by manisha.vyas on 5/10/15.
 */
public class ScreenSlidePageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        Log.d("MRV == ","creating "+pageNumber);
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);

        if(mPageNumber != 0 && mPageNumber != 1 && mPageNumber+4 > Data.EntryList.size()){

            Log.d("MRV","=================== Its time now ==============");
            URLReader urlr = new URLReader(URLReader.loadNextPageImages());
            urlr.execute();

        }
        new DisplayImage((ImageView) rootView.findViewById(R.id.imageView2),mPageNumber,rootView).execute();

        // Set the title view to show the page number.
        Entry entry = null;
        if(Data.EntryList.size() !=0) {
            entry = Data.EntryList.get(mPageNumber);
            ((TextView) rootView.findViewById(R.id.tv1)).setText(entry.title);
        }

        return rootView;
    }
}
//3,8,12
