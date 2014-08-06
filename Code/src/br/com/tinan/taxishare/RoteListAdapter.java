package br.com.tinan.taxishare;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RoteListAdapter extends BaseAdapter {

	String[] mAddresses;
	Context mContext;

	public RoteListAdapter(String[] mAddresses, Context mContext) {
		this.mAddresses = mAddresses;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mAddresses.length;
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return mAddresses[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.rote_list_item, null);
		}

		TextView txtTitle = (TextView) convertView.findViewById(R.id.txt_title_rote_list_item);

		txtTitle.setText(mAddresses[position]);

		return convertView;
	}

}
