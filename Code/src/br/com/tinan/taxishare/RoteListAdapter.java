package br.com.tinan.taxishare;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RoteListAdapter extends BaseAdapter {

	ArrayList<Rote> mAddresses;
	Context mContext;

	public RoteListAdapter(ArrayList<Rote> mAddresses, Context mContext) {
		this.mAddresses = mAddresses;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mAddresses.size();
	}

	@Override
	public Rote getItem(int position) {
		// TODO Auto-generated method stub
		return mAddresses.get(position);
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

		TextView txtOrigim = (TextView) convertView.findViewById(R.id.txt_origim_rote_list_item);
		TextView txtDestiny = (TextView) convertView.findViewById(R.id.txt_destiny_rote_list_item);

		txtOrigim.setText(mAddresses.get(position).getOrigimString());
		txtDestiny.setText(mAddresses.get(position).getDestinyString());

		return convertView;
	}

}
