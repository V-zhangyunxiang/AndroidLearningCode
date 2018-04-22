package zyx.unibike.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopBackFragment extends Fragment {

    //private String title;

    public PopBackFragment() {
        // Required empty public constructor
    }

//    public PopBackFragment(String title) {
//        this.title = title;
//
//    }

    /**
     * fragment标准传参方式
     * @param title
     * @return
     */
    public static PopBackFragment getInstance(String title) {
        PopBackFragment p = new PopBackFragment();
        Bundle b = new Bundle();
        b.putString("title", title);
        p.setArguments(b);
        return p;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pop_back, container, false);
        TextView tv = (TextView) v.findViewById(R.id.tv_pop_back);
        tv.setText(getArguments().getString("title").toString());
        return v;
    }

}
