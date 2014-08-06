package br.com.tinan.taxishare;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.ButterKnife;

public class RoteListFragment extends Fragment {

	Context mContext;
	ListView mRoteListView;
	ArrayList<Rote> mRoteList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.rote_list_fragment, container, false);
		mContext = getActivity();
		ButterKnife.inject(this, rootView);

		mRoteListView = (ListView) rootView.findViewById(R.id.list_rote_list);

		Bundle bundle = getArguments();
		mRoteList = bundle.getParcelableArrayList("mRoteList");
		RoteListAdapter adapter = new RoteListAdapter(mRoteList, mContext);
		mRoteListView.setAdapter(adapter);

		return rootView;

	}
}
