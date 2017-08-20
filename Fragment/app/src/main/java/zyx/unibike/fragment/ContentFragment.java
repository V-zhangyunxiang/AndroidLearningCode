package zyx.unibike.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ContentFragment extends Fragment{
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//当boolean参数为null时,不需要添加container参数
		//View view =inflater.inflate(R.layout.fragment_content, container, false);
		View view = inflater.inflate(R.layout.fragment_content, null);
		return view;
	
	}

}
