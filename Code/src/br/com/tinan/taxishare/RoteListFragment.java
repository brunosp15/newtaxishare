package br.com.tinan.taxishare;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.ButterKnife;

public class RoteListFragment extends Fragment {

	Context mContext;
	ListView mRoteList;
	String[] mAddresses;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.rote_list_fragment, container, false);
		mContext = getActivity();
		ButterKnife.inject(this, rootView);

		mRoteList = (ListView) rootView.findViewById(R.id.list_rote_list);

		Bundle bundle = getArguments();
		mAddresses = bundle.getStringArray("mAddresses");
		RoteListAdapter adapter = new RoteListAdapter(mAddresses, mContext);
		mRoteList.setAdapter(adapter);

		return rootView;

	}
}
